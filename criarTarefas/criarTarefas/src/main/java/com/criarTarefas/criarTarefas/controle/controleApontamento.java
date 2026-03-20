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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.criarTarefas.criarTarefas.modelo.DTO.apontamentoDTO;
import com.criarTarefas.criarTarefas.modelo.modeloApontamento;
import com.criarTarefas.criarTarefas.servico.servicoApontamento;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/apontamentos")
@CrossOrigin(origins = "http://localhost:5173")
public class controleApontamento {
    
    @Autowired
    private servicoApontamento servicoApontamento;
    
    @PostMapping
    public ResponseEntity<modeloApontamento> criarApontamento(@RequestBody @Valid apontamentoDTO dto) {
        modeloApontamento apontamento = servicoApontamento.criarApontamento(dto);
        return new ResponseEntity<>(apontamento, HttpStatus.CREATED);
    }
    
    @GetMapping("/tarefa/{tarefaId}")
    public ResponseEntity<List<modeloApontamento>> listarPorTarefa(@PathVariable Long tarefaId) {
        return ResponseEntity.ok(servicoApontamento.buscarPorTarefa(tarefaId));
    }
}