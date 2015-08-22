package br.com.wjaa.ranchucrutes.commons.form;

/**
 * Created by wagner on 22/08/15.
 */
public class HorarioForm {

    private Long id;
    private String horaIni;
    private String horaFim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }
}
