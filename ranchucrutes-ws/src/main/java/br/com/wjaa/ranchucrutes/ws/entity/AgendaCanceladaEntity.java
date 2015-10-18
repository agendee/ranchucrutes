package br.com.wjaa.ranchucrutes.ws.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wagner on 01/07/15.
 */
@Entity
@Table(name = "AGENDA_CANCELADA")
public class AgendaCanceladaEntity implements Serializable {

    private static final long serialVersionUID = 2483618403981663111L;
    private Long id;
    private Long idProfissional;
    private Long idClinica;
    private Date dataCancelada;
    private Date dataCancelamento;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ID_PROFISSIONAL", nullable = false)
    public Long getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Long idProfissional) {
        this.idProfissional = idProfissional;
    }

    @Column(name = "DATA_CANCELADA", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDataCancelada() {
        return dataCancelada;
    }

    public void setDataCancelada(Date dataCancelada) {
        this.dataCancelada = dataCancelada;
    }

    @Column(name = "DATA_CANCELAMENTO", nullable = false)
    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }


    @Column(name = "ID_CLINICA", nullable = false)
    public Long getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Long idClinica) {
        this.idClinica = idClinica;
    }



}
