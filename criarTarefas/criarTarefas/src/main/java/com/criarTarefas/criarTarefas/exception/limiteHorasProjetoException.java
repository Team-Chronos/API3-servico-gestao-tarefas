package com.criarTarefas.criarTarefas.exception;

public class limiteHorasProjetoException extends RuntimeException {

    private final Long projetoId;
    private final Long minutosDisponiveis;
    private final String tempoDisponivelFormatado;
    private final String tempoUsadoFormatado;
    private final String limiteProjetoFormatado;

    public limiteHorasProjetoException(
            String message,
            Long projetoId,
            Long minutosDisponiveis,
            String tempoDisponivelFormatado,
            String tempoUsadoFormatado,
            String limiteProjetoFormatado) {
        super(message);
        this.projetoId = projetoId;
        this.minutosDisponiveis = minutosDisponiveis;
        this.tempoDisponivelFormatado = tempoDisponivelFormatado;
        this.tempoUsadoFormatado = tempoUsadoFormatado;
        this.limiteProjetoFormatado = limiteProjetoFormatado;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public Long getMinutosDisponiveis() {
        return minutosDisponiveis;
    }

    public String getTempoDisponivelFormatado() {
        return tempoDisponivelFormatado;
    }

    public String getTempoUsadoFormatado() {
        return tempoUsadoFormatado;
    }

    public String getLimiteProjetoFormatado() {
        return limiteProjetoFormatado;
    }
}