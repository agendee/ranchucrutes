package br.com.wjaa.ranchucrutes.ws.entity;

import javax.persistence.*;

/**
 * @author wagner
 */
@Entity
@Table(name = "PROFISSIONAL_ORIGEM")
public class ProfissionalOrigemEntity {

    private Integer id;
    private Long idProfissional;
    private Long idClinica;
    private ParceiroEmpresaEntity parceiro;
    private String tokenProfissional;
    private String tokenClinica;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ID_PROFISSIONAL", nullable = false)
    public Long getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Long idProfissional) {
        this.idProfissional = idProfissional;
    }

    @Column(name = "ID_CLINICA", nullable = false)
    public Long getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Long idClinica) {
        this.idClinica = idClinica;
    }

    @Column(name = "TOKEN_PROFISSIONAL", nullable = false)
    public String getTokenProfissional() {
        return tokenProfissional;
    }

    public void setTokenProfissional(String hashProfissional) {
        this.tokenProfissional = hashProfissional;
    }

    @Column(name = "TOKEN_CLINICA", nullable = false)
    public String getTokenClinica() {
        return tokenClinica;
    }

    public void setTokenClinica(String tokenClinica) {
        this.tokenClinica = tokenClinica;
    }

    @ManyToOne
    @JoinColumn(name = "ID_PARCEIRO", nullable = false)
    public ParceiroEmpresaEntity getParceiro() {
        return parceiro;
    }

    public void setParceiro(ParceiroEmpresaEntity parceiro) {
        this.parceiro = parceiro;
    }


    @Transient
    public Integer getIdParceiro(){
        return parceiro.getId();
    }
}
