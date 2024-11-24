package com.miranda.gestao_vendas.controlador;

import com.miranda.gestao_vendas.entidades.Categoria;
import com.miranda.gestao_vendas.servico.CategoriaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Categoria", description = "Gerenciamento de Categorias")
@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

    @Autowired
    private CategoriaServico categoriaServico;

    @Operation(summary = "Listar", description = "Lista todas as categorias")
    @GetMapping
    public List<Categoria> listaTodas() {
        return categoriaServico.listarTodas();
    }

    @Operation(summary = "Listar por código", description = "Busca uma categoria pelo código")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable Long codigo) {
        Optional<Categoria> categoria = categoriaServico.buscarPorId(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Salvar", description = "Cria uma nova categoria")
    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServico.salvar(categoria));
    }

    @Operation(summary = "Atualizar", description = "Atualiza uma categoria existente")
    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaServico.atualizar(codigo, categoria));
    }

    @Operation(summary = "Deletar", description = "Deleta por código")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        categoriaServico.deletar(codigo);
    }

}
