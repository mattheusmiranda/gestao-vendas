package com.miranda.gestao_vendas.controlador;

import com.miranda.gestao_vendas.dto.categoria.CategoriaRequestDTO;
import com.miranda.gestao_vendas.dto.categoria.CategoriaResponseDTO;
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
import java.util.stream.Collectors;

@Tag(name = "Categoria", description = "Gerenciamento de Categorias")
@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

    @Autowired
    private CategoriaServico categoriaServico;

    @Operation(summary = "Listar", description = "Lista todas as categorias")
    @GetMapping
    public List<CategoriaResponseDTO> listaTodas() {
        return categoriaServico.listarTodas().stream()
                .map(CategoriaResponseDTO::conveterParaCategoriaDTO).collect(Collectors.toList());
    }

    @Operation(summary = "Listar por código", description = "Busca uma categoria pelo código")
    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long codigo) {
        Optional<Categoria> categoria = categoriaServico.buscarPorId(codigo);
        return categoria.isPresent()
                ? ResponseEntity.ok(CategoriaResponseDTO.conveterParaCategoriaDTO(categoria.get()))
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Salvar", description = "Cria uma nova categoria")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoriaSalva = categoriaServico.salvar(categoriaRequestDTO.coverteParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.conveterParaCategoriaDTO(categoriaSalva));
    }

    @Operation(summary = "Atualizar", description = "Atualiza uma categoria existente")
    @PutMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoriAtualizada = categoriaServico.atualizar(codigo, categoriaRequestDTO.coverteParaEntidade(codigo));
        return ResponseEntity.ok(CategoriaResponseDTO.conveterParaCategoriaDTO(categoriAtualizada));
    }

    @Operation(summary = "Deletar", description = "Deleta por código")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        categoriaServico.deletar(codigo);
    }

}
