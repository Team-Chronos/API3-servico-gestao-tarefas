package com.criarTarefas.criarTarefas.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.criarTarefas.criarTarefas.modelo.DTO.ResumoHorasProjetoDTO;
import com.criarTarefas.criarTarefas.repositorio.repositorioTarefa;

@Service
public class servicoHorasProjeto {

    @Autowired
    private repositorioTarefa repositorioTarefa;

    public ResumoHorasProjetoDTO calcularResumoHorasProjeto(Long projetoId) {
        Long totalMinutos = repositorioTarefa.somarTempoMaximoMinutosPorProjeto(projetoId);
        long quantidadeTarefas = repositorioTarefa.countByProjetoId(projetoId);

        long totalMinutosAjustado = totalMinutos == null ? 0L : totalMinutos;

        long horas = totalMinutosAjustado / 60;
        long minutos = totalMinutosAjustado % 60;
        String horasTotaisFormatadas = String.format("%02d:%02d", horas, minutos);

        return new ResumoHorasProjetoDTO(
                projetoId,
                quantidadeTarefas,
                totalMinutosAjustado,
                horasTotaisFormatadas
        );
    }
}