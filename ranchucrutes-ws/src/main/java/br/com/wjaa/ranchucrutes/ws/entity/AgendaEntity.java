package br.com.wjaa.ranchucrutes.ws.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wagner on 01/07/15.
 */
@Entity
@Table(name = "AGENDA")
public class AgendaEntity implements Serializable {

    private static final long serialVersionUID = 2483618403981663111L;
    private Long id;
    private String horaFuncionamentoIni;
    private String horaFuncionamentoFim;
    private Integer tempoConsultaEmMin;
    private Long idClinica;
    private List<AgendaHorarioEntity> agendaHorarios;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "HORA_FUNCIONAMENTO_INI", nullable = false)
    public String getHoraFuncionamentoIni() {
        return horaFuncionamentoIni;
    }

    public void setHoraFuncionamentoIni(String horaFuncionamentoIni) {
        this.horaFuncionamentoIni = horaFuncionamentoIni;
    }

    @Column(name = "HORA_FUNCIONAMENTO_FIM", nullable = false)
    public String getHoraFuncionamentoFim() {
        return horaFuncionamentoFim;
    }

    public void setHoraFuncionamentoFim(String horaFuncionamentoFim) {
        this.horaFuncionamentoFim = horaFuncionamentoFim;
    }

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "ID_AGENDA", insertable = false, updatable = false)
    public List<AgendaHorarioEntity> getAgendaHorarios() {
        return agendaHorarios;
    }


    public void setAgendaHorarios(List<AgendaHorarioEntity> agendaHorarios) {
        this.agendaHorarios = agendaHorarios;
    }

    @Column(name = "TEMPO_CONSULTA_EM_MIN", nullable = false)
    public Integer getTempoConsultaEmMin() {
        return tempoConsultaEmMin;
    }

    public void setTempoConsultaEmMin(Integer tempoConsultaEmMin) {
        this.tempoConsultaEmMin = tempoConsultaEmMin;
    }


    @Column(name = "ID_CLINICA")
    public Long getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Long idClinica) {
        this.idClinica = idClinica;
    }
}
