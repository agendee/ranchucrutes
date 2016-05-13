package br.com.wjaa.ranchucrutes.ws.integracao.vo;

import br.com.wjaa.ranchucrutes.ws.integracao.camtwo.vo.AgendaProfissionalExternaVO;

/**
 * Created by wagner on 22/04/16.
 */
public class ParceiroAgendamentoVo {

    private String idAgendamento;

    public static ParceiroAgendamentoVo toParceiroAgendamento(AgendaProfissionalExternaVO agendaProfissionalRetorno) {
        ParceiroAgendamentoVo parceiroAgendamento = new ParceiroAgendamentoVo();
        parceiroAgendamento.setIdAgendamento(String.valueOf(agendaProfissionalRetorno.getIdAgenda()));
        return parceiroAgendamento;
    }

    public String getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(String idAgendamento) {
        this.idAgendamento = idAgendamento;
    }
}
