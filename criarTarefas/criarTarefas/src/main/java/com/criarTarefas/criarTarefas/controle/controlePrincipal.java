package com.criarTarefas.criarTarefas.controle;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class controlePrincipal {

    @GetMapping("/")
    public String principal(){
        return "Grimmjow Jaegerjaquez";
    }
    
}
