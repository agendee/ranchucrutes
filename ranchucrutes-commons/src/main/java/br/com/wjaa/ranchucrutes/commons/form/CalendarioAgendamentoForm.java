package br.com.wjaa.ranchucrutes.commons.form;

import java.util.Date;

/**
 * Created by wagner on 09/11/15.
 */
public class CalendarioAgendamentoForm {

    private Long idProfissional;
    private Long idClinica;
    private Date dataIni;
    private Date dataFim;

    public Long getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Long idProfissional) {
        this.idProfissional = idProfissional;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Long getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Long idClinica) {
        this.idClinica = idClinica;
    }
}
