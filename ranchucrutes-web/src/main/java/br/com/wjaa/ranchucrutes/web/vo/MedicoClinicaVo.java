package br.com.wjaa.ranchucrutes.web.vo;

/**
 * Created by wagner on 21/07/15.
 */
public class MedicoClinicaVo {

    private Long id;
    private Long idMedico;
    private ClinicaVo clinica;
    private Short ddd;
    private Long telefone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public ClinicaVo getClinica() {
        return clinica;
    }

    public void setClinica(ClinicaVo clinica) {
        this.clinica = clinica;
    }

    public Short getDdd() {
        return ddd;
    }

    public void setDdd(Short ddd) {
        this.ddd = ddd;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }
}
