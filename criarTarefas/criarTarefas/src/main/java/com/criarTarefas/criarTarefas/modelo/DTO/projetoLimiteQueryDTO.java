package com.criarTarefas.criarTarefas.modelo.DTO;

import java.math.BigDecimal;

public class projetoLimiteQueryDTO {

    private Long projetoId;
    private String tipoProjeto;
    private BigDecimal horasContratadas;

    public projetoLimiteQueryDTO() {
    }

    public projetoLimiteQueryDTO(Long projetoId, String tipoProjeto, BigDecimal horasContratadas) {
        this.projetoId = projetoId;
        this.tipoProjeto = tipoProjeto;
        this.horasContratadas = horasContratadas;
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

    public BigDecimal getHorasContratadas() {
        return horasContratadas;
    }

    public void setHorasContratadas(BigDecimal horasContratadas) {
        this.horasContratadas = horasContratadas;
    }
}