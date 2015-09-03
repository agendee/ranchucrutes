package br.com.wjaa.ranchucrutes.ws.entity;

import javax.persistence.*;

/**
 * Created by wagner on 21/07/15.
 */
@Entity
@Table(name = "MEDICO_CLINICA")
public class MedicoClinicaEntity {

    private Long id;
    private Long idMedico;
    private ClinicaEntity clinica;
    private Short ddd;
    private Long telefone;
    private Boolean aceitaParticular;
    private Double valorConsulta;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ID_MEDICO", nullable = false)
    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    @ManyToOne
    @JoinColumn(name = "ID_CLINICA", nullable = false)
    public ClinicaEntity getClinica() {
        return clinica;
    }

    public void setClinica(ClinicaEntity clinica) {
        this.clinica = clinica;
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

    @Column(name = "ACEITA_PARTICULAR", nullable = false)
    public Boolean getAceitaParticular() {
        return aceitaParticular;
    }

    public void setAceitaParticular(Boolean aceitaParticular) {
        this.aceitaParticular = aceitaParticular;
    }


    @Column(name = "VALOR_CONSULTA")
    public Double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(Double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicoClinicaEntity that = (MedicoClinicaEntity) o;

        if (!getId().equals(that.getId())) return false;
        if (!getIdMedico().equals(that.getIdMedico())) return false;
        return getClinica().equals(that.getClinica());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getIdMedico().hashCode();
        result = 31 * result + getClinica().hashCode();
        return result;
    }
}
