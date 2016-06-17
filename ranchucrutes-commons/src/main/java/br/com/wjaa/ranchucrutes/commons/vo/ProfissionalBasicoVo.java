package br.com.wjaa.ranchucrutes.commons.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 22/07/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfissionalBasicoVo {

    private Long id;
    private String nome;
    private String numeroRegistro;
    private String espec;
    private Double latitude;
    private Double longitude;
    private String endereco;
    private String telefone;
    private List<ClinicaVo> clinicas;
    private Boolean temAgenda;
    private Integer idProfissao;
    private String nomeProfissao;
    private Integer idParceiro;
    private Boolean aceitaParticular;
    private Boolean aceitaPlano;
    private Long idClinicaAtual;
    private String foto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
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

    public List<ClinicaVo> getClinicas() {
        return clinicas;
    }

    public void setClinicas(List<ClinicaVo> clinicas) {
        this.clinicas = clinicas;
    }

    public void addClinica(ClinicaVo clinica){
        if (clinicas == null){
            this.clinicas = new ArrayList<>();
        }
        this.clinicas.add(clinica);
    }

    public Boolean getTemAgenda() {
        return temAgenda;
    }

    public void setTemAgenda(Boolean temAgenda) {
        this.temAgenda = temAgenda;
    }

    public Integer getIdProfissao() {
        return idProfissao;
    }

    public void setIdProfissao(Integer idProfissao) {
        this.idProfissao = idProfissao;
    }

    public String getNomeProfissao() {
        return nomeProfissao;
    }

    public void setNomeProfissao(String nomeProfissao) {
        this.nomeProfissao = nomeProfissao;
    }

    public Integer getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(Integer idParceiro) {
        this.idParceiro = idParceiro;
    }

    public Boolean getAceitaParticular() {
        return aceitaParticular;
    }

    public void setAceitaParticular(Boolean aceitaParticular) {
        this.aceitaParticular = aceitaParticular;
    }

    public Boolean getAceitaPlano() {
        return aceitaPlano;
    }

    public void setAceitaPlano(Boolean aceitaPlano) {
        this.aceitaPlano = aceitaPlano;
    }
    
    public Long getIdClinicaAtual() {
        return idClinicaAtual;
    }

    public void setIdClinicaAtual(Long idClinicaAtual) {
        this.idClinicaAtual = idClinicaAtual;

    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
