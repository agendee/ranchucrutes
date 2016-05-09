
package br.com.wjaa.ranchucrutes.ws.integracao.camtwo.vo;

import br.com.wjaa.ranchucrutes.commons.utils.DateUtils;
import br.com.wjaa.ranchucrutes.commons.vo.AgendaVo;
import org.apache.commons.lang.ArrayUtils;

import java.util.*;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class AgendaCamtwoVO {

    private Clinica clinica;
    private List<DiaHorario> diaHorarios = new ArrayList<DiaHorario>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The clinica
     */
    public Clinica getClinica() {
        return clinica;
    }

    /**
     * 
     * @param clinica
     *     The clinica
     */
    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    /**
     * 
     * @return
     *     The diaHorarios
     */
    public List<DiaHorario> getDiaHorarios() {
        return diaHorarios;
    }

    /**
     * 
     * @param diaHorarios
     *     The diaHorarios
     */
    public void setDiaHorarios(List<DiaHorario> diaHorarios) {
        this.diaHorarios = diaHorarios;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public AgendaVo toAgendaVO() {

        AgendaVo agendaVo = new AgendaVo();
        Date[] horariosDisponiveis = new Date[]{};
        for ( DiaHorario dh : this.getDiaHorarios() ){

            for (HorariosDisponivei h : dh.getHorariosDisponiveis()){

                Date dataHora = DateUtils.getDateyyyymmddhhmm(dh.getDia() + " " + h.getHora());
                horariosDisponiveis = (Date[]) ArrayUtils.add(horariosDisponiveis,dataHora);
            }
        }
        agendaVo.setHorariosDisponiveis(horariosDisponiveis);
        return agendaVo;
    }
}
