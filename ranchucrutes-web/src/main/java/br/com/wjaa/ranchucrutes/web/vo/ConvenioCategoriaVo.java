package br.com.wjaa.ranchucrutes.web.vo;

import java.io.Serializable;

/**
 * Created by wagner on 15/06/15.
 */
public class ConvenioCategoriaVo implements Serializable {

    private static final long serialVersionUID = 5731155124845130216L;
    private Integer id;
    private String nome;
    private Integer idConvenio;
    private ConvenioVo convenio;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public ConvenioVo getConvenio() {
        return convenio;
    }

    public void setConvenio(ConvenioVo convenio) {
        this.convenio = convenio;
    }
}
