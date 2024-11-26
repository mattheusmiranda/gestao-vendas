package com.miranda.gestao_vendas.dto.produto;

import com.miranda.gestao_vendas.entidades.Categoria;
import com.miranda.gestao_vendas.entidades.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Schema(description = "Categoria requisição DTO")
@Getter
@Setter
@AllArgsConstructor
public class ProdutoRequestDTO {

    @Schema(description = "Descricao")
    @NotBlank(message = "Descricao")
    @Length(min = 3, max = 100, message = "Descrição")
    private String descricao;

    @Schema(description = "Quantidade")
    @NotNull(message = "Quantidade")
    private Integer quantidade;

    @Schema(description = "Preço Custo")
    @NotNull(message = "Preço Custo")
    private BigDecimal precoCusto;

    @Schema(description = "Preço Venda")
    @NotNull(message = "Preço Venda")
    private BigDecimal precoVenda;

    @Schema(description = "Observação")
    @Length(max = 500, message = "Observação")
    private String observacao;

    public Produto converterParaEntidade(Long codigoCategoria) {
        return new Produto(descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
    }

    public Produto converterParaEntidade(Long codigoCategoria, Long codigoProduto) {
        return new Produto(descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
    }

}
