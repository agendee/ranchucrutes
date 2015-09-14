package br.com.wjaa.ranchucrutes.ws.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wagner on 15/06/15.
 */
@Entity
@Table(name = "CONVENIO_CATEGORIA")
public class ConvenioCategoriaEntity implements Serializable {

    private static final long serialVersionUID = 5731155124845130216L;
    private Integer id;
    private String nome;
    private Integer idConvenio;
    @JsonIgnore
    private ConvenioEntity convenio;
    private Boolean ativo;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NOME", nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "ID_CONVENIO", nullable = false)
    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    @ManyToOne()
    @JoinColumn(name = "ID_CONVENIO", nullable = false, insertable = false, updatable = false)
    public ConvenioEntity getConvenio() {
        return convenio;
    }

    public void setConvenio(ConvenioEntity convenio) {
        this.convenio = convenio;
    }

    @Column(name = "ATIVO", nullable = false)
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
