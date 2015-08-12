package br.com.wjaa.ranchucrutes.ws.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wagner on 01/07/15.
 */
@Entity
@Table(name = "AGENDA_HORARIO")
public class AgendaHorarioEntity implements Serializable {


    private static final long serialVersionUID = -4264865042865019912L;
    private Long id;
    private String horaIni;
    private String horaFim;
    private Long idAgenda;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "HORA_INI", nullable = false)
    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    @Column(name = "HORA_FIM", nullable = false)
    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    @Column(name = "ID_AGENDA", nullable = false)
    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }
}
