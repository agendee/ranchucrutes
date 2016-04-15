package br.com.wjaa.ranchucrutes.ws.entity;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wagner on 4/14/16.
 */
@Entity
@Table(name = "PARCEIRO_EMPRESA")
public class ParceiroEmpresaEntity {

    private Integer id;
    private String nome;
    private ParceiroIntegracaoEntity parceiroIntegracao;
    private List<ParceiroIntegracaoEntity> parceirosIntegracoes;

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


    @OneToMany
    @JoinColumn(name = "ID_PARCEIRO", insertable = false, updatable = false)
    public List<ParceiroIntegracaoEntity> getParceirosIntegracoes() {
        return parceirosIntegracoes;
    }

    public void setParceirosIntegracoes(List<ParceiroIntegracaoEntity> parceirosIntegracoes) {
        this.parceirosIntegracoes = parceirosIntegracoes;
        if (!CollectionUtils.isEmpty(this.parceirosIntegracoes)){
            this.parceiroIntegracao = this.parceirosIntegracoes.get(0);
        }
    }

    @Transient
    public ParceiroIntegracaoEntity getParceiroIntegracao() {
        return parceiroIntegracao;
    }

    public void setParceiroIntegracao(ParceiroIntegracaoEntity parceiroIntegracao) {
        this.parceiroIntegracao = parceiroIntegracao;
    }
}
