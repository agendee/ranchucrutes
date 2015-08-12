package br.com.wjaa.ranchucrutes.web.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wagner on 01/07/15.
 */
public class AgendaVo implements Serializable {

    private static final long serialVersionUID = 2483618403981663111L;
    private Long id;
    private String horaFuncionamentoIni;
    private String horaFuncionamentoFim;
    private Integer tempoConsultaEmMin;
    private Long idClinica;
    private List<AgendaHorarioVo> agendaHorarios;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoraFuncionamentoIni() {
        return horaFuncionamentoIni;
    }

    public void setHoraFuncionamentoIni(String horaFuncionamentoIni) {
        this.horaFuncionamentoIni = horaFuncionamentoIni;
    }

    public String getHoraFuncionamentoFim() {
        return horaFuncionamentoFim;
    }

    public void setHoraFuncionamentoFim(String horaFuncionamentoFim) {
        this.horaFuncionamentoFim = horaFuncionamentoFim;
    }

    public List<AgendaHorarioVo> getAgendaHorarios() {
        return agendaHorarios;
    }

    public void setAgendaHorarios(List<AgendaHorarioVo> agendaHorarios) {
        this.agendaHorarios = agendaHorarios;
    }

    public Integer getTempoConsultaEmMin() {
        return tempoConsultaEmMin;
    }

    public void setTempoConsultaEmMin(Integer tempoConsultaEmMin) {
        this.tempoConsultaEmMin = tempoConsultaEmMin;
    }

    public Long getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Long idClinica) {
        this.idClinica = idClinica;
    }
}
