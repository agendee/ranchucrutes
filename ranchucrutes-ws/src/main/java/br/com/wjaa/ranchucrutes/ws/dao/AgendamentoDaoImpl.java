package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.ws.entity.AgendaCanceladaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.AgendaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.AgendamentoEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 16/10/15.
 */
@Repository
public class AgendamentoDaoImpl extends GenericDaoImpl<AgendamentoEntity, Long> implements AgendamentoDao {


    @Override
    public AgendaEntity getAgendaConfig(Long idProfissional, Long idClinica) {

        StringBuilder sb = new StringBuilder();
        sb.append("select a from " + ProfissionalEntity.class.getName() + " p ");
        sb.append(" join p.clinicas pc ");
        sb.append(" join pc.clinica c ");
        sb.append(" join c.agenda a ");
        sb.append(" where c.id = :idClinica ");
        sb.append(" and p.idLogin = :idProfissional ");
        List<?> resultList = this.getHibernateTemplate().findByNamedParam(
                sb.toString(),
                new String[]{"idClinica", "idProfissional"},
                new Object[]{idClinica, idProfissional}
        );

        if (resultList.size() > 0) {
            return (AgendaEntity) resultList.get(0);
        }
        return null;
    }

    @Override
    public List<AgendaCanceladaEntity> getAgendaCancelada(Long idProfissional, Long idClinica, Date date) {

        StringBuilder sb = new StringBuilder();
        sb.append("select a from " + AgendaCanceladaEntity.class.getName() + " a ");
        sb.append(" where a.idClinica = :idClinica ");
        sb.append(" and a.idProfissional = :idProfissional ");
        sb.append(" and a.dataCancelada = :dataCancelada ");

        Calendar dateSemHora = Calendar.getInstance();
        dateSemHora.setTime(date);
        dateSemHora.set(Calendar.HOUR, 0);
        dateSemHora.set(Calendar.MINUTE, 0);
        dateSemHora.set(Calendar.SECOND, 0);

        List<?> resultList = this.getHibernateTemplate().findByNamedParam(
                sb.toString(),
                new String[]{"idClinica", "idProfissional", "dataCancelada"},
                new Object[]{idClinica, idProfissional, dateSemHora.getTime()}
        );


        return (List<AgendaCanceladaEntity>) resultList;

    }

    @Override
    public List<AgendamentoEntity> getAgendamentos(Long idProfissional, Long idClinica, Date date) {
        StringBuilder sb = new StringBuilder();
        sb.append("select a from " + AgendamentoEntity.class.getName() + " a ");
        sb.append(" where a.idClinica = :idClinica ");
        sb.append(" and a.idProfissional = :idProfissional ");
        sb.append(" and a.dataAgendamento >= :dataIni ");
        sb.append(" and a.dataAgendamento < :dataFim ");

        Calendar dataIni = Calendar.getInstance();
        dataIni.setTime(date);
        dataIni.set(Calendar.HOUR, 0);
        dataIni.set(Calendar.MINUTE, 0);
        dataIni.set(Calendar.SECOND, 0);

        Calendar dataFim = Calendar.getInstance();
        dataFim.setTime(date);
        dataFim.set(Calendar.HOUR, 0);
        dataFim.set(Calendar.MINUTE, 0);
        dataFim.set(Calendar.SECOND, 0);
        dataFim.add(Calendar.DATE,1);


        List<?> resultList = this.getHibernateTemplate().findByNamedParam(
                sb.toString(),
                new String[]{"idClinica", "idProfissional", "dataIni", "dataFim"},
                new Object[]{idClinica, idProfissional, dataIni.getTime(),dataFim.getTime()}
        );


        return (List<AgendamentoEntity>) resultList;
    }

    @Override
    public AgendamentoEntity getAgendamento(Long idProfissional, Long idPaciente, Long idClinica, Date dataAgendamento) {


        StringBuilder sb = new StringBuilder();
        sb.append("select a from " + AgendamentoEntity.class.getName() + " a ");
        sb.append(" where a.idClinica = :idClinica ");
        sb.append(" and a.idProfissional = :idProfissional ");
        sb.append(" and a.idPaciente = :idPaciente ");
        sb.append(" and a.dataAgendamento = :dataAgendamento ");

        List<?> resultList = this.getHibernateTemplate().findByNamedParam(
                sb.toString(),
                new String[]{"idClinica", "idProfissional", "idPaciente", "dataAgendamento"},
                new Object[]{idClinica, idProfissional, idPaciente,dataAgendamento}
        );

        if (resultList.size() > 0){
            return (AgendamentoEntity) resultList.get(0);
        }

        return null;
    }
}
