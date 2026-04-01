package com.criarTarefas.criarTarefas.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = true)
    private Long responsavelId;

    @Column(nullable = false)
    private Long tempoMaximoMinutos;

    @Column(nullable = false)
    private String status;

    @Column(nullable = true)
    private Long projetoId;
    
    @Column(nullable = true)
    private Long tipoTarefaId;

    @Column(nullable = true)
    private Long itemId;

    public Long getId() {
        return id;
    }

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

    public Long getTempoMaximoMinutos() {
        return tempoMaximoMinutos;
    }

    public void setTempoMaximoMinutos(Long tempoMaximoMinutos) {
        this.tempoMaximoMinutos = tempoMaximoMinutos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
