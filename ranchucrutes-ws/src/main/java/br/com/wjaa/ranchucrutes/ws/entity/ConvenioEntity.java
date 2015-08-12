package br.com.wjaa.ranchucrutes.ws.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wagner on 15/06/15.
 */
@Entity
@Table(name = "CONVENIO")
public class ConvenioEntity {

    private Integer id;
    private String nome;
    private Boolean popular;
    private List<ConvenioCategoriaEntity> categorias;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "convenio", fetch = FetchType.EAGER)
    public List<ConvenioCategoriaEntity> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<ConvenioCategoriaEntity> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "ConvenioEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", popular=" + popular +
                ", categorias=" + categorias +
                '}';
    }
}
