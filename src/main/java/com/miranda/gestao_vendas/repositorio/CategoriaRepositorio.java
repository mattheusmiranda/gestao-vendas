package com.miranda.gestao_vendas.repositorio;

import com.miranda.gestao_vendas.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

    Categoria findByNome(String nome);

    Categoria findByCodigo(Long codigo);

}
