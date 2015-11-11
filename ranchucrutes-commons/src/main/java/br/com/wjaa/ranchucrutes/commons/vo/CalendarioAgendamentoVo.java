package br.com.wjaa.ranchucrutes.commons.vo;

import java.util.List;

/**
 * Created by wagner on 06/11/15.
 */
public class CalendarioAgendamentoVo {

    private String dataIni;
    private String dataFim;
    private List<CalendarioClinicaVo> calendariosClinicas;

    public String getDataIni() {
        return dataIni;
    }

    public void setDataIni(String dataIni) {
        this.dataIni = dataIni;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public List<CalendarioClinicaVo> getCalendariosClinicas() {
        return calendariosClinicas;
    }

    public void setCalendariosClinicas(List<CalendarioClinicaVo> calendariosClinicas) {
        this.calendariosClinicas = calendariosClinicas;
    }
}
