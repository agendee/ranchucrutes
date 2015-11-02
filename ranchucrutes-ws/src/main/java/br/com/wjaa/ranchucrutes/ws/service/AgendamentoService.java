package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.form.AgendamentoForm;
import br.com.wjaa.ranchucrutes.commons.vo.AgendaVo;
import br.com.wjaa.ranchucrutes.commons.vo.AgendamentoVo;
import br.com.wjaa.ranchucrutes.commons.vo.ConfirmarAgendamentoVo;
import br.com.wjaa.ranchucrutes.ws.entity.AgendamentoEntity;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import br.com.wjaa.ranchucrutes.ws.exception.AgendamentoServiceException;

import java.sql.SQLException;

/**
 * Created by wagner on 15/10/15.
 */
public interface AgendamentoService extends GenericService<AgendamentoEntity, Long> {
    ConfirmarAgendamentoVo criarAgendamento(AgendamentoForm form) throws AgendamentoServiceException;

    AgendaVo getAgendaProfissional(Long idProfissional, Long idClinica) throws AgendamentoServiceException;

    ConfirmarAgendamentoVo criarAgendamentoNovaTransaction(AgendamentoForm form, PacienteEntity pacienteEntity,
                                                                  ProfissionalEntity profissionalEntity) throws SQLException;

    AgendamentoVo confirmarAgendamento(Long idAgendamento, String codigo) throws AgendamentoServiceException;

    AgendamentoVo confirmarConsulta(Long idAgendamento, Boolean confirma);
}