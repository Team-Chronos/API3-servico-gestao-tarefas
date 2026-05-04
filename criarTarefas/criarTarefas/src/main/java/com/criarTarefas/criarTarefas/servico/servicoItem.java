package com.criarTarefas.criarTarefas.servico;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.criarTarefas.criarTarefas.modelo.DTO.itemDTO;
import com.criarTarefas.criarTarefas.modelo.Item;
import com.criarTarefas.criarTarefas.modelo.Tarefa;
import com.criarTarefas.criarTarefas.repositorio.repositorioItem;

@Service
public class servicoItem {

    @Autowired
    private repositorioItem repositorioItem;

    @Autowired
    private servicoTarefa servicoTarefa;

    public Item criarItem(itemDTO dto) {
        Tarefa tarefa = servicoTarefa.buscarTarefaPorId(dto.getTarefaId());
        Item item = new Item();
        item.setNome(dto.getNome());
        item.setDescricao(dto.getDescricao());
        item.setTarefaId(tarefa.getId());
        return repositorioItem.save(item);
    }

    public Item atualizarItem(Long id, itemDTO dto) {
        Item item = repositorioItem.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + id));
        item.setNome(dto.getNome());
        item.setDescricao(dto.getDescricao());
        return repositorioItem.save(item);
    }

    public void deletarItem(Long id) {
        if (!repositorioItem.existsById(id)) {
            throw new RuntimeException("Item não encontrado com ID: " + id);
        }
        repositorioItem.deleteById(id);
    }

    public Item listarItemPorId(Long id) {
        return repositorioItem.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + id));
    }

    public List<Item> listarItensPorTarefa(Long tarefaId) {
        return repositorioItem.findByTarefaId(tarefaId);
    }

    public List<Item> listarItensPorProjeto(Long projetoId) {
        List<Long> tarefaIds = servicoTarefa.listarTarefasPorProjeto(projetoId)
                .stream()
                .map(Tarefa::getId)
                .toList();
        if (tarefaIds.isEmpty()) return List.of();
        return repositorioItem.findByTarefaIdIn(tarefaIds);
    }

    public List<Item> listarItensPorProjetoEResponsavel(Long projetoId, Long responsavelId) {
        List<Long> tarefaIds = servicoTarefa.listarTarefasPorProjeto(projetoId)
                .stream()
                .filter(tarefa -> tarefa.getResponsavelId().equals(responsavelId))
                .map(Tarefa::getId)
                .toList();
        if (tarefaIds.isEmpty()) return List.of();
        return repositorioItem.findByTarefaIdIn(tarefaIds);
    }
}