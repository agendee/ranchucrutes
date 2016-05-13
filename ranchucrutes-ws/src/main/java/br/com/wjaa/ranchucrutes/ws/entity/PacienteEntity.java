package br.com.wjaa.ranchucrutes.ws.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 12/06/15.
 */
@Entity
@Table(name = "PACIENTE")
@PrimaryKeyJoinColumn(name="ID_LOGIN")
public class PacienteEntity extends LoginEntity implements Serializable {


    private String nome;
    private String telefone;
    private String keySocial;
    private RedeSocialEnum redeSocial;
    private List<ConvenioCategoriaEntity> convenios;
    private Date dataNascimento;
    private String cpf;
    private SexoEnum sexo;


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



    @OneToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "PACIENTE_CONVENIO_CATEGORIA", joinColumns={@JoinColumn(name="ID_PACIENTE", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ID_CATEGORIA", referencedColumnName="ID")})
    public List<ConvenioCategoriaEntity> getConvenios() {
        return convenios;
    }

    public void setConvenios(List<ConvenioCategoriaEntity> convenios) {
        this.convenios = convenios;
    }


    @Column(name = "DATA_NASCIMENTO")
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Column(name = "CPF", length = 11)
    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name = "SEXO", length = 1)
    @Enumerated(EnumType.STRING)
    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }


    @Transient
    public Integer [] getIdsCategoriaConvenio() {
        if (convenios != null){
            Integer [] ids = new Integer[convenios.size()];

            for (int i = 0; i < convenios.size(); i++){
                ConvenioCategoriaEntity c = convenios.get(i);
                ids[i] = c.getId();
            }
            return ids;
        }
        return null;
    }

    @Transient
    public void addConvenioCategoria(ConvenioCategoriaEntity c) {
        if (convenios == null){
            convenios = new ArrayList<ConvenioCategoriaEntity>();
        }
        convenios.add(c);
    }
}
