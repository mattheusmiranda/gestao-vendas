package com.miranda.gestao_vendas.repositorio;

import com.miranda.gestao_vendas.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaCodigo(Long codigoCategoria);

    @Query("SELECT prod" +
            " FROM Produto prod" +
            " WHERE prod.codigo = :codigo" +
            "   AND prod.categoria.codigo = :codigoCategoria")
    Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria);

}
