package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.ws.entity.AgendaCanceladaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.AgendaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.AgendamentoEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 16/10/15.
 */
public interface AgendamentoDao extends GenericDao<AgendamentoEntity, Long>{

    AgendaEntity getAgendaConfig(Long idProfissional, Long idClinica);

    List<AgendaCanceladaEntity> getAgendaCancelada(Long idProfissional, Long idClinica, Date date);

    List<AgendamentoEntity> getAgendamentos(Long idProfissional, Long idClinica, Date date);

    AgendamentoEntity getAgendamento(Long idProfissional, Long idPaciente, Long idClinica, Date dataAgendamento);
}
