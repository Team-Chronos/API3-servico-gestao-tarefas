package com.criarTarefas.criarTarefas.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        Item itemCriado = servicoItem.criarItem(itemDTO);
        return new ResponseEntity<>(itemCriado, HttpStatus.CREATED);
    }

    @GetMapping("/itens/projeto/{projetoId}/responsavel/{responsavelId}")
    public ResponseEntity<List<Item>> listarItensPorProjetoEResponsavvel(@PathVariable Long projetoId, @PathVariable Long responsavelId) {
        List<Item> itens = servicoItem.listarItensPorProjetoEResponsavel(projetoId, responsavelId);
        return ResponseEntity.ok(itens);
    }
    
    @GetMapping("/itens/projeto/{projetoId}")
    public ResponseEntity<List<Item>> listarItensPorProjeto(@PathVariable Long projetoId) {
        List<Item> itens = servicoItem.listarItensPorProjeto(projetoId);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/itens/tarefa/{tarefaId}")
    public ResponseEntity<List<Item>> listarItensPorTarefa(@PathVariable Long tarefaId) {
        List<Item> itens = servicoItem.listarItensPorTarefa(tarefaId);
        return ResponseEntity.ok(itens);
    }

}