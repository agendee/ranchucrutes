package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.ws.entity.AgendaCanceladaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.AgendaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.AgendamentoEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        sb.append(" join c.agendas a ");
        sb.append(" where c.id = :idClinica ");
        sb.append(" and p.id = :idProfissional ");
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

        Calendar dateSemHora = Calendar.getInstance(new Locale("pt", "BR"));
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
    public List<AgendaCanceladaEntity> getAgendaCanceladaPosterior(Long idProfissional, Long idClinica, Date hoje) {

        StringBuilder sb = new StringBuilder();
        sb.append("select a from " + AgendaCanceladaEntity.class.getName() + " a ");
        sb.append(" where a.idClinica = :idClinica ");
        sb.append(" and a.idProfissional = :idProfissional ");
        sb.append(" and a.dataCancelada >= :hoje ");

        Calendar dateSemHora = Calendar.getInstance(new Locale("pt", "BR"));
        dateSemHora.setTime(hoje);
        dateSemHora.set(Calendar.HOUR, 0);
        dateSemHora.set(Calendar.MINUTE, 0);
        dateSemHora.set(Calendar.SECOND, 0);

        List<?> resultList = this.getHibernateTemplate().findByNamedParam(
                sb.toString(),
                new String[]{"idClinica", "idProfissional", "hoje"},
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
        sb.append(" and a.cancelado = false ");

        Calendar dataIni = Calendar.getInstance(new Locale("pt", "BR"));
        dataIni.setTime(date);
        dataIni.set(Calendar.HOUR_OF_DAY, 0);
        dataIni.set(Calendar.MINUTE, 0);
        dataIni.set(Calendar.SECOND, 0);



        List<?> resultList = this.getHibernateTemplate().findByNamedParam(
                sb.toString(),
                new String[]{"idClinica", "idProfissional", "dataIni"},
                new Object[]{idClinica, idProfissional, dataIni.getTime()}
        );


        return (List<AgendamentoEntity>) resultList;
    }

    @Override
    public AgendamentoEntity getAgendamento(Long idProfissional, Long idClinica, Date dataAgendamento) {


        StringBuilder sb = new StringBuilder();
        sb.append("select a from " + AgendamentoEntity.class.getName() + " a ");
        sb.append(" where a.idClinica = :idClinica ");
        sb.append(" and a.idProfissional = :idProfissional ");
        sb.append(" and a.dataAgendamento = :dataAgendamento ");
        sb.append(" and a.cancelado = false ");


        List<?> resultList = this.getHibernateTemplate().findByNamedParam(
                sb.toString(),
                new String[]{"idClinica", "idProfissional", "dataAgendamento"},
                new Object[]{idClinica, idProfissional, dataAgendamento}
        );

        if (resultList.size() > 0){
            return (AgendamentoEntity) resultList.get(0);
        }

        return null;
    }

    @Override
    public List<AgendamentoEntity> getAgendamentosPosteriores(Long idProfissional, Long idClinica, Long idPaciente, Date hoje) {
        StringBuilder sb = new StringBuilder();
        sb.append("select a from " + AgendamentoEntity.class.getName() + " a ");
        sb.append(" where a.idClinica = :idClinica ");
        sb.append(" and a.idProfissional = :idProfissional ");
        sb.append(" and a.idPaciente = :idPaciente ");
        sb.append(" and a.dataAgendamento >= :hoje ");
        sb.append(" and a.cancelado = false ");

        List<?> resultList = this.getHibernateTemplate().findByNamedParam(
                sb.toString(),
                new String[]{"idClinica", "idProfissional", "idPaciente", "hoje"},
                new Object[]{idClinica, idProfissional,idPaciente, hoje}
        );

        return (List<AgendamentoEntity>) resultList;
    }

    @Override
    public List<AgendamentoEntity> getAgendamentosPaciente(Long idPaciente) {
        StringBuilder sb = new StringBuilder();
        sb.append("select a from " + AgendamentoEntity.class.getName() + " a ");
        sb.append(" where a.idPaciente = :idPaciente");
        sb.append(" and a.cancelado = false ");

        List<?> resultList = this.getHibernateTemplate().findByNamedParam(
                sb.toString(),
                new String[]{"idPaciente"},
                new Object[]{idPaciente}
        );

        return (List<AgendamentoEntity>) resultList;
    }
}
