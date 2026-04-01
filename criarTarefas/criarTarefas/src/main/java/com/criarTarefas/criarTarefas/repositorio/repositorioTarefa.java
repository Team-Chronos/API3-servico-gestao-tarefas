package com.criarTarefas.criarTarefas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.criarTarefas.criarTarefas.modelo.Tarefa;

public interface repositorioTarefa extends JpaRepository<Tarefa, Long> {
    
    List<Tarefa> findByProjetoId(Long projetoId);
    List<Tarefa> findByResponsavelId(Long responsavelId);
    
    List<Tarefa> findByProjetoIdAndResponsavelId(Long projetoId, Long responsavelId);
    
    @Query(value = "SELECT nome FROM projeto WHERE id = :projetoId", nativeQuery = true)
    String findNomeProjetoById(@Param("projetoId") Long projetoId);
    
    @Query(value = "SELECT nome FROM usuario WHERE id = :usuarioId", nativeQuery = true)
    String findNomeUsuarioById(@Param("usuarioId") Long usuarioId);
}