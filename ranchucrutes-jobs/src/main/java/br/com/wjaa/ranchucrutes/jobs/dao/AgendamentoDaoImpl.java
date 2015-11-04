package br.com.wjaa.ranchucrutes.jobs.dao;

import br.com.wjaa.ranchucrutes.framework.dao.GenericDaoImpl;
import br.com.wjaa.ranchucrutes.jobs.entity.AgendamentoEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 04/11/15.
 */
@Repository
public class AgendamentoDaoImpl extends GenericDaoImpl<AgendamentoEntity,Long> implements AgendamentoDao {



    @Override
    public List<AgendamentoEntity> listAgendamentosExpirados(Date limitDate) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a from " + AgendamentoEntity.class.getSimpleName() + " a ");
        sql.append(" where a.dataConfirmacao is null ");
        sql.append(" and a.dataConfirmacaoConsulta is null ");
        sql.append(" and a.dataCriacao < :limitDate ");
        sql.append(" and a.cancelado = false ");

        return (List<AgendamentoEntity>) getHibernateTemplate().findByNamedParam(sql.toString(),"limitDate", limitDate);
    }
}
