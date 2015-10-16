package br.com.wjaa.ranchucrutes.commons.vo;

/**
 * Created by wagner on 15/10/15.
 */
public class ConfirmarAgendamentoVo {

    private Long id;
    private String codigoConfirmacao;
    private AgendamentoVo agendamentoVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoConfirmacao() {
        return codigoConfirmacao;
    }

    public void setCodigoConfirmacao(String codigoConfirmacao) {
        this.codigoConfirmacao = codigoConfirmacao;
    }

    public AgendamentoVo getAgendamentoVo() {
        return agendamentoVo;
    }

    public void setAgendamentoVo(AgendamentoVo agendamentoVo) {
        this.agendamentoVo = agendamentoVo;
    }
}
