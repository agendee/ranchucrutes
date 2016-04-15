package br.com.wjaa.ranchucrutes.ws.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wagner on 15/06/15.
 */
@Entity
@Table(name = "ESPECIALIDADE")
public class EspecialidadeEntity implements Serializable {

    private static final long serialVersionUID = -315464438387192274L;
    private Integer id;
    private String nome;
    private Boolean popular;
    private Boolean ativo;
    private ProfissaoEntity profissao;


    public EspecialidadeEntity(){}

    public EspecialidadeEntity(Integer idEspecialidade, String nome) {
        this.id = idEspecialidade;
        this.nome = nome;
    }

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


    @Column(name = "POPULAR", nullable = false)
    public Boolean getPopular() {
        return popular;
    }

    public void setPopular(Boolean popular) {
        this.popular = popular;
    }

    @Column(name = "ATIVO", nullable = false)
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


    @ManyToOne()
    @JoinColumn(name="ID_PROFISSAO")
    public ProfissaoEntity getProfissao() {
        return profissao;
    }

    public void setProfissao(ProfissaoEntity profissao) {
        this.profissao = profissao;
    }

}
