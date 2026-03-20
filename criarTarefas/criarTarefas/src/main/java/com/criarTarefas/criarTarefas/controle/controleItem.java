package com.criarTarefas.criarTarefas.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.criarTarefas.criarTarefas.modelo.DTO.itemDTO;
import com.criarTarefas.criarTarefas.modelo.Item;
import com.criarTarefas.criarTarefas.servico.servicoItem;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class controleItem {

    @Autowired
    private servicoItem servicoItem;

    @PostMapping("/itens")
    public ResponseEntity<Item> criarItem(@RequestBody @Valid itemDTO itemDTO) {
        Item itemCriado = servicoItem.criarItem(itemDTO);
        return new ResponseEntity<>(itemCriado, HttpStatus.CREATED);
    }
}
