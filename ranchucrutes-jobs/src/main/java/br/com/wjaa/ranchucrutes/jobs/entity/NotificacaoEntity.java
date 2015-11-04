package br.com.wjaa.ranchucrutes.jobs.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wagner on 01/07/15.
 */
@Entity
@Table(name = "NOTIFICACAO")
public class NotificacaoEntity implements Serializable {

    public enum NotificacaoType {
        //notificacao de cancelamento da consulta.
        NOTIFICACAO_CANCELAMENTO(NotificacaoVia.APP,NotificacaoVia.EMAIL),


        ;

        private NotificacaoType(NotificacaoVia ... vias){
            this.vias = vias;
        }
        NotificacaoVia [] vias;

    }

    public enum NotificacaoVia {
        SMS, //usado para disparos de sms. ATIVO
        WHATSAPP, //usado para disparos de msg para whatsapp. ATIVO
        EMAIL, //usado para disparos de emails. ATIVO
        APP //usado por um servico, essa é unica Via contraria, quem solicita é o cliente. PASSIVO.
    }


    private Long id;
    private NotificacaoType notificacaoType;
    private Date dataCriacao;
    private Date dataEnvio;
    private Long idLogin;
    private Long idReferencia;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "DATA_CRIACAO", nullable = false)
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TIPO", nullable = false)
    public NotificacaoType getNotificacaoType() {
        return notificacaoType;
    }

    public void setNotificacaoType(NotificacaoType notificacaoType) {
        this.notificacaoType = notificacaoType;
    }

    @Column(name = "DATA_ENVIO")
    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Column(name = "ID_LOGIN", nullable = false)
    public Long getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Long idLogin) {
        this.idLogin = idLogin;
    }

    /*id da tabela de referencia*/
    @Column(name = "ID_REFERENCIA", nullable = false)
    public Long getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Long idReferencia) {
        this.idReferencia = idReferencia;
    }
}