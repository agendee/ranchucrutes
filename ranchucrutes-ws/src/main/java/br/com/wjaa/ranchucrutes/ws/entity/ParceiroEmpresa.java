package br.com.wjaa.ranchucrutes.ws.entity;

import javax.persistence.*;

/**
 * Created by wagner on 4/14/16.
 */
@Entity
@Table(name = "EMPRESA_PARCEIRO")
public class ParceiroEmpresa {

    private Integer id;
    private String nome;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NOME", nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
