package com.criarTarefas.criarTarefas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.criarTarefas.criarTarefas.modelo.Tarefa;

import java.util.List;

public interface repositorioTarefa extends JpaRepository<Tarefa, Long> {
    
    List<Tarefa> findByProjetoId(Long projetoId);
}
