package com.criarTarefas.criarTarefas.modelo.DTO;

public class ResumoHorasProjetoDTO {

    private Long projetoId;
    private Long quantidadeTarefas;
    private Long totalMinutosEstimados;
    private String horasTotaisFormatadas;

    public ResumoHorasProjetoDTO() {
    }

    public ResumoHorasProjetoDTO(Long projetoId, Long quantidadeTarefas, Long totalMinutosEstimados, String horasTotaisFormatadas) {
        this.projetoId = projetoId;
        this.quantidadeTarefas = quantidadeTarefas;
        this.totalMinutosEstimados = totalMinutosEstimados;
        this.horasTotaisFormatadas = horasTotaisFormatadas;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

    public Long getQuantidadeTarefas() {
        return quantidadeTarefas;
    }

    public void setQuantidadeTarefas(Long quantidadeTarefas) {
        this.quantidadeTarefas = quantidadeTarefas;
    }

    public Long getTotalMinutosEstimados() {
        return totalMinutosEstimados;
    }

    public void setTotalMinutosEstimados(Long totalMinutosEstimados) {
        this.totalMinutosEstimados = totalMinutosEstimados;
    }

    public String getHorasTotaisFormatadas() {
        return horasTotaisFormatadas;
    }

    public void setHorasTotaisFormatadas(String horasTotaisFormatadas) {
        this.horasTotaisFormatadas = horasTotaisFormatadas;
    }
}