package br.com.wjaa.ranchucrutes.ws.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wagner on 06/09/15.
 */
@Entity
@Table(name = "APP_CONFIG")
public class AppConfigEntity {

    private Integer id;
    private String chave;
    private String valor;
    private Date dataCriacao;
    private Date dataAtualizacao;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "CHAVE", nullable = false)
    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    @Column(name = "VALOR", nullable = false)
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Column(name = "DATA_CRIACAO", nullable = false)
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Column(name = "DATA_ATUALIZACAO", nullable = false)
    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
