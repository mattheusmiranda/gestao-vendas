package com.miranda.gestao_vendas.dto.produto;

import com.miranda.gestao_vendas.dto.categoria.CategoriaResponseDTO;
import com.miranda.gestao_vendas.entidades.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Schema(description = "Produto retorno DTO")
@Getter
@Setter
@AllArgsConstructor
public class ProdutoResponseDTO {

    @Schema(description = "CÓdigo")
    private Long codigo;

    @Schema(description = "Descricao")
    private String descricao;

    @Schema(description = "Quantidade")
    private Integer quantidade;

    @Schema(description = "Preço Custo")
    private BigDecimal precoCusto;

    @Schema(description = "Preço Venda")
    private BigDecimal precoVenda;

    @Schema(description = "Observação")
    private String observacao;

    @Schema(description = "Categoria")
    private CategoriaResponseDTO categoriaResponseDTO;

    public static ProdutoResponseDTO converterParaProdutoDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getCodigo(),
                produto.getDescricao(),
                produto.getQuantidade(),
                produto.getPrecoCusto(),
                produto.getPrecoVenda(),
                produto.getObservacao(),
                CategoriaResponseDTO.conveterParaCategoriaDTO(produto.getCategoria()));
    }

}
