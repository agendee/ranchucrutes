package br.com.wjaa.ranchucrutes.ws.entity;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.ws.vo.DistanceVo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wagner on 15/06/15.
 */
@Entity
@Table(name = "ENDERECO")
public class EnderecoEntity implements Serializable{

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
    private DistanceVo distanceVo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "LOGRADOURO", nullable = false)
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Column(name = "NUMERO", nullable = false)
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Column(name = "BAIRRO", nullable = false)
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Column(name = "LOCALIDADE", nullable = false)
    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Column(name = "UF", nullable = false)
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Column(name = "COMPLEMENTO")
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Column(name = "CEP", nullable = false)
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Column(name = "LONGITUDE")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "LATITUDE")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonIgnore
    @Transient
    public LocationVo getLocationVo(){
        return new LocationVo(this.latitude,this.longitude);
    }

    @Transient
    public DistanceVo getDistanceVo() {
        return distanceVo;
    }

    public void setDistanceVo(DistanceVo distanceVo) {
        this.distanceVo = distanceVo;
    }

    public boolean hasLocation() {
        return this.latitude != null && this.longitude != null;
    }


}
