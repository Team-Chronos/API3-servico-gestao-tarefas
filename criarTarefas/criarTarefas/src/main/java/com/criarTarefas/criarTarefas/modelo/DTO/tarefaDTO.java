package com.criarTarefas.criarTarefas.modelo.DTO;

import jakarta.validation.constraints.NotBlank;

public class tarefaDTO {

    @NotBlank(message = "O título da tarefa é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição não pode estar vazia")
    private String descricao;

    private Long responsavelId;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    private Long tempoMaximoMinutos;

    private Long projetoId;
    
    private Long tipoTarefaId;

    private Long itemId;

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

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemId() {
        return itemId;
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

    public Long getTempoMaximoMinutos() {
        return tempoMaximoMinutos;
    }

    public void setTempoMaximoMinutos(Long tempoMaximoMinutos) {
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