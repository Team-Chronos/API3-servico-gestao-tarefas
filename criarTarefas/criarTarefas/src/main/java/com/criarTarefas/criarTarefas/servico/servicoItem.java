package com.criarTarefas.criarTarefas.servico;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.criarTarefas.criarTarefas.modelo.DTO.itemDTO;
import com.criarTarefas.criarTarefas.modelo.Item;
import com.criarTarefas.criarTarefas.modelo.Tarefa;
import com.criarTarefas.criarTarefas.repositorio.repositorioItem;

import java.util.List;

@Service
public class servicoItem {

    @Autowired
    private repositorioItem repositorioItem;

    @Autowired
    private servicoTarefa servicoTarefa;

    public Item criarItem(itemDTO dto) {
        Item item = new Item();
        item.setNome(dto.getNome());
        item.setDescricao(dto.getDescricao());
        return repositorioItem.save(item);
    };

    public Item listarItemPorId(Long id){
        Item item = repositorioItem.findById(id)
            .orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + id));

        return item;
    }

    public List<Item> listarItensPorProjeto(Long projetoId) {
        List<Tarefa> tarefas = servicoTarefa.listarTarefasPorProjeto(projetoId);

        List<Item> itens = new ArrayList<>();
        for (Tarefa tarefa : tarefas){
            if (tarefa.getItemId() != null){
                itens.add(listarItemPorId(tarefa.getItemId()));
            }
        }
        return itens;
    }
    
    public List<Item> listarItensPorTarefa(Long tarefaId) {
        Tarefa tarefa = servicoTarefa.buscarTarefaPorId(tarefaId);
        
        List<Item> itens = new ArrayList<>();
        if (tarefa.getItemId() != null) {
            itens.add(listarItemPorId(tarefa.getItemId()));
        }
        return itens;
    }

    public List<Item> listarItensPorProjetoEResponsavel(Long projetoId, Long responsavelId){
        List<Tarefa> tarefas = servicoTarefa.listarTarefasPorProjeto(projetoId)
            .stream()
            .filter(tarefa -> tarefa.getResponsavelId().equals(responsavelId))
            .toList();

        List<Item> itens = new ArrayList<>();
        for (Tarefa tarefa : tarefas){
            if (tarefa.getItemId() != null){
                itens.add(listarItemPorId(tarefa.getItemId()));
            }
        }
        return itens;
    }
    
}