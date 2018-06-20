package br.com.wjaa.ranchucrutes.commons.form;

/**
 * Created by wagner on 12/08/15.
 */
public class ProfissionalForm {

    private Long idLogin;
    private String nome;
    private String numeroRegistro;
    private Integer [] idEspecialidade;
    private Short ddd;
    private Long celular;
    private String email;
    private String senha;
    private String cpf;
    private String foto;
    private Boolean atendente;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Integer[] getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer[] idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public Short getDdd() {
        return ddd;
    }

    public void setDdd(Short ddd) {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Long idLogin) {
        this.idLogin = idLogin;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

	public Boolean getAtendente() {
		return atendente;
	}

	public void setAtendente(Boolean atendente) {
		this.atendente = atendente;
	}
}
