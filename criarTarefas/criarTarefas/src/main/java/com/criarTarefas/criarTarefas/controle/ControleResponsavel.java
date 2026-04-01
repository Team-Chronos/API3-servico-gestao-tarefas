package com.criarTarefas.criarTarefas.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/responsaveis")
@CrossOrigin(origins = "http://localhost:5173")
public class ControleResponsavel {

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${usuarios.api.url:http://localhost:8089}")
    private String usuariosApiUrl;

    @GetMapping
    public ResponseEntity<?> listarResponsaveis() {
        try {
            String url = usuariosApiUrl + "/responsaveis";
            System.out.println("Buscando responsáveis em: " + url);
            
            ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);
            
            if (response.getBody() != null) {
                List<Object> responsaveis = Arrays.asList(response.getBody());
                return ResponseEntity.ok(responsaveis);
            }
            return ResponseEntity.ok().body("[]");
            
        } catch (Exception e) {
            System.err.println("Erro ao buscar responsáveis: " + e.getMessage());
            return ResponseEntity.status(503)
                    .body("Erro ao buscar responsáveis: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarResponsavelPorId(@PathVariable Integer id) {
        try {
            String url = usuariosApiUrl + "/responsaveis/" + id;
            System.out.println("Buscando responsável em: " + url);
            
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
            return ResponseEntity.ok(response.getBody());
            
        } catch (Exception e) {
            System.err.println("Erro ao buscar responsável: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}