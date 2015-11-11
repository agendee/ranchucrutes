package br.com.wjaa.ranchucrutes.commons.vo;

import java.util.List;

/**
 * Created by wagner on 10/11/15.
 */
public class CalendarioClinicaVo {

    private Long idAgenda;
    private List<AgendamentoVo> agendamento;
    private String horaFuncionamentoIni;
    private String horaFuncionamentoFim;
    private Integer tempoConsultaEmMin;
    private Integer diasAberturaAgenda;
    private ClinicaVo clinicaVo;


    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public List<AgendamentoVo> getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(List<AgendamentoVo> agendamento) {
        this.agendamento = agendamento;
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

    public Integer getTempoConsultaEmMin() {
        return tempoConsultaEmMin;
    }

    public void setTempoConsultaEmMin(Integer tempoConsultaEmMin) {
        this.tempoConsultaEmMin = tempoConsultaEmMin;
    }

    public Integer getDiasAberturaAgenda() {
        return diasAberturaAgenda;
    }

    public void setDiasAberturaAgenda(Integer diasAberturaAgenda) {
        this.diasAberturaAgenda = diasAberturaAgenda;
    }

    public ClinicaVo getClinicaVo() {
        return clinicaVo;
    }

    public void setClinicaVo(ClinicaVo clinicaVo) {
        this.clinicaVo = clinicaVo;
    }

}
