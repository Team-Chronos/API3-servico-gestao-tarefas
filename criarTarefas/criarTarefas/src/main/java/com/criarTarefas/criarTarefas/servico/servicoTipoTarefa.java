package com.criarTarefas.criarTarefas.servico;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.criarTarefas.criarTarefas.modelo.DTO.tarefaTipoDTO;
import com.criarTarefas.criarTarefas.modelo.TipoTarefa;
import com.criarTarefas.criarTarefas.repositorio.repositorioTipoTarefa;

@Service
public class servicoTipoTarefa {

    @Autowired
    private repositorioTipoTarefa repositorioTipoTarefa;

    @CacheEvict(value = {"tipos-tarefa", "tipo-tarefa"}, allEntries = true)
    public TipoTarefa criarTipoTarefa(tarefaTipoDTO dto) {
        TipoTarefa tipoTarefa = new TipoTarefa();
        tipoTarefa.setNome(dto.getNome());
        return repositorioTipoTarefa.save(tipoTarefa);
    }

    @Cacheable(value = "tipos-tarefa")
    public List<TipoTarefa> listarTipoTarefas() {
        return repositorioTipoTarefa.findAll();
    }

    @Cacheable(value = "tipo-tarefa", key = "#id")
    public List<TipoTarefa> listarTipoTarefasId(Long id) {
        return repositorioTipoTarefa.findById(id).stream().toList();
    }

}
