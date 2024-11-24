package com.miranda.gestao_vendas.excecao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Erro {
    private String mensagemUsuario;
    private String mensagemDesenvolvedor;

}
