package com.criarTarefas.criarTarefas.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria_log")
public class AuditoriaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioAutor;

    @Column(nullable = false)
    private String nomeTabelaModificada;

    @Column(nullable = false)
    private Long entidadeId;

    @Column(nullable = false)
    private String campoAlterado;

    @Column(columnDefinition = "TEXT")
    private String valorAnterior;

    @Column(columnDefinition = "TEXT")
    private String novoValor;

    @Column(nullable = false)
    private String tipoOperacao;

    @Column(nullable = false)
    private LocalDateTime dataAlteracao;

    public Long getId() { return id; }

    public Long getUsuarioAutor() { return usuarioAutor; }
    public void setUsuarioAutor(Long usuarioAutor) { this.usuarioAutor = usuarioAutor; }

    public String getNomeTabelaModificada() { return nomeTabelaModificada; }
    public void setNomeTabelaModificada(String nomeTabelaModificada) { this.nomeTabelaModificada = nomeTabelaModificada; }

    public Long getEntidadeId() { return entidadeId; }
    public void setEntidadeId(Long entidadeId) { this.entidadeId = entidadeId; }

    public String getCampoAlterado() { return campoAlterado; }
    public void setCampoAlterado(String campoAlterado) { this.campoAlterado = campoAlterado; }

    public String getValorAnterior() { return valorAnterior; }
    public void setValorAnterior(String valorAnterior) { this.valorAnterior = valorAnterior; }

    public String getNovoValor() { return novoValor; }
    public void setNovoValor(String novoValor) { this.novoValor = novoValor; }

    public String getTipoOperacao() { return tipoOperacao; }
    public void setTipoOperacao(String tipoOperacao) { this.tipoOperacao = tipoOperacao; }

    public LocalDateTime getDataAlteracao() { return dataAlteracao; }
    public void setDataAlteracao(LocalDateTime dataAlteracao) { this.dataAlteracao = dataAlteracao; }
}
