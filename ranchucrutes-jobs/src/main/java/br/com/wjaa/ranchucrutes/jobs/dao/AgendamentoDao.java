package br.com.wjaa.ranchucrutes.jobs.dao;

import br.com.wjaa.ranchucrutes.framework.dao.GenericDao;
import br.com.wjaa.ranchucrutes.jobs.entity.AgendamentoEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 04/11/15.
 */
public interface AgendamentoDao extends GenericDao<AgendamentoEntity,Long>{


    List<AgendamentoEntity> listAgendamentosExpirados(Date limitDate);

}
