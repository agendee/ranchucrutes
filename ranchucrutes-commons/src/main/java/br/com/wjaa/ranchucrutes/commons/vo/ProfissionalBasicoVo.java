package br.com.wjaa.ranchucrutes.commons.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by wagner on 22/07/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfissionalBasicoVo {

    private Long id;
    private String nome;
    private Integer crm;
    private String espec;
    private Double latitude;
    private Double longitude;
    @Deprecated
    private String endereco;
    private String telefone;
    private ClinicaVo clinica;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public String getEspec() {
        return espec;
    }

    public void setEspec(String espec) {
        this.espec = espec;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getEndereco() {
        return endereco;
    }


    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public String getPrimeiroNome(){
        if (this.nome != null && this.nome.length() > 0){
            return this.nome.split(" ")[0];
        }
        return "Sem Nome";
    }

    public ClinicaVo getClinica() {
        return clinica;
    }

    public void setClinica(ClinicaVo clinica) {
        this.clinica = clinica;
    }
}
