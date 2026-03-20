package com.criarTarefas.criarTarefas.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.criarTarefas.criarTarefas.modelo.DTO.tarefaDTO;
import com.criarTarefas.criarTarefas.modelo.Tarefa;
import com.criarTarefas.criarTarefas.servico.servicoTarefa;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "http://localhost:5173")
public class controleTarefa {

    @Autowired
    private servicoTarefa servicoTarefa;

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody tarefaDTO dto) {
        Tarefa tarefaCriada = servicoTarefa.criarTarefa(dto);
        return ResponseEntity.ok(tarefaCriada);
        
    }
}
