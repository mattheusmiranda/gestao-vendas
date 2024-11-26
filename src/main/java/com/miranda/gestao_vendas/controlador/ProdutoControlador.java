package com.miranda.gestao_vendas.controlador;

import com.miranda.gestao_vendas.dto.produto.ProdutoRequestDTO;
import com.miranda.gestao_vendas.dto.produto.ProdutoResponseDTO;
import com.miranda.gestao_vendas.entidades.Produto;
import com.miranda.gestao_vendas.servico.ProdutoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Produto", description = "Gerenciamento de Produtos")
@RestController
@RequestMapping("/produto")
public class ProdutoControlador {

    @Autowired
    private ProdutoServico produtoServico;

    @GetMapping
    @Operation(summary = "Listar")
    public List<ProdutoResponseDTO> listaTodas(@PathVariable Long codigoCategoria) {
        return produtoServico.listarTodos(codigoCategoria).stream()
                .map(ProdutoResponseDTO::converterParaProdutoDTO).collect(Collectors.toList());
    }

    @GetMapping("/{codigo}")
    @Operation(summary = "Listar por código")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long codigoCategoria, @PathVariable Long codigo) {
        Optional<Produto> produto = produtoServico.buscarPorCodigo(codigo, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produto.get())) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Cria um novo produto")
    public ResponseEntity<ProdutoResponseDTO> salvar(@PathVariable Long codigoCategoria, @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        Produto produtoSalvo = produtoServico.salvar(codigoCategoria, produtoRequestDTO.converterParaEntidade(codigoCategoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.converterParaProdutoDTO(produtoSalvo));
    }

    @PutMapping("/{codigoCategoria}/{codigoProduto}")
    @Operation(summary = "Atualizar", description = "Atualiza produto")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long codigoCategoria,
                                             @PathVariable Long codigoProduto,
                                             @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        Produto produtoAtualizado = produtoServico.atualicar(codigoCategoria, codigoProduto,
                produtoRequestDTO.converterParaEntidade(codigoCategoria, codigoProduto));
        return ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produtoAtualizado));
    }

    @DeleteMapping("/{codigoProduto}")
    @Operation(summary = "Deletar", description = "Deletar produto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
        produtoServico.deletar(codigoCategoria, codigoProduto);
    }

}
