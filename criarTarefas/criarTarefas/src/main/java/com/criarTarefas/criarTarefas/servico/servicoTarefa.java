package com.criarTarefas.criarTarefas.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.criarTarefas.criarTarefas.modelo.DTO.tarefaDTO;
import com.criarTarefas.criarTarefas.modelo.Tarefa;
import com.criarTarefas.criarTarefas.repositorio.repositorioTarefa;

@Service
public class servicoTarefa {

    @Autowired
    private repositorioTarefa repositorioTarefa;
    
    public Tarefa criarTarefa(tarefaDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setResponsavelId(dto.getResponsavelId()); 
        tarefa.setTempoMaximoMinutos(dto.getTempoMaximoMinutos());
        tarefa.setStatus(dto.getStatus());
        tarefa.setProjetoId(dto.getProjetoId());
        tarefa.setItemId(dto.getItemId());
        tarefa.setTipoTarefaId(dto.getTipoTarefaId());
        
        return repositorioTarefa.save(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return repositorioTarefa.findAll();
    }

    public List<Tarefa> listarTarefasPorProjeto(Long projetoId) {
        return repositorioTarefa.findByProjetoId(projetoId);
    }

    public List<Tarefa> listarTarefasPorResponsavel(Long id) {
        return repositorioTarefa.findByResponsavelId(id);
    }
    
    public List<Tarefa> listarTarefasPorProjetoEResponsavel(Long projetoId, Long responsavelId) {
        return repositorioTarefa.findByProjetoIdAndResponsavelId(projetoId, responsavelId);
    }

    public Tarefa buscarTarefaPorId(Long id) {
        return repositorioTarefa.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));
    }

    public Tarefa atualizarTarefa(Long id, tarefaDTO dto) {
        Tarefa tarefa = buscarTarefaPorId(id);
        
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setResponsavelId(dto.getResponsavelId());
        tarefa.setTempoMaximoMinutos(dto.getTempoMaximoMinutos());
        tarefa.setStatus(dto.getStatus());
        tarefa.setProjetoId(dto.getProjetoId());
        tarefa.setTipoTarefaId(dto.getTipoTarefaId());
        
        return repositorioTarefa.save(tarefa);
    }

    public Tarefa atualizarStatusTarefa(Long id, String status) {
        Tarefa tarefa = buscarTarefaPorId(id);
        
        if (status.startsWith("\"") && status.endsWith("\"")) {
            status = status.substring(1, status.length() - 1);
        }
        
        tarefa.setStatus(status);
        return repositorioTarefa.save(tarefa);
    }

    public void deletarTarefa(Long id) {
        Tarefa tarefa = buscarTarefaPorId(id);
        repositorioTarefa.delete(tarefa);
    }
}