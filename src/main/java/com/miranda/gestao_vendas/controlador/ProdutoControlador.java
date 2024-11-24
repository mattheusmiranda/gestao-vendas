package com.miranda.gestao_vendas.controlador;

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

@Tag(name = "Produto", description = "Gerenciamento de Produtos")
@RestController
@RequestMapping("/produto")
public class ProdutoControlador {

    @Autowired
    private ProdutoServico produtoServico;

    @Operation(summary = "Listar")
    @GetMapping
    public List<Produto> listaTodas(@PathVariable Long codigoCategoria) {
        return produtoServico.listarTodos(codigoCategoria);
    }

    @Operation(summary = "Listar por código")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Produto>> buscarPorId(@PathVariable Long codigoCategoria, @PathVariable Long codigo) {
        Optional<Produto> produto = produtoServico.buscarPorCodigo(codigo, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Salvar", description = "Cria um novo produto")
    @PostMapping
    public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoServico.salvar(produto));
    }

}
