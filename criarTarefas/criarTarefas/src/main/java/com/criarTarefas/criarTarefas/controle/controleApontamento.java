package com.criarTarefas.criarTarefas.controle;

import com.criarTarefas.criarTarefas.modelo.DTO.apontamentoDTO;
import com.criarTarefas.criarTarefas.modelo.modeloApontamento;
import org.springframework.beans.factory.annotation.Autowired;
import com.criarTarefas.criarTarefas.servico.servicoApontamento;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;

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