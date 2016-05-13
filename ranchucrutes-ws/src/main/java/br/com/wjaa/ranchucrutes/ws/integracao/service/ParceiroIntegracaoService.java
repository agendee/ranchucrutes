package br.com.wjaa.ranchucrutes.ws.integracao.service;

import br.com.wjaa.ranchucrutes.commons.form.AgendamentoForm;
import br.com.wjaa.ranchucrutes.commons.vo.AgendaVo;
import br.com.wjaa.ranchucrutes.ws.integracao.vo.ParceiroAgendamentoVo;
import br.com.wjaa.ranchucrutes.ws.entity.AgendamentoEntity;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import br.com.wjaa.ranchucrutes.ws.exception.ParceiroIntegracaoServiceException;

/**
 * Created by wagner on 4/15/16.
 */
public interface ParceiroIntegracaoService {


    AgendaVo getAgenda(Long idProfissional, Long idClinica) throws ParceiroIntegracaoServiceException;


    ParceiroAgendamentoVo criarAgendamento(AgendamentoForm form, ProfissionalEntity profissionalEntity, PacienteEntity pacienteEntity)
            throws ParceiroIntegracaoServiceException;

    void confirmarAgendamento(AgendamentoEntity agendamento, Boolean confirma) throws ParceiroIntegracaoServiceException;
}
