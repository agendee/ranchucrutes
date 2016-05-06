
package br.com.wjaa.ranchucrutes.ws.integracao.camtwo.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class DiaHorario {

    private String dia;
    private List<HorariosDisponivei> horariosDisponiveis = new ArrayList<HorariosDisponivei>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * 
     * @param dia
     *     The dia
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * 
     * @return
     *     The horariosDisponiveis
     */
    public List<HorariosDisponivei> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    /**
     * 
     * @param horariosDisponiveis
     *     The horariosDisponiveis
     */
    public void setHorariosDisponiveis(List<HorariosDisponivei> horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
