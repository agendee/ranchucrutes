package br.com.wjaa.ranchucrutes.web.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wagner on 15/06/15.
 */
public class ClinicaVo implements Serializable{

    private static final long serialVersionUID = 7207991135581266227L;

    private Long id;
    private String nome;
    private Short ddd;
    private Long telefone;
    private AgendaVo agenda;
    private EnderecoVo endereco;
    private List<ConvenioCategoriaVo> convenios;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AgendaVo getAgenda() {
        return this.agenda;
    }

    public void setAgenda(AgendaVo agenda) {
        this.agenda = agenda;
    }

    public List<ConvenioCategoriaVo> getConvenios() {
        return convenios;
    }


    public void setConvenios(List<ConvenioCategoriaVo> convenios) {
        this.convenios = convenios;
    }


    public Short getDdd() {
        return ddd;
    }

    public void setDdd(Short ddd) {
        this.ddd = ddd;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }


    public EnderecoVo getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoVo endereco) {
        this.endereco = endereco;
    }
}
