package com.miranda.gestao_vendas.servico;

import com.miranda.gestao_vendas.entidades.Categoria;
import com.miranda.gestao_vendas.excecao.RegraNegocioException;
import com.miranda.gestao_vendas.repositorio.CategoriaRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServico {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public List<Categoria> listarTodas() {
        return categoriaRepositorio.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepositorio.findById(id);
    }

    public Categoria salvar(Categoria categoria) {
        validarCategoriaDuplicada(categoria);
        return categoriaRepositorio.save(categoria);
    }

    public Categoria atualizar(Long codigo, Categoria categoria) {
        Categoria categoriaExistente = validarSeCategoriaExiste(codigo);
        validarCategoriaDuplicada(categoria);
        BeanUtils.copyProperties(categoria, categoriaExistente, "codigo");
        return categoriaRepositorio.save(categoriaExistente);
    }

    public void deletar(Long codigo) {
        categoriaRepositorio.deleteById(codigo);
    }

    private Categoria validarSeCategoriaExiste(Long codigo) {
        Optional<Categoria> categoriaBuscada = buscarPorId(codigo);
        if(categoriaBuscada.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return categoriaBuscada.get();
    }

    private void validarCategoriaDuplicada(Categoria categoria) {
        Categoria categoriaEncontrada = categoriaRepositorio.findByNome(categoria.getNome());
        if(categoriaEncontrada != null && categoriaEncontrada.getCodigo() != categoria.getCodigo())
            throw new RegraNegocioException(String.format("A categoria %s já esta cadastrada", categoria.getNome().toUpperCase()));
    }
}
