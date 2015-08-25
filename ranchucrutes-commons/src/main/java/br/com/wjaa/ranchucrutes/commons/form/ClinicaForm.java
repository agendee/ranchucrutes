package br.com.wjaa.ranchucrutes.commons.form;

import br.com.wjaa.ranchucrutes.commons.form.HorarioForm;

import java.util.List;

/**
 * Created by wagner on 22/08/15.
 */
public class ClinicaForm {


    private Long id;
    private Long idClinica;
    private String nome;
    private Short ddd;
    private Long telefone;
    private Boolean aceitaParticular;
    /*endereco*/
    private EnderecoForm endereco;
    /*convenios aceitos*/
    private Integer [] idPlanos;

    /*Horarios agenda*/
    private Long idAgenda;
    private String horaFuncionamentoIni;
    private String horaFuncionamentoFim;
    private Integer tempoConsultaEmMin;
    private Double valorConsulta;
    private List<HorarioForm> agendaHorarios;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(Double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public Integer[] getIdPlanos() {
        return idPlanos;
    }

    public void setIdPlanos(Integer[] idPlanos) {
        this.idPlanos = idPlanos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Long idClinica) {
        this.idClinica = idClinica;
    }

    public EnderecoForm getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoForm endereco) {
        this.endereco = endereco;
    }

    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getHoraFuncionamentoIni() {
        return horaFuncionamentoIni;
    }

    public void setHoraFuncionamentoIni(String horaFuncionamentoIni) {
        this.horaFuncionamentoIni = horaFuncionamentoIni;
    }

    public String getHoraFuncionamentoFim() {
        return horaFuncionamentoFim;
    }

    public void setHoraFuncionamentoFim(String horaFuncionamentoFim) {
        this.horaFuncionamentoFim = horaFuncionamentoFim;
    }

    public Integer getTempoConsultaEmMin() {
        return tempoConsultaEmMin;
    }

    public void setTempoConsultaEmMin(Integer tempoConsultaEmMin) {
        this.tempoConsultaEmMin = tempoConsultaEmMin;
    }

    public List<HorarioForm> getAgendaHorarios() {
        return agendaHorarios;
    }

    public void setAgendaHorarios(List<HorarioForm> agendaHorarios) {
        this.agendaHorarios = agendaHorarios;
    }

    public Boolean getAceitaParticular() {
        return aceitaParticular == null ? false : aceitaParticular ;
    }

    public void setAceitaParticular(Boolean aceitaParticular) {
        this.aceitaParticular = aceitaParticular;
    }
}
