package com.criarTarefas.criarTarefas.controle;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.criarTarefas.criarTarefas.modelo.DTO.itemDTO;
import com.criarTarefas.criarTarefas.modelo.Item;
import com.criarTarefas.criarTarefas.servico.servicoItem;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class controleItem {

    @Autowired
    private servicoItem servicoItem;

    @PostMapping("/itens")
    public ResponseEntity<Item> criarItem(@RequestBody @Valid itemDTO itemDTO) {
        return new ResponseEntity<>(servicoItem.criarItem(itemDTO), HttpStatus.CREATED);
    }

    @PutMapping("/itens/{id}")
    public ResponseEntity<Item> atualizarItem(@PathVariable Long id, @RequestBody @Valid itemDTO itemDTO) {
        return ResponseEntity.ok(servicoItem.atualizarItem(id, itemDTO));
    }

    @DeleteMapping("/itens/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        servicoItem.deletarItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/itens/tarefa/{tarefaId}")
    public ResponseEntity<List<Item>> listarItensPorTarefa(@PathVariable Long tarefaId) {
        return ResponseEntity.ok(servicoItem.listarItensPorTarefa(tarefaId));
    }

    @GetMapping("/itens/projeto/{projetoId}")
    public ResponseEntity<List<Item>> listarItensPorProjeto(@PathVariable Long projetoId) {
        return ResponseEntity.ok(servicoItem.listarItensPorProjeto(projetoId));
    }

    @GetMapping("/itens/projeto/{projetoId}/responsavel/{responsavelId}")
    public ResponseEntity<List<Item>> listarItensPorProjetoEResponsavel(
            @PathVariable Long projetoId, @PathVariable Long responsavelId) {
        return ResponseEntity.ok(servicoItem.listarItensPorProjetoEResponsavel(projetoId, responsavelId));
    }
}