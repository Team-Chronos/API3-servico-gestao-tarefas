package com.criarTarefas.criarTarefas.controle;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.criarTarefas.criarTarefas.modelo.DTO.tarefaTipoDTO;
import com.criarTarefas.criarTarefas.modelo.TipoTarefa;
import com.criarTarefas.criarTarefas.servico.servicoTipoTarefa;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipoTarefa")
@CrossOrigin(origins = "http://localhost:5173")
public class controleTipoTarefa {

    @Autowired
    private servicoTipoTarefa servicoTipoTarefa;

    @PostMapping
    public ResponseEntity<TipoTarefa> criarTipoTarefa(@Valid @RequestBody tarefaTipoDTO dto) {
        TipoTarefa tipoTarefaCriada = servicoTipoTarefa.criarTipoTarefa(dto);
        return ResponseEntity.ok(tipoTarefaCriada);
    }
}
