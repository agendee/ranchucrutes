
package br.com.wjaa.ranchucrutes.ws.integracao.camtwo.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

}
