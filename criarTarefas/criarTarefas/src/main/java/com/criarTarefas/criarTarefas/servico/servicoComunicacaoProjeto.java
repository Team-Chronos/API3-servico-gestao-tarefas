package com.criarTarefas.criarTarefas.servico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.criarTarefas.criarTarefas.modelo.DTO.ConsultaProjetoDTO;

@RestController
@RequestMapping("/api/projeto")
@CrossOrigin(origins = "*")
public class servicoComunicacaoProjeto {

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${projeto.api.url:http://localhost:8084/projetos}")
    private String apiUrl;

    @GetMapping("/todos")
    public ResponseEntity<?> listarTodosProjetos() {
        
        
        try {
            String url = apiUrl;
            System.out.println("URL: " + url);
            
            ResponseEntity<ConsultaProjetoDTO[]> response = restTemplate.getForEntity(url, ConsultaProjetoDTO[].class);
            ConsultaProjetoDTO[] projetos = response.getBody();
            
            if (projetos != null) {
                return ResponseEntity.ok(Arrays.asList(projetos));
            } else {
                return ResponseEntity.ok().body("[]");
            }
            
        } catch (RestClientException e) {
            System.err.println("Erro ao listar projetos: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Erro ao consultar API: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> comunicarProjeto(@PathVariable("id") Integer id) {
        
        
        try {
            String url = apiUrl + "/" + id;
            System.out.println("URL: " + url);
            
            ConsultaProjetoDTO projeto = restTemplate.getForObject(url, ConsultaProjetoDTO.class);
            
            if (projeto != null) {
                return ResponseEntity.ok(projeto);
            } else {
                return ResponseEntity.notFound().build();
            }
            
        } catch (RestClientException e) {
            System.err.println("Erro ao consultar projeto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Erro: " + e.getMessage());
        }
    }
    
    @GetMapping("/responsaveis")
    public ResponseEntity<?> listarResponsaveisDosProjetos() {
        
        try {
            String url = apiUrl;
            System.out.println("URL: " + url);
            
            ConsultaProjetoDTO[] projetos = restTemplate.getForEntity(url, ConsultaProjetoDTO[].class).getBody();
            
            if (projetos != null && projetos.length > 0) {
                Map<Integer, Map<String, Object>> responsaveisMap = new java.util.LinkedHashMap<>();
                
                for (ConsultaProjetoDTO projeto : projetos) {
                    if (projeto.getResponsavelId() != null) {
                        Integer id = projeto.getResponsavelId();
                        
                        if (!responsaveisMap.containsKey(id)) {
                            Map<String, Object> resp = new HashMap<>();
                            resp.put("id", id);
                            resp.put("nome", "Responsável ID: " + id);
                            responsaveisMap.put(id, resp);
                        }
                    }
                }
                
                List<Map<String, Object>> responsaveis = new ArrayList<>(responsaveisMap.values());
                System.out.println("Encontrados " + responsaveis.size() + " responsáveis únicos");
                return ResponseEntity.ok(responsaveis);
            }
            
            return ResponseEntity.ok().body("[]");
            
        } catch (RestClientException e) {
            System.err.println("Erro ao listar responsáveis: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Erro ao consultar API: " + e.getMessage());
        }
    }
    
    @GetMapping("/teste")
    public ResponseEntity<String> testarConexao() {
        try {
            String url = apiUrl;
            ResponseEntity<String> resposta = restTemplate.getForEntity(url, String.class);
            return ResponseEntity.ok("Conexão OK! Status: " + resposta.getStatusCode());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Erro de conexão: " + e.getMessage());
        }
    }
}