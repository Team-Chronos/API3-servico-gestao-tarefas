package com.criarTarefas.criarTarefas.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.criarTarefas.criarTarefas.modelo.Item;

public interface repositorioItem extends JpaRepository<Item, Long> {
    List<Item> findByTarefaId(Long tarefaId);

    @Query("SELECT i FROM Item i WHERE i.tarefaId IN :tarefaIds")
    List<Item> findByTarefaIdIn(@Param("tarefaIds") List<Long> tarefaIds);
}