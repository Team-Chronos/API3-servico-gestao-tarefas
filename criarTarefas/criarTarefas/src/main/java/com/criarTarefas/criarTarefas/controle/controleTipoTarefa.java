package com.criarTarefas.criarTarefas.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.criarTarefas.criarTarefas.modelo.DTO.tarefaTipoDTO;
import com.criarTarefas.criarTarefas.modelo.TipoTarefa;
import com.criarTarefas.criarTarefas.servico.servicoTipoTarefa;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipoTarefa")
@CrossOrigin(origins = "*")
public class controleTipoTarefa {

    @Autowired
    private servicoTipoTarefa servicoTipoTarefa;

    @PostMapping
    public ResponseEntity<TipoTarefa> criarTipoTarefa(@Valid @RequestBody tarefaTipoDTO dto) {
        TipoTarefa tipoTarefaCriada = servicoTipoTarefa.criarTipoTarefa(dto);
        return ResponseEntity.ok(tipoTarefaCriada);
    }
    @GetMapping
    public ResponseEntity<List<TipoTarefa>> listarTipoTarefas(){
        return ResponseEntity.ok(servicoTipoTarefa.listarTipoTarefas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTarefa> listarTipoTarefasId(@PathVariable Long id){
    return ResponseEntity.ok(servicoTipoTarefa.listarTipoTarefasId(id).stream().findFirst().orElse(null));
    }
}
