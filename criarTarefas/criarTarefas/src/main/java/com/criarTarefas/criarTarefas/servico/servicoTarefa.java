package com.criarTarefas.criarTarefas.servico;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.criarTarefas.criarTarefas.modelo.DTO.tarefaDTO;
import com.criarTarefas.criarTarefas.modelo.Tarefa;
import com.criarTarefas.criarTarefas.repositorio.repositorioTarefa;
import com.criarTarefas.criarTarefas.validacao.validadorLimiteHorasProjeto;

@Service
public class servicoTarefa {

    @Autowired
    private repositorioTarefa repositorioTarefa;

    @Autowired

    private validadorLimiteHorasProjeto validadorLimiteHorasProjeto;

    @CacheEvict(value = { "tarefas", "tarefa", "tarefas-projeto", "tarefas-responsavel",
            "tarefas-projeto-responsavel" }, allEntries = true)
    public Tarefa criarTarefa(tarefaDTO dto) {
        validadorLimiteHorasProjeto.validarCriacaoTarefa(dto.getProjetoId(), dto.getTempoMaximoMinutos());

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

    @Cacheable(value = "tarefas")
    public List<Tarefa> listarTarefas() {
        return repositorioTarefa.findAll();
    }

    @Cacheable(value = "tarefas-projeto", key = "#projetoId")
    public List<Tarefa> listarTarefasPorProjeto(Long projetoId) {
        return repositorioTarefa.findByProjetoId(projetoId);
    }

    @Cacheable(value = "tarefas-responsavel", key = "#id")
    public List<Tarefa> listarTarefasPorResponsavel(Long id) {
        return repositorioTarefa.findByResponsavelId(id);
    }

    @Cacheable(value = "tarefas-projeto-responsavel", key = "#projetoId + '-' + #responsavelId")
    public List<Tarefa> listarTarefasPorProjetoEResponsavel(Long projetoId, Long responsavelId) {
        return repositorioTarefa.findByProjetoIdAndResponsavelId(projetoId, responsavelId);
    }

    @Cacheable(value = "tarefa", key = "#id")
    public Tarefa buscarTarefaPorId(Long id) {
        return repositorioTarefa.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));
    }

    @CacheEvict(value = { "tarefas", "tarefa", "tarefas-projeto", "tarefas-responsavel",
            "tarefas-projeto-responsavel" }, allEntries = true)
    public Tarefa atualizarTarefa(Long id, tarefaDTO dto) {
        Tarefa tarefa = buscarTarefaPorId(id);

        boolean mudouProjeto = !Objects.equals(tarefa.getProjetoId(), dto.getProjetoId());
        boolean mudouTempo = !Objects.equals(tarefa.getTempoMaximoMinutos(), dto.getTempoMaximoMinutos());

        if (mudouProjeto || mudouTempo) {
            validadorLimiteHorasProjeto.validarAtualizacaoTarefa(id, dto.getProjetoId(), dto.getTempoMaximoMinutos());
        }

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

    @CacheEvict(value = { "tarefas", "tarefa", "tarefas-projeto", "tarefas-responsavel",
            "tarefas-projeto-responsavel" }, allEntries = true)
    public Tarefa atualizarStatusTarefa(Long id, String status) {
        Tarefa tarefa = buscarTarefaPorId(id);
        if (status.startsWith("\"") && status.endsWith("\"")) {
            status = status.substring(1, status.length() - 1);
        }
        tarefa.setStatus(status);
        return repositorioTarefa.save(tarefa);
    }

    @CacheEvict(value = { "tarefas", "tarefa", "tarefas-projeto", "tarefas-responsavel",
            "tarefas-projeto-responsavel" }, allEntries = true)
    public void deletarTarefa(Long id) {
        Tarefa tarefa = buscarTarefaPorId(id);
        repositorioTarefa.delete(tarefa);
    }
}
