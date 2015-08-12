package br.com.wjaa.ranchucrutes.ws.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 15/06/15.
 */
@Entity
@Table(name = "CLINICA")
public class ClinicaEntity implements Serializable{

    private static final long serialVersionUID = 7207991135581266227L;

    private Long id;
    private String nome;
    private Short ddd;
    private Long telefone;
    private AgendaEntity agenda;
    private EnderecoEntity endereco;
    private List<ConvenioCategoriaEntity> convenios;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Column(name = "NOME")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @OneToMany
    @JoinColumn(name = "ID_CLINICA", insertable = false, updatable = false)
    @JsonIgnore
    protected List<AgendaEntity> getAgendas() {
        if (this.agenda != null){
            List<AgendaEntity> agendas = new ArrayList<>(1);
            agendas.add(this.agenda);
            return agendas;
        }
        return null;
    }

    protected void setAgendas(List<AgendaEntity> agendas) {
        if (!CollectionUtils.isEmpty(agendas)){
            agenda = agendas.get(0);
        }
    }

    @Transient
    public AgendaEntity getAgenda() {
        return this.agenda;
    }

    public void setAgenda(AgendaEntity agenda) {
        this.agenda = agenda;
    }

    @OneToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "CLINICA_CONVENIO_CATEGORIA", joinColumns={@JoinColumn(name="ID_CLINICA", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ID_CATEGORIA", referencedColumnName="ID")})
    public List<ConvenioCategoriaEntity> getConvenios() {
        return convenios;
    }


    public void setConvenios(List<ConvenioCategoriaEntity> convenios) {
        this.convenios = convenios;
    }


    @Column(name = "DDD")
    public Short getDdd() {
        return ddd;
    }

    public void setDdd(Short ddd) {
        this.ddd = ddd;
    }

    @Column(name = "TELEFONE")
    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }


    @ManyToOne()
    @JoinColumn(name="ID_ENDERECO")
    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }
}
