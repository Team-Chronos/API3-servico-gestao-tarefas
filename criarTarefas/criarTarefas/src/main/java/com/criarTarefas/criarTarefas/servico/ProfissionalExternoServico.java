package com.criarTarefas.criarTarefas.servico;

import com.criarTarefas.criarTarefas.modelo.DTO.ProfissionalNomeDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfissionalExternoServico {

    private final RestTemplate restTemplate;

    public ProfissionalExternoServico() {
        this.restTemplate = new RestTemplate();
    }

    public List<ProfissionalNomeDTO> listarNomesProfissionais() {
        String url = "http://localhost:8081/api/profissionais";

        try {
            ResponseEntity<List<ProfissionalResponse>> resposta = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProfissionalResponse>>() {}
            );

            List<ProfissionalResponse> profissionais = resposta.getBody();
            
            if (profissionais != null && !profissionais.isEmpty()) {
                return profissionais.stream()
                        .map(p -> new ProfissionalNomeDTO(p.getId(), p.getNome()))
                        .collect(Collectors.toList());
            }
            
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Erro ao consultar microsserviço: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    private static class ProfissionalResponse {
        private int id;
        private String nome;
        
        public int getId() {
            return id;
        }
        
        /*public void setId(int id) {
            this.id = id;
        }*/
        
        public String getNome() {
            return nome;
        }
        
        /*public void setNome(String nome) {
            this.nome = nome;
        }*/
    }
}