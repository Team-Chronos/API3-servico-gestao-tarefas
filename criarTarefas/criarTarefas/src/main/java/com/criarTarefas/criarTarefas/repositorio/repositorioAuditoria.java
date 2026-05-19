package com.criarTarefas.criarTarefas.repositorio;

import com.criarTarefas.criarTarefas.modelo.AuditoriaLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface repositorioAuditoria extends JpaRepository<AuditoriaLog, Long> {

    List<AuditoriaLog> findByNomeTabelaModificadaAndEntidadeId(String nomeTabelaModificada, Long entidadeId);

    List<AuditoriaLog> findByUsuarioAutor(Long usuarioAutor);

    List<AuditoriaLog> findByNomeTabelaModificada(String nomeTabelaModificada);
}
