package com.criarTarefas.criarTarefas.servico;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.criarTarefas.criarTarefas.modelo.DTO.disponibilidadeProjetoDTO;
import com.criarTarefas.criarTarefas.modelo.DTO.projetoLimiteQueryDTO;
import com.criarTarefas.criarTarefas.repositorio.repositorioProjetoQuery;
import com.criarTarefas.criarTarefas.repositorio.repositorioTarefa;

@Service
public class servicoDisponibilidadeProjeto {

    @Autowired
    private repositorioTarefa repositorioTarefa;

    @Autowired
    private repositorioProjetoQuery repositorioProjetoQuery;

    public disponibilidadeProjetoDTO obterDisponibilidadeProjeto(Long projetoId) {
        Long totalUsado = repositorioTarefa.somarTempoMaximoMinutosPorProjeto(projetoId);
        long totalUsadoAjustado = totalUsado == null ? 0L : totalUsado;
        long quantidadeTarefas = repositorioTarefa.countByProjetoId(projetoId);

        Optional<projetoLimiteQueryDTO> projetoOpt = repositorioProjetoQuery.buscarProjetoParaValidacao(projetoId);

        if (projetoOpt.isEmpty()) {
            return new disponibilidadeProjetoDTO(
                    projetoId,
                    null,
                    false,
                    quantidadeTarefas,
                    null,
                    null,
                    totalUsadoAjustado,
                    formatarMinutos(totalUsadoAjustado),
                    null,
                    null,
                    null,
                    true
            );
        }

        projetoLimiteQueryDTO projeto = projetoOpt.get();

        if (!ehProjetoHoraFechada(projeto.getTipoProjeto()) || projeto.getHorasContratadas() == null) {
            return new disponibilidadeProjetoDTO(
                    projetoId,
                    projeto.getTipoProjeto(),
                    false,
                    quantidadeTarefas,
                    null,
                    null,
                    totalUsadoAjustado,
                    formatarMinutos(totalUsadoAjustado),
                    null,
                    null,
                    null,
                    true
            );
        }

        long limiteTotalMinutos = converterHorasParaMinutos(projeto.getHorasContratadas());
        long minutosDisponiveis = Math.max(limiteTotalMinutos - totalUsadoAjustado, 0L);

        BigDecimal percentualUsado = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        if (limiteTotalMinutos > 0) {
            percentualUsado = BigDecimal.valueOf(totalUsadoAjustado)
                    .multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(limiteTotalMinutos), 2, RoundingMode.HALF_UP);
        }

        return new disponibilidadeProjetoDTO(
                projetoId,
                projeto.getTipoProjeto(),
                true,
                quantidadeTarefas,
                limiteTotalMinutos,
                formatarMinutos(limiteTotalMinutos),
                totalUsadoAjustado,
                formatarMinutos(totalUsadoAjustado),
                minutosDisponiveis,
                formatarMinutos(minutosDisponiveis),
                percentualUsado,
                minutosDisponiveis > 0
        );
    }

    public String formatarMinutos(long totalMinutos) {
        long horas = totalMinutos / 60;
        long minutos = totalMinutos % 60;
        return String.format("%02d:%02d", horas, minutos);
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