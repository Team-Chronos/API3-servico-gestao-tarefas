package com.criarTarefas.criarTarefas.controle;

import com.criarTarefas.criarTarefas.modelo.AuditoriaLog;
import com.criarTarefas.criarTarefas.servico.servicoAuditoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditoria")
@CrossOrigin(origins = "*")
public class controleAuditoria {

    @Autowired
    private servicoAuditoria servicoAuditoria;

    @GetMapping
    public ResponseEntity<List<AuditoriaLog>> listarTodos() {
        return ResponseEntity.ok(servicoAuditoria.buscarTodos());
    }

    @GetMapping("/tabela/{nomeTabela}")
    public ResponseEntity<List<AuditoriaLog>> listarPorTabela(@PathVariable String nomeTabela) {
        return ResponseEntity.ok(servicoAuditoria.buscarPorTabela(nomeTabela));
    }

    @GetMapping("/tabela/{nomeTabela}/entidade/{entidadeId}")
    public ResponseEntity<List<AuditoriaLog>> listarPorEntidade(
            @PathVariable String nomeTabela,
            @PathVariable Long entidadeId) {
        return ResponseEntity.ok(servicoAuditoria.buscarPorEntidade(nomeTabela, entidadeId));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AuditoriaLog>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(servicoAuditoria.buscarPorUsuario(usuarioId));
    }
}
