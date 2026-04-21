package com.criarTarefas.criarTarefas.validacao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.criarTarefas.criarTarefas.exception.limiteHorasProjetoException;
import com.criarTarefas.criarTarefas.modelo.DTO.projetoLimiteQueryDTO;
import com.criarTarefas.criarTarefas.repositorio.repositorioProjetoQuery;
import com.criarTarefas.criarTarefas.repositorio.repositorioTarefa;
import com.criarTarefas.criarTarefas.servico.servicoDisponibilidadeProjeto;

@Component
public class validadorLimiteHorasProjeto {

    @Autowired
    private repositorioProjetoQuery repositorioProjetoQuery;

    @Autowired
    private repositorioTarefa repositorioTarefa;

    @Autowired
    private servicoDisponibilidadeProjeto servicoDisponibilidadeProjeto;

    public void validarCriacaoTarefa(Long projetoId, Long tempoMaximoMinutos) {
        if (projetoId == null || tempoMaximoMinutos == null) {
            return;
        }

        Optional<projetoLimiteQueryDTO> projetoOpt = repositorioProjetoQuery.buscarProjetoParaValidacao(projetoId);
        if (projetoOpt.isEmpty()) {
            return;
        }

        projetoLimiteQueryDTO projeto = projetoOpt.get();
        if (!ehProjetoHoraFechada(projeto.getTipoProjeto()) || projeto.getHorasContratadas() == null) {
            return;
        }

        long limiteTotalMinutos = converterHorasParaMinutos(projeto.getHorasContratadas());
        Long totalUsado = repositorioTarefa.somarTempoMaximoMinutosPorProjeto(projetoId);
        long totalUsadoAjustado = totalUsado == null ? 0L : totalUsado;

        if (totalUsadoAjustado + tempoMaximoMinutos > limiteTotalMinutos) {
            long minutosDisponiveis = Math.max(limiteTotalMinutos - totalUsadoAjustado, 0L);

            throw new limiteHorasProjetoException(
                    "O tempo da tarefa ultrapassa o limite disponível do projeto.",
                    projetoId,
                    minutosDisponiveis,
                    servicoDisponibilidadeProjeto.formatarMinutos(minutosDisponiveis),
                    servicoDisponibilidadeProjeto.formatarMinutos(totalUsadoAjustado),
                    servicoDisponibilidadeProjeto.formatarMinutos(limiteTotalMinutos)
            );
        }
    }

    public void validarAtualizacaoTarefa(Long tarefaId, Long projetoId, Long tempoMaximoMinutos) {
        if (tarefaId == null || projetoId == null || tempoMaximoMinutos == null) {
            return;
        }

        Optional<projetoLimiteQueryDTO> projetoOpt = repositorioProjetoQuery.buscarProjetoParaValidacao(projetoId);
        if (projetoOpt.isEmpty()) {
            return;
        }

        projetoLimiteQueryDTO projeto = projetoOpt.get();
        if (!ehProjetoHoraFechada(projeto.getTipoProjeto()) || projeto.getHorasContratadas() == null) {
            return;
        }

        long limiteTotalMinutos = converterHorasParaMinutos(projeto.getHorasContratadas());
        Long totalUsado = repositorioTarefa.somarTempoMaximoMinutosPorProjetoExcetoTarefa(projetoId, tarefaId);
        long totalUsadoAjustado = totalUsado == null ? 0L : totalUsado;

        if (totalUsadoAjustado + tempoMaximoMinutos > limiteTotalMinutos) {
            long minutosDisponiveis = Math.max(limiteTotalMinutos - totalUsadoAjustado, 0L);

            throw new limiteHorasProjetoException(
                    "O tempo da tarefa ultrapassa o limite disponível do projeto.",
                    projetoId,
                    minutosDisponiveis,
                    servicoDisponibilidadeProjeto.formatarMinutos(minutosDisponiveis),
                    servicoDisponibilidadeProjeto.formatarMinutos(totalUsadoAjustado),
                    servicoDisponibilidadeProjeto.formatarMinutos(limiteTotalMinutos)
            );
        }
    }

    private boolean ehProjetoHoraFechada(String tipoProjeto) {
        if (tipoProjeto == null || tipoProjeto.isBlank()) {
            return false;
        }

        String normalizado = tipoProjeto.trim().replace("-", "_").toUpperCase();
        return "HORA_FECHADA".equals(normalizado);
    }

    private long converterHorasParaMinutos(BigDecimal horasContratadas) {
        return horasContratadas
                .multiply(BigDecimal.valueOf(60))
                .setScale(0, RoundingMode.HALF_UP)
                .longValue();
    }
}