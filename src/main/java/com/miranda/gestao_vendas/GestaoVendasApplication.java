package com.miranda.gestao_vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.miranda.gestao_vendas.entidades"})
@EnableJpaRepositories(basePackages = {"com.miranda.gestao_vendas.repositorio"})
@ComponentScan(basePackages = {"com.miranda.gestao_vendas.servico", "com.miranda.gestao_vendas.controlador",
        "com.miranda.gestao_vendas.excecao"})
@SpringBootApplication
public class GestaoVendasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoVendasApplication.class, args);
    }

}
