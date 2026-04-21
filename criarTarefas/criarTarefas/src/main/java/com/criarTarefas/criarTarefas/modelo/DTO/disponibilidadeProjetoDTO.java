package com.criarTarefas.criarTarefas.modelo.DTO;

import java.math.BigDecimal;

public class disponibilidadeProjetoDTO {

    private Long projetoId;
    private String tipoProjeto;
    private Boolean possuiLimiteHoras;
    private Long quantidadeTarefas;
    private Long limiteTotalMinutos;
    private String limiteTotalFormatado;
    private Long totalUsadoMinutos;
    private String totalUsadoFormatado;
    private Long minutosDisponiveis;
    private String tempoDisponivelFormatado;
    private BigDecimal percentualUsado;
    private Boolean podeCriarTarefa;

    public disponibilidadeProjetoDTO() {
    }

    public disponibilidadeProjetoDTO(
            Long projetoId,
            String tipoProjeto,
            Boolean possuiLimiteHoras,
            Long quantidadeTarefas,
            Long limiteTotalMinutos,
            String limiteTotalFormatado,
            Long totalUsadoMinutos,
            String totalUsadoFormatado,
            Long minutosDisponiveis,
            String tempoDisponivelFormatado,
            BigDecimal percentualUsado,
            Boolean podeCriarTarefa) {
        this.projetoId = projetoId;
        this.tipoProjeto = tipoProjeto;
        this.possuiLimiteHoras = possuiLimiteHoras;
        this.quantidadeTarefas = quantidadeTarefas;
        this.limiteTotalMinutos = limiteTotalMinutos;
        this.limiteTotalFormatado = limiteTotalFormatado;
        this.totalUsadoMinutos = totalUsadoMinutos;
        this.totalUsadoFormatado = totalUsadoFormatado;
        this.minutosDisponiveis = minutosDisponiveis;
        this.tempoDisponivelFormatado = tempoDisponivelFormatado;
        this.percentualUsado = percentualUsado;
        this.podeCriarTarefa = podeCriarTarefa;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

    public String getTipoProjeto() {
        return tipoProjeto;
    }

    public void setTipoProjeto(String tipoProjeto) {
        this.tipoProjeto = tipoProjeto;
    }

    public Boolean getPossuiLimiteHoras() {
        return possuiLimiteHoras;
    }

    public void setPossuiLimiteHoras(Boolean possuiLimiteHoras) {
        this.possuiLimiteHoras = possuiLimiteHoras;
    }

    public Long getQuantidadeTarefas() {
        return quantidadeTarefas;
    }

    public void setQuantidadeTarefas(Long quantidadeTarefas) {
        this.quantidadeTarefas = quantidadeTarefas;
    }

    public Long getLimiteTotalMinutos() {
        return limiteTotalMinutos;
    }

    public void setLimiteTotalMinutos(Long limiteTotalMinutos) {
        this.limiteTotalMinutos = limiteTotalMinutos;
    }

    public String getLimiteTotalFormatado() {
        return limiteTotalFormatado;
    }

    public void setLimiteTotalFormatado(String limiteTotalFormatado) {
        this.limiteTotalFormatado = limiteTotalFormatado;
    }

    public Long getTotalUsadoMinutos() {
        return totalUsadoMinutos;
    }

    public void setTotalUsadoMinutos(Long totalUsadoMinutos) {
        this.totalUsadoMinutos = totalUsadoMinutos;
    }

    public String getTotalUsadoFormatado() {
        return totalUsadoFormatado;
    }

    public void setTotalUsadoFormatado(String totalUsadoFormatado) {
        this.totalUsadoFormatado = totalUsadoFormatado;
    }

    public Long getMinutosDisponiveis() {
        return minutosDisponiveis;
    }

    public void setMinutosDisponiveis(Long minutosDisponiveis) {
        this.minutosDisponiveis = minutosDisponiveis;
    }

    public String getTempoDisponivelFormatado() {
        return tempoDisponivelFormatado;
    }

    public void setTempoDisponivelFormatado(String tempoDisponivelFormatado) {
        this.tempoDisponivelFormatado = tempoDisponivelFormatado;
    }

    public BigDecimal getPercentualUsado() {
        return percentualUsado;
    }

    public void setPercentualUsado(BigDecimal percentualUsado) {
        this.percentualUsado = percentualUsado;
    }

    public Boolean getPodeCriarTarefa() {
        return podeCriarTarefa;
    }

    public void setPodeCriarTarefa(Boolean podeCriarTarefa) {
        this.podeCriarTarefa = podeCriarTarefa;
    }
}