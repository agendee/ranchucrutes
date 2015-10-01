package br.com.wjaa.ranchucrutes.ws.entity;

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
@Table(name = "PACIENTE")
@PrimaryKeyJoinColumn(name="ID_LOGIN")
public class PacienteEntity extends LoginEntity implements Serializable{


    private String nome;
    private String telefone;
    private String keySocial;
    private RedeSocialEnum redeSocial;
    private Integer idCategoria;



    @Column(name = "NOME", nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "TELEFONE", nullable = false)
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Column(name = "SOCIAL_KEY", nullable = false)
    public String getKeySocial() {
        return keySocial;
    }

    public void setKeySocial(String keySocial) {
        this.keySocial = keySocial;
    }


    @Column(name = "REDE_SOCIAL")
    @Enumerated(EnumType.ORDINAL)
    public RedeSocialEnum getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(RedeSocialEnum redeSocial) {
        this.redeSocial = redeSocial;
    }


    @Column(name = "ID_CATEGORIA")
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}
