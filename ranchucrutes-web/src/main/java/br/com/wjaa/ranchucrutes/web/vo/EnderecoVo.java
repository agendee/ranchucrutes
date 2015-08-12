package br.com.wjaa.ranchucrutes.web.vo;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 15/06/15.
 */
public class EnderecoVo implements Serializable{

    private static final long serialVersionUID = 7207991135581266227L;

    private Long id;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String localidade;
    private String uf;
    private String complemento;
    private String cep;
    private Double latitude;
    private Double longitude;
    private Long idLogin;
    private AgendaVo agenda;
    private DistanceVo distanceVo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Long idLogin) {
        this.idLogin = idLogin;
    }

    protected List<AgendaVo> getAgendas() {
        if (this.agenda != null){
            List<AgendaVo> agendas = new ArrayList<>(1);
            agendas.add(this.agenda);
            return agendas;
        }
        return null;
    }

    protected void setAgendas(List<AgendaVo> agendas) {
        if (!CollectionUtils.isEmpty(agendas)){
            agenda = agendas.get(0);
        }
    }

    public AgendaVo getAgenda() {
        return this.agenda;
    }

    public void setAgenda(AgendaVo agenda) {
        this.agenda = agenda;
    }

    public DistanceVo getDistanceVo() {
        return distanceVo;
    }

    public void setDistanceVo(DistanceVo distanceVo) {
        this.distanceVo = distanceVo;
    }

    @Override
    public String toString() {
        return this.logradouro + ", "  + this.numero + " - " + this.localidade + " " + this.uf;
    }
}
