package com.criarTarefas.criarTarefas.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.criarTarefas.criarTarefas.modelo.AuditoriaLog;
import com.criarTarefas.criarTarefas.modelo.DTO.tarefaDTO;
import com.criarTarefas.criarTarefas.modelo.Tarefa;
import com.criarTarefas.criarTarefas.repositorio.repositorioTarefa;
import com.criarTarefas.criarTarefas.validacao.validadorLimiteHorasProjeto;

@Service
public class servicoTarefa {

    private static final String TABELA = "Tarefa";

    @Autowired
    private repositorioTarefa repositorioTarefa;

    @Autowired
    private validadorLimiteHorasProjeto validadorLimiteHorasProjeto;

    @Autowired
    private servicoAuditoria servicoAuditoria;

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
    public Tarefa atualizarTarefa(Long id, tarefaDTO dto, Long usuarioAutor) {
        Tarefa tarefa = buscarTarefaPorId(id);

        boolean mudouProjeto = !Objects.equals(tarefa.getProjetoId(), dto.getProjetoId());
        boolean mudouTempo = !Objects.equals(tarefa.getTempoMaximoMinutos(), dto.getTempoMaximoMinutos());

        if (mudouProjeto || mudouTempo) {
            validadorLimiteHorasProjeto.validarAtualizacaoTarefa(id, dto.getProjetoId(), dto.getTempoMaximoMinutos());
        }

        // Detecta campos alterados antes de aplicar as mudanças
        List<AuditoriaLog> alteracoes = new ArrayList<>();
        servicoAuditoria.adicionarSeAlterado(alteracoes, servicoAuditoria.compararCampo("titulo", tarefa.getTitulo(), dto.getTitulo()));
        servicoAuditoria.adicionarSeAlterado(alteracoes, servicoAuditoria.compararCampo("descricao", tarefa.getDescricao(), dto.getDescricao()));
        servicoAuditoria.adicionarSeAlterado(alteracoes, servicoAuditoria.compararCampo("responsavelId", tarefa.getResponsavelId(), dto.getResponsavelId()));
        servicoAuditoria.adicionarSeAlterado(alteracoes, servicoAuditoria.compararCampo("tempoMaximoMinutos", tarefa.getTempoMaximoMinutos(), dto.getTempoMaximoMinutos()));
        servicoAuditoria.adicionarSeAlterado(alteracoes, servicoAuditoria.compararCampo("status", tarefa.getStatus(), dto.getStatus()));
        servicoAuditoria.adicionarSeAlterado(alteracoes, servicoAuditoria.compararCampo("projetoId", tarefa.getProjetoId(), dto.getProjetoId()));
        servicoAuditoria.adicionarSeAlterado(alteracoes, servicoAuditoria.compararCampo("tipoTarefaId", tarefa.getTipoTarefaId(), dto.getTipoTarefaId()));
        servicoAuditoria.adicionarSeAlterado(alteracoes, servicoAuditoria.compararCampo("itemId", tarefa.getItemId(), dto.getItemId()));

        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setResponsavelId(dto.getResponsavelId());
        tarefa.setTempoMaximoMinutos(dto.getTempoMaximoMinutos());
        tarefa.setStatus(dto.getStatus());
        tarefa.setProjetoId(dto.getProjetoId());
        tarefa.setItemId(dto.getItemId());
        tarefa.setTipoTarefaId(dto.getTipoTarefaId());

        Tarefa tarefaSalva = repositorioTarefa.save(tarefa);

        servicoAuditoria.registrarUpdate(usuarioAutor, TABELA, id, alteracoes);

        return tarefaSalva;
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
    public void deletarTarefa(Long id, Long usuarioAutor) {
        Tarefa tarefa = buscarTarefaPorId(id);

        String dadosAntes = String.format(
            "{id:%d, titulo:'%s', status:'%s', projetoId:%d, responsavelId:%d}",
            tarefa.getId(), tarefa.getTitulo(), tarefa.getStatus(),
            tarefa.getProjetoId(), tarefa.getResponsavelId()
        );

        repositorioTarefa.delete(tarefa);

        servicoAuditoria.registrarDelete(usuarioAutor, TABELA, id, dadosAntes);
    }
}
