package com.miranda.gestao_vendas.dto.categoria;

import com.miranda.gestao_vendas.entidades.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Categoria retorno DTO")
@Getter
@Setter
@AllArgsConstructor
public class CategoriaResponseDTO {

    @Schema(description = "Código")
    private Long codigo;

    @Schema(description = "Nome")
    private String nome;

    public static CategoriaResponseDTO conveterParaCategoriaDTO(Categoria categoria) {
        return new CategoriaResponseDTO(categoria.getCodigo(), categoria.getNome());
    }
}
