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
    private Long Id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = true)
    private Long responsavelId;

    @Column(nullable = false)
    private Number tempoMaximoMinutos;

    @Column(nullable = false)
    private String status;

    @Column(nullable = true)
    private Long projetoId;
    
    @Column(nullable = true)
    private Long tipoTarefaId;

    @Column(nullable = true)
    private Long ItemId;


    public Long getId() {
        return Id;
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

    public Long getResponsavel() {
        return responsavelId;
    }

    public void setResponsavel(Long responsavel) {
        this.responsavelId = responsavel;
    }

    public Number getTempoMaximoMinutos() {
        return tempoMaximoMinutos;
    }

    public void setTempoMaximoMinutos(Number tempoMaximoMinutos) {
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

    public Long getItem() {
        return ItemId;
    }

    public void setItem(Long IdItemId){
        this.ItemId = IdItemId;
    }

}