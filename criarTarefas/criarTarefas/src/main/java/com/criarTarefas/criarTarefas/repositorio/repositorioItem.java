package com.criarTarefas.criarTarefas.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import com.criarTarefas.criarTarefas.modelo.Item;

public interface repositorioItem extends JpaRepository<Item, Long> {
}
