package br.com.wjaa.ranchucrutes.ws.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wagner on 17/06/15.
 */
@Entity
@Table(name="LOGIN")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LoginEntity implements Serializable {

    private static final long serialVersionUID = -3524811303273920421L;
    private Long idLogin;
    private String email;
    private String senha;
    private Date dataCriacao;
    private Date dataUltimoAcesso;
    private Date dataConfirmacao;
    private String codeConfirmacao;
    private Boolean ativo;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Long idLogin) {
        this.idLogin = idLogin;
    }

    @Column(name = "EMAIL", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "SENHA", nullable = false)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(name = "DATA_CRIACAO", nullable = false)
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Column(name = "DATA_ULTIMO_ACESSO", nullable = false)
    public Date getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    public void setDataUltimoAcesso(Date dataUltimoAcesso) {
        this.dataUltimoAcesso = dataUltimoAcesso;
    }


    @Column(name = "DATA_CONFIRMACAO", nullable = false)
    public Date getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(Date dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    @Column(name = "CODE_CONFIRMACAO", nullable = false)
    public String getCodeConfirmacao() {
        return codeConfirmacao;
    }

    public void setCodeConfirmacao(String codeConfirmacao) {
        this.codeConfirmacao = codeConfirmacao;
    }


    @Column(name = "ATIVO", nullable = false)
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
