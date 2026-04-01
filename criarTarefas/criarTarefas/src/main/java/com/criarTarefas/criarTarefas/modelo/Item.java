package com.criarTarefas.criarTarefas.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ItemId;
    
    private Long tarefaId;

    private String nome;
    private String descricao;

    public Long getIdItem() {
        return ItemId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Long getTarefaId() {
        return tarefaId;
    }
    
    public void setTarefaId(Long tarefaId) {
        this.tarefaId = tarefaId;
    }
}