package br.com.wjaa.ranchucrutes.ws.integracao.camtwo.vo;

/**
 * Created by wagner on 5/12/16.
 */
public class AgendaProfissionalExternaVO {

    private String tokenProfissional;
    private String data;
    private String horaInicio;
    private PacienteAgendaExternaVO paciente;
    private Long idAgenda;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public PacienteAgendaExternaVO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteAgendaExternaVO paciente) {
        this.paciente = paciente;
    }

    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getTokenProfissional() {
        return tokenProfissional;
    }

    public void setTokenProfissional(String tokenProfissional) {
        this.tokenProfissional = tokenProfissional;
    }
}
