package com.miranda.gestao_vendas.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Getter
@Setter
@EqualsAndHashCode
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    @NotBlank(message = "Descricao")
    @Length(min = 3, max = 100, message = "Descroção")
    private String descricao;

    @Column(name = "quantidade")
    @NotNull(message = "Quantidade")
    private Integer quantidade;

    @Column(name = "preco_custo")
    @NotNull(message = "Preço Custo")
    private BigDecimal precoCusto;

    @Column(name = "preco_venda")
    @NotNull(message = "Preço Venda")
    private BigDecimal precoVenda;

    @Column(name = "observacao")
    @Length(max = 500, message = "Observação")
    private String observacao;

    @NotNull(message = "Código categoria")
    @ManyToOne
    @JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
    private Categoria categoria;

}
