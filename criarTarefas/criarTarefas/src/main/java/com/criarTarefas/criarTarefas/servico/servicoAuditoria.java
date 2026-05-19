package com.criarTarefas.criarTarefas.servico;

import com.criarTarefas.criarTarefas.modelo.AuditoriaLog;
import com.criarTarefas.criarTarefas.repositorio.repositorioAuditoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class servicoAuditoria {

    @Autowired
    private repositorioAuditoria repositorioAuditoria;

    public void registrarDelete(Long usuarioAutor, String nomeTabela, Long entidadeId, String dadosAntesJson) {
        AuditoriaLog log = new AuditoriaLog();
        log.setUsuarioAutor(usuarioAutor);
        log.setNomeTabelaModificada(nomeTabela);
        log.setEntidadeId(entidadeId);
        log.setCampoAlterado("*");
        log.setValorAnterior(dadosAntesJson);
        log.setNovoValor(null);
        log.setTipoOperacao("DELETE");
        log.setDataAlteracao(LocalDateTime.now());
        repositorioAuditoria.save(log);
    }

    public void registrarUpdate(Long usuarioAutor, String nomeTabela, Long entidadeId,
            List<AuditoriaLog> logsAlteracoes) {
        if (logsAlteracoes == null || logsAlteracoes.isEmpty())
            return;

        LocalDateTime agora = LocalDateTime.now();
        for (AuditoriaLog log : logsAlteracoes) {
            log.setUsuarioAutor(usuarioAutor);
            log.setNomeTabelaModificada(nomeTabela);
            log.setEntidadeId(entidadeId);
            log.setTipoOperacao("UPDATE");
            log.setDataAlteracao(agora);
        }
        repositorioAuditoria.saveAll(logsAlteracoes);
    }

    public static AuditoriaLog compararCampo(String nomeCampo, Object valorAnterior, Object novoValor) {
        String anterior = valorAnterior == null ? null : valorAnterior.toString();
        String novo = novoValor == null ? null : novoValor.toString();

        boolean mudou = anterior == null ? novo != null : !anterior.equals(novo);
        if (!mudou)
            return null;

        AuditoriaLog log = new AuditoriaLog();
        log.setCampoAlterado(nomeCampo);
        log.setValorAnterior(anterior);
        log.setNovoValor(novo);
        return log;
    }

    public static void adicionarSeAlterado(List<AuditoriaLog> lista, AuditoriaLog log) {
        if (log != null)
            lista.add(log);
    }

    public List<AuditoriaLog> buscarPorEntidade(String nomeTabela, Long entidadeId) {
        return repositorioAuditoria.findByNomeTabelaModificadaAndEntidadeId(nomeTabela, entidadeId);
    }

    public List<AuditoriaLog> buscarPorUsuario(Long usuarioId) {
        return repositorioAuditoria.findByUsuarioAutor(usuarioId);
    }

    public List<AuditoriaLog> buscarPorTabela(String nomeTabela) {
        return repositorioAuditoria.findByNomeTabelaModificada(nomeTabela);
    }

    public List<AuditoriaLog> buscarTodos() {
        return repositorioAuditoria.findAll();
    }
}
