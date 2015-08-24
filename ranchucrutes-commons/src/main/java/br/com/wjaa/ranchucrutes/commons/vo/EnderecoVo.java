package br.com.wjaa.ranchucrutes.commons.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wagner on 05/07/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoVo{

    private String bairro;
    private String cep;
    private String logradouro;
    private String localidade;
    private String uf;

    public EnderecoVo(){}



    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @JsonProperty("rua")
    public void setRua(String rua){
        this.logradouro = rua;
    }
    @JsonProperty("address")
    public void setStreet(String logradouro){
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @JsonProperty("code")
    public void setCode(String code){
        this.cep = code;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @JsonProperty("city")
    public void setCity(String localidade) {
        this.localidade = localidade;
    }

    @JsonProperty("cidade")
    public void setCidade(String localidade) {
        this.localidade = localidade;
    }

    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @JsonProperty("district")
    public void setDistrict(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @JsonProperty("estado")
    public void setEstado(String estado) {
        this.uf = estado;
    }
    @JsonProperty("state")
    public void setState(String uf) {
        this.uf = uf;
    }

}
