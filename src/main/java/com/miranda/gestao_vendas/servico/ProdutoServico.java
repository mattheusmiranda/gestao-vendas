package com.miranda.gestao_vendas.servico;

import com.miranda.gestao_vendas.entidades.Produto;
import com.miranda.gestao_vendas.excecao.RegraNegocioException;
import com.miranda.gestao_vendas.repositorio.ProdutoRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private CategoriaServico categoriaServico;

    public List<Produto> listarTodos(Long codigoCategoria) {
        return produtoRepositorio.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria) {
        return produtoRepositorio.buscarPorCodigo(codigo, codigoCategoria);
    }

    public Produto salvar(Long codigoCategoria, Produto produto) {
        validarCategoriaDoProdutoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        return produtoRepositorio.save(produto);
    }

    public Produto atualicar(Long codigoCategoria, Long codigoProduto, Produto produto) {
        Produto produtSalvar = validarProdutoExiste(codigoProduto, codigoCategoria);
        validarCategoriaDoProdutoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        BeanUtils.copyProperties(produto, produtSalvar, "codigo");
        return produtoRepositorio.save(produtSalvar);
    }

    public void deletar(Long codigoCategoria, Long codigoProduto) {
        Produto produto = validarProdutoExiste(codigoProduto, codigoCategoria);
        produtoRepositorio.delete(produto);
    }

    private Produto validarProdutoExiste(Long codigoProduto, Long codigoCategoria) {
        Optional<Produto> produto = buscarPorCodigo(codigoProduto, codigoCategoria);
        if(produto.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return produto.get();
    }

    private void validarProdutoDuplicado(Produto produto) {
        Optional<Produto> produtoPorDescricao = produtoRepositorio.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(),
                produto.getDescricao());
        if(produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()) {
            throw new RegraNegocioException(String.format("O produto %s já está cadastrado", produto.getDescricao()));
        }
    }

    private void validarCategoriaDoProdutoExiste(Long codigoCategoria) {
        if(codigoCategoria == null) throw new RegraNegocioException("A categoria não pode ser nula");

        if(categoriaServico.buscarPorCodigo(codigoCategoria).isEmpty()) {
            throw new RegraNegocioException(String.format("A categoria de codigo %s informada não existe no cadastro", codigoCategoria));
        }
    }

}
