package br.com.wjaa.ranchucrutes.commons.vo;

import br.com.wjaa.ranchucrutes.commons.helper.JacksonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by wagner on 15/10/15.
 */
public class AgendamentoVo {

    private Long id;
    private ProfissionalBasicoVo profissional;
    private PacienteVo paciente;
    @JsonSerialize(using = JacksonDateSerializer.class)
    private Date dataAgendamento;
    @JsonSerialize(using = JacksonDateSerializer.class)
    private Date dataCriacao;
    @JsonSerialize(using = JacksonDateSerializer.class)
    private Date dataConfirmacao;
    private String codigoConfirmacao;
    private Boolean cancelado;
    @JsonSerialize(using = JacksonDateSerializer.class)
    private Date dataConfirmacaoConsulta;
    @JsonSerialize(using = JacksonDateSerializer.class)
    private Date dataConfirmacaoProfissional;
    private Boolean finalizado;

    public ProfissionalBasicoVo getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalBasicoVo profissional) {
        this.profissional = profissional;
    }

    public PacienteVo getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteVo paciente) {
        this.paciente = paciente;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(Date dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public String getCodigoConfirmacao() {
        return codigoConfirmacao;
    }

    public void setCodigoConfirmacao(String codigoConfirmacao) {
        this.codigoConfirmacao = codigoConfirmacao;
    }

    public Date getDataConfirmacaoConsulta() {
        return dataConfirmacaoConsulta;
    }

    public void setDataConfirmacaoConsulta(Date dataConfirmacaoConsulta) {
        this.dataConfirmacaoConsulta = dataConfirmacaoConsulta;
    }

    public Date getDataConfirmacaoProfissional() {
        return dataConfirmacaoProfissional;
    }

    public void setDataConfirmacaoProfissional(Date dataConfirmacaoProfissional) {
        this.dataConfirmacaoProfissional = dataConfirmacaoProfissional;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }
}
