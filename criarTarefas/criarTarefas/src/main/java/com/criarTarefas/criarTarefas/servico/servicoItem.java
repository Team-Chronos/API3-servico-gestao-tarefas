package com.criarTarefas.criarTarefas.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.criarTarefas.criarTarefas.modelo.AuditoriaLog;
import com.criarTarefas.criarTarefas.modelo.DTO.itemDTO;
import com.criarTarefas.criarTarefas.modelo.Item;
import com.criarTarefas.criarTarefas.modelo.Tarefa;
import com.criarTarefas.criarTarefas.repositorio.repositorioItem;

@Service
public class servicoItem {

    private static final String TABELA = "Item";

    @Autowired
    private repositorioItem repositorioItem;

    @Autowired
    private servicoTarefa servicoTarefa;

    @Autowired
    private servicoAuditoria servicoAuditoria;

    public Item criarItem(itemDTO dto) {
        Tarefa tarefa = servicoTarefa.buscarTarefaPorId(dto.getTarefaId());
        Item item = new Item();
        item.setNome(dto.getNome());
        item.setDescricao(dto.getDescricao());
        item.setTarefaId(tarefa.getId());
        return repositorioItem.save(item);
    }

    public Item atualizarItem(Long id, itemDTO dto, Long usuarioAutor) {
        Item item = repositorioItem.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + id));

        // Detecta campos alterados antes de aplicar as mudanças
        List<AuditoriaLog> alteracoes = new ArrayList<>();
        servicoAuditoria.adicionarSeAlterado(alteracoes, servicoAuditoria.compararCampo("nome", item.getNome(), dto.getNome()));
        servicoAuditoria.adicionarSeAlterado(alteracoes, servicoAuditoria.compararCampo("descricao", item.getDescricao(), dto.getDescricao()));

        item.setNome(dto.getNome());
        item.setDescricao(dto.getDescricao());

        Item itemSalvo = repositorioItem.save(item);

        servicoAuditoria.registrarUpdate(usuarioAutor, TABELA, id, alteracoes);

        return itemSalvo;
    }

    public void deletarItem(Long id, Long usuarioAutor) {
        Item item = repositorioItem.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + id));

        String dadosAntes = String.format(
            "{id:%d, nome:'%s', descricao:'%s', tarefaId:%d}",
            item.getIdItem(), item.getNome(), item.getDescricao(), item.getTarefaId()
        );

        repositorioItem.deleteById(id);

        servicoAuditoria.registrarDelete(usuarioAutor, TABELA, id, dadosAntes);
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
