package br.com.wjaa.ranchucrutes.commons.form;

import br.com.wjaa.ranchucrutes.commons.helper.JacksonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

/**
 * Created by wagner on 15/10/15.
 */
public class AgendamentoForm {

    private Long idPaciente;
    private Long idProfissional;
    private Long idClinica;
    @JsonDeserialize(using = JacksonDateDeserializer.class)
    private Date dataAgendamento;
    private Boolean consultaParticular;

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Long idProfissional) {
        this.idProfissional = idProfissional;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Long getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Long idClinica) {
        this.idClinica = idClinica;
    }

    public Boolean getConsultaParticular() {
        return consultaParticular;
    }

    public void setConsultaParticular(Boolean consultaParticular) {
        this.consultaParticular = consultaParticular;
    }
}
