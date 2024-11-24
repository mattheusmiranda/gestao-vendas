package com.miranda.gestao_vendas.controlador.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestão de Vendas")
                        .description("Sistema de gestão de vendas")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Seu Nome")
                                .email("seuemail@exemplo.com")
                                .url("https://www.seusite.com")));
    }
}
