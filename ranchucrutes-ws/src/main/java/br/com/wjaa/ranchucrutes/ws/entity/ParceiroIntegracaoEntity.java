package br.com.wjaa.ranchucrutes.ws.entity;

import javax.persistence.*;

/**
 * Created by wagner on 4/15/16.
 */
@Entity
@Table(name = "PARCEIRO_INTEGRACAO")
public class ParceiroIntegracaoEntity {


    private Integer id;
    private Integer idParceiro;
    private String host;
    private String bean;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ID_PARCEIRO", nullable = false)
    public Integer getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(Integer idParceiro) {
        this.idParceiro = idParceiro;
    }

    @Column(name = "HOST", nullable = false, length = 200)
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Column(name = "BEAN", nullable = false, length = 100)
    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }
}
