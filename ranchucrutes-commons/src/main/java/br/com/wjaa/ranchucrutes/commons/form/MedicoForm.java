package br.com.wjaa.ranchucrutes.commons.form;

/**
 * Created by wagner on 12/08/15.
 */
public class MedicoForm {

    private Long idLogin;
    private String nome;
    private Integer crm;
    private Integer [] idEspecialidade;
    private Integer ddd;
    private Long celular;
    private String email;
    private String senha;

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

    public Integer[] getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer[] idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
