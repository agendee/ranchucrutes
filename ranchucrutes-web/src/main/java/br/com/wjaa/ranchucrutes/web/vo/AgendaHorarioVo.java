package br.com.wjaa.ranchucrutes.web.vo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wagner on 01/07/15.
 */
public class AgendaHorarioVo implements Serializable {


    private static final long serialVersionUID = -4264865042865019912L;
    private Long id;
    private String horaIni;
    private String horaFim;
    private Long idAgenda;


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

    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }
}
