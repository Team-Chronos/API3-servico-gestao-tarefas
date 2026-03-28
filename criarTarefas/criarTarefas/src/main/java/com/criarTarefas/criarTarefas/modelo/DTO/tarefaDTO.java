package com.criarTarefas.criarTarefas.modelo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class tarefaDTO {

    @NotBlank(message = "O título da tarefa é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição não pode estar vazia")
    private String descricao;

    private Long responsavelId;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    @NotNull(message = "Tempo máximo é obrigatório")
    private Number tempoMaximoMinutos;

    private Long projetoId;
    
    private Long tipoTarefaId;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(Long responsavelId) {
        this.responsavelId = responsavelId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Number getTempoMaximoMinutos() {
        return tempoMaximoMinutos;
    }

    public void setTempoMaximoMinutos(Number tempoMaximoMinutos) {
        this.tempoMaximoMinutos = tempoMaximoMinutos;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }
    
    public Long getTipoTarefaId() {
        return tipoTarefaId;
    }
    
    public void setTipoTarefaId(Long tipoTarefaId) {
        this.tipoTarefaId = tipoTarefaId;
    }
}