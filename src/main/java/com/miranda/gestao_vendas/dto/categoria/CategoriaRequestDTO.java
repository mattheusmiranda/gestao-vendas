package com.miranda.gestao_vendas.dto.categoria;

import com.miranda.gestao_vendas.entidades.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Schema(description = "Categoria requisição DTO")
@Getter
@Setter
@AllArgsConstructor
public class CategoriaRequestDTO {

    @Schema(description = "Nome")
    @NotBlank
    @Length(min = 3, max = 50)
    private String nome;

    public Categoria coverteParaEntidade() {
        return new Categoria(nome);
    }
    public Categoria coverteParaEntidade(Long codigo) {
        return new Categoria(codigo, nome);
    }

}
