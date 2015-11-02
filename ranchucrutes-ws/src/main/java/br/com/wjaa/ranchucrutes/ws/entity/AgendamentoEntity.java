package br.com.wjaa.ranchucrutes.ws.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 01/07/15.
 */
@Entity
@Table(name = "AGENDAMENTO")
public class AgendamentoEntity implements Serializable {

    private static final long serialVersionUID = 2483618403981663111L;
    private Long id;
    private Long idProfissional;
    private Long idPaciente;
    private Long idClinica;
    private Date dataAgendamento;
    private Date dataCriacao;
    private Date dataConfirmacao;
    private String codigoConfirmacao;
    private Boolean cancelado;
    private Date dataConfirmacaoConsulta;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "ID_PROFISSIONAL", nullable = false)
    public Long getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Long idProfissional) {
        this.idProfissional = idProfissional;
    }

    @Column(name = "ID_PACIENTE", nullable = false)
    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Column(name = "DATA_AGENDAMENTO", nullable = false)
    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    @Column(name = "DATA_CRIACAO", nullable = false)
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Column(name = "DATA_CONFIRMACAO")
    public Date getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(Date dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    @Column(name = "CODIGO_CONFIRMACAO")
    public String getCodigoConfirmacao() {
        return codigoConfirmacao;
    }

    public void setCodigoConfirmacao(String codigoConfirmacao) {
        this.codigoConfirmacao = codigoConfirmacao;
    }

    @Column(name = "CANCELADO", nullable = false)
    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    @Column(name = "DATA_CONFIRMACAO_CONSULTA", nullable = false)
    public Date getDataConfirmacaoConsulta() {
        return dataConfirmacaoConsulta;
    }

    public void setDataConfirmacaoConsulta(Date dataConfirmacaoConsulta) {
        this.dataConfirmacaoConsulta = dataConfirmacaoConsulta;
    }

    @Column(name = "ID_CLINICA", nullable = false)
    public Long getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Long idClinica) {
        this.idClinica = idClinica;
    }

    @Override
    public String toString() {
        return "AgendamentoEntity{" +
                "id=" + id +
                ", idProfissional=" + idProfissional +
                ", idPaciente=" + idPaciente +
                ", idClinica=" + idClinica +
                ", dataAgendamento=" + dataAgendamento +
                ", dataCriacao=" + dataCriacao +
                ", dataConfirmacao=" + dataConfirmacao +
                ", codigoConfirmacao='" + codigoConfirmacao + '\'' +
                ", cancelado=" + cancelado +
                ", dataConfirmacaoConsulta=" + dataConfirmacaoConsulta +
                '}';
    }
}
