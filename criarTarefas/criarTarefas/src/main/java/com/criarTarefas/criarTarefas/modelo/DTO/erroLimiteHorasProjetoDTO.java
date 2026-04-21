package com.criarTarefas.criarTarefas.modelo.DTO;

public class erroLimiteHorasProjetoDTO {

    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private Long projetoId;
    private Long minutosDisponiveis;
    private String tempoDisponivelFormatado;
    private String tempoUsadoFormatado;
    private String limiteProjetoFormatado;

    public erroLimiteHorasProjetoDTO() {
    }

    public erroLimiteHorasProjetoDTO(
            String timestamp,
            Integer status,
            String error,
            String message,
            Long projetoId,
            Long minutosDisponiveis,
            String tempoDisponivelFormatado,
            String tempoUsadoFormatado,
            String limiteProjetoFormatado) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.projetoId = projetoId;
        this.minutosDisponiveis = minutosDisponiveis;
        this.tempoDisponivelFormatado = tempoDisponivelFormatado;
        this.tempoUsadoFormatado = tempoUsadoFormatado;
        this.limiteProjetoFormatado = limiteProjetoFormatado;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
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

    public String getTempoUsadoFormatado() {
        return tempoUsadoFormatado;
    }

    public void setTempoUsadoFormatado(String tempoUsadoFormatado) {
        this.tempoUsadoFormatado = tempoUsadoFormatado;
    }

    public String getLimiteProjetoFormatado() {
        return limiteProjetoFormatado;
    }

    public void setLimiteProjetoFormatado(String limiteProjetoFormatado) {
        this.limiteProjetoFormatado = limiteProjetoFormatado;
    }
}