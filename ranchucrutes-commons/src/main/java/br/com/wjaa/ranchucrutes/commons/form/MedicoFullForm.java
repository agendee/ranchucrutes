package br.com.wjaa.ranchucrutes.commons.form;

import java.util.List;

/**
 * Created by wagner on 21/08/15.
 */
public class MedicoFullForm {

    private MedicoForm medico;
    private List<ClinicaForm> clinicas;

    public MedicoForm getMedico() {
        return medico;
    }

    public void setMedico(MedicoForm medico) {
        this.medico = medico;
    }

    public List<ClinicaForm> getClinicas() {
        return clinicas;
    }

    public void setClinicas(List<ClinicaForm> clinicas) {
        this.clinicas = clinicas;
    }
}
