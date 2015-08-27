package br.com.wjaa.ranchucrutes.commons.form;

/**
 * Created by wagner on 21/08/15.
 */
public class ConvenioCategoriaForm {

    private Integer id;
    private String nome;

    public ConvenioCategoriaForm(){}
    public ConvenioCategoriaForm(Integer id, String nome){
        this.id = id;
        this.nome = nome;
    }

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
}
