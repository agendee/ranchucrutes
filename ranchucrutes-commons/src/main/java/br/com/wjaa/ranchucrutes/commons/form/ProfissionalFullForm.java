package br.com.wjaa.ranchucrutes.commons.form;

import java.util.List;

/**
 * Created by wagner on 21/08/15.
 */
public class ProfissionalFullForm {

    private ProfissionalForm profissional;
    private List<ClinicaForm> clinicas;

    public ProfissionalForm getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalForm profissional) {
        this.profissional = profissional;
    }

    public List<ClinicaForm> getClinicas() {
        return clinicas;
    }

    public void setClinicas(List<ClinicaForm> clinicas) {
        this.clinicas = clinicas;
    }
}
