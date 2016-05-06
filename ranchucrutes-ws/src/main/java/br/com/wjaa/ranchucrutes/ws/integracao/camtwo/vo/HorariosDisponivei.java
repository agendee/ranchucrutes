
package br.com.wjaa.ranchucrutes.ws.integracao.camtwo.vo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class HorariosDisponivei {

    private String hora;
    private String profissional;
    private Integer codprofissional;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * 
     * @param hora
     *     The hora
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * 
     * @return
     *     The profissional
     */
    public String getProfissional() {
        return profissional;
    }

    /**
     * 
     * @param profissional
     *     The profissional
     */
    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }

    /**
     * 
     * @return
     *     The codprofissional
     */
    public Integer getCodprofissional() {
        return codprofissional;
    }

    /**
     * 
     * @param codprofissional
     *     The codprofissional
     */
    public void setCodprofissional(Integer codprofissional) {
        this.codprofissional = codprofissional;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
