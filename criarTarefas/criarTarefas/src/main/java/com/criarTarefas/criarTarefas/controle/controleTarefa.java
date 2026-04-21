package com.criarTarefas.criarTarefas.controle;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.criarTarefas.criarTarefas.modelo.DTO.disponibilidadeProjetoDTO;

import com.criarTarefas.criarTarefas.modelo.DTO.tarefaDTO;
import com.criarTarefas.criarTarefas.modelo.Tarefa;
import com.criarTarefas.criarTarefas.servico.servicoDisponibilidadeProjeto;
import com.criarTarefas.criarTarefas.servico.servicoTarefa;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class controleTarefa {

    @Autowired
    private servicoTarefa servicoTarefa;

    @Autowired
    private servicoDisponibilidadeProjeto servicoDisponibilidadeProjeto;

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody tarefaDTO dto) {
        Tarefa tarefaCriada = servicoTarefa.criarTarefa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaCriada);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        List<Tarefa> tarefas = servicoTarefa.listarTarefas();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
        Tarefa tarefa = servicoTarefa.buscarTarefaPorId(id);
        return ResponseEntity.ok(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody tarefaDTO dto) {
        Tarefa tarefaAtualizada = servicoTarefa.atualizarTarefa(id, dto);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Tarefa> atualizarStatusTarefa(@PathVariable Long id, @RequestBody String status) {
        Tarefa tarefaAtualizada = servicoTarefa.atualizarStatusTarefa(id, status);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        servicoTarefa.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projeto/{projetoId}")
    public ResponseEntity<List<Tarefa>> listarTarefasPorProjeto(@PathVariable Long projetoId) {
        List<Tarefa> tarefas = servicoTarefa.listarTarefasPorProjeto(projetoId);
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/projeto/{projetoId}/disponibilidade")
    public ResponseEntity<disponibilidadeProjetoDTO> buscarDisponibilidadeProjeto(@PathVariable Long projetoId) {
        disponibilidadeProjetoDTO disponibilidade = servicoDisponibilidadeProjeto.obterDisponibilidadeProjeto(projetoId);
        return ResponseEntity.ok(disponibilidade);
    }

    @GetMapping("/responsavel/{id}")
    public ResponseEntity<List<Tarefa>> listarTarefasPorResponsavel(@PathVariable Long id) {
        List<Tarefa> tarefas = servicoTarefa.listarTarefasPorResponsavel(id);
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/projeto/{projetoId}/responsavel/{id}")
    public ResponseEntity<List<Tarefa>> listarTarefasPorProjetoEResponsavel(
            @PathVariable Long projetoId, 
            @PathVariable Long id) {
        List<Tarefa> tarefas = servicoTarefa.listarTarefasPorProjetoEResponsavel(projetoId, id);
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/responsaveis")
    public ResponseEntity<List<Long>> listarResponsaveisUnicos() {
        List<Tarefa> tarefas = servicoTarefa.listarTarefas();
        List<Long> responsaveis = tarefas.stream()
            .map(Tarefa::getResponsavelId)
            .filter(Objects::nonNull)
            .distinct()
            .toList();
        return ResponseEntity.ok(responsaveis);
    }
}