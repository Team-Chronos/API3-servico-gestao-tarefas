package com.criarTarefas.criarTarefas.modelo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaProjetoDTO {
    
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("nome")
    private String nome;
    
    @JsonProperty("codigo")
    private String codigo;
    
    @JsonProperty("tipoProjeto")
    private String tipoProjeto;
    
    @JsonProperty("valorHoraBase")
    private Double valorHoraBase;
    
    @JsonProperty("horasContratadas")
    private Double horasContratadas;
    
    @JsonProperty("valorTotal")
    private Double valorTotal;
    
    @JsonProperty("dataInicio")
    private String dataInicio;
    
    @JsonProperty("dataFim")
    private String dataFim;
    
    @JsonProperty("responsavelId")
    private Integer responsavelId;
}