package br.com.wjaa.ranchucrutes.commons.vo;

import br.com.wjaa.ranchucrutes.commons.helper.JacksonDateDeserializer;
import br.com.wjaa.ranchucrutes.commons.helper.JacksonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    @JsonDeserialize(using = JacksonDateDeserializer.class)
    private Date dataAgendamento;
    @JsonSerialize(using = JacksonDateSerializer.class)
    @JsonDeserialize(using = JacksonDateDeserializer.class)
    private Date dataCriacao;
    @JsonDeserialize(using = JacksonDateDeserializer.class)
    @JsonSerialize(using = JacksonDateSerializer.class)
    private Date dataConfirmacao;
    private String codigoConfirmacao;
    private Boolean cancelado;
    @JsonDeserialize(using = JacksonDateDeserializer.class)
    @JsonSerialize(using = JacksonDateSerializer.class)
    private Date dataConfirmacaoConsulta;
    @JsonDeserialize(using = JacksonDateDeserializer.class)
    @JsonSerialize(using = JacksonDateSerializer.class)
    private Date dataConfirmacaoProfissional;
    private Boolean finalizado;
    private String dataInicioConsulta;
    private String dataFimConsulta;

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

    public String getDataInicioConsulta() {
        return dataInicioConsulta;
    }

    public void setDataInicioConsulta(String dataInicioConsulta) {
        this.dataInicioConsulta = dataInicioConsulta;
    }

    public String getDataFimConsulta() {
        return dataFimConsulta;
    }

    public void setDataFimConsulta(String dataFimConsulta) {
        this.dataFimConsulta = dataFimConsulta;
    }
}
