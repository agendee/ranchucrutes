
package br.com.wjaa.ranchucrutes.ws.integracao.camtwo.vo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ClinicaVO {

    private String token;
    private String nome;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The token
     */
    public String getToken() {
        return token;
    }

    /**
     * 
     * @param token
     *     The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 
     * @return
     *     The nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @param nome
     *     The nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
