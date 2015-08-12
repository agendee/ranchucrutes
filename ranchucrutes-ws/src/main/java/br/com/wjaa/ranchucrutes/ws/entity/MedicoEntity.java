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
@Table(name = "MEDICO")
@PrimaryKeyJoinColumn(name="ID_LOGIN")
public class MedicoEntity extends LoginEntity implements Serializable{

    private static final long serialVersionUID = 8235523726178515224L;
    private Integer crm;
    private String nome;
    private Short ddd;
    private Long celular;
    private List<MedicoClinicaEntity> clinicas;
    private List<EspecialidadeEntity> especialidades;
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

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "ID_MEDICO", insertable = false, updatable = false)
    public List<MedicoClinicaEntity> getClinicas() {
        return clinicas;
    }

    public void setClinicas(List<MedicoClinicaEntity> clinicas) {
        this.clinicas = clinicas;
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

    @OneToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "MEDICO_ESPECIALIDADE", joinColumns={@JoinColumn(name="ID_MEDICO", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ID_ESPECIALIDADE", referencedColumnName="ID")})
    public List<EspecialidadeEntity> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<EspecialidadeEntity> especialidades) {
        this.especialidades = especialidades;
    }

    @Transient
    public void addIdEspecialidade(Integer idEspecialidade){
        if (this.especialidades == null){
            this.especialidades = new ArrayList<>();
        }
        this.especialidades.add(new EspecialidadeEntity(idEspecialidade,null));
    }

}
