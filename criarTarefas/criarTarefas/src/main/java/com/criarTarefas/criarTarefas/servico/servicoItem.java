package com.criarTarefas.criarTarefas.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.criarTarefas.criarTarefas.modelo.DTO.itemDTO;
import com.criarTarefas.criarTarefas.modelo.Item;
import com.criarTarefas.criarTarefas.repositorio.repositorioItem;

@Service
public class servicoItem {

    @Autowired
    private repositorioItem repositorioItem;

    public Item criarItem(itemDTO dto) {
        Item item = new Item();
        item.setNome(dto.getNome());
        item.setDescricao(dto.getDescricao());
        item.setTarefaId(dto.getTarefaId());
        return repositorioItem.save(item);
    }

    public Item listarItensPorTarefa(Long tarefaId) {
        return repositorioItem.findByTarefaId(tarefaId);
    }
}