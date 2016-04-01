package br.com.wjaa.ranchucrutes.jobs.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 12/06/15.
 */
@Entity
@Table(name = "PROFISSIONAL")
@PrimaryKeyJoinColumn(name="ID_LOGIN")
public class ProfissionalEntity extends LoginEntity implements Serializable{

    private static final long serialVersionUID = 8235523726178515224L;
    private Integer crm;
    private String nome;
    private Short ddd;
    private Long celular;
    private String cpf;
    //private Notification notificacoes de aplicativos


     @Column(name = "CRM", nullable = false)
    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    @Column(name = "NOME", nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Column(name = "DDD")
    public Short getDdd() {
        return ddd;
    }

    public void setDdd(Short ddd) {
        this.ddd = ddd;
    }

    @Column(name = "CELULAR")
    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long telefone) {
        this.celular = telefone;
    }

    @Column(name = "CPF")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


}
