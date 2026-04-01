package com.criarTarefas.criarTarefas.modelo.DTO;

import jakarta.validation.constraints.NotBlank;

public class itemDTO {

    @NotBlank(message = "O nome do item é obrigatório")
    private String nome;
    
    @NotBlank(message = "A descrição do item é obrigatória")
    private String descricao;

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
}