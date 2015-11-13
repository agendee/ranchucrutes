package br.com.wjaa.ranchucrutes.jobs.service;

import br.com.wjaa.ranchucrutes.commons.utils.DateUtils;
import br.com.wjaa.ranchucrutes.jobs.dao.AgendamentoDao;
import br.com.wjaa.ranchucrutes.jobs.entity.AgendamentoEntity;
import br.com.wjaa.ranchucrutes.jobs.entity.NotificacaoEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by wagner on 04/11/15.
 */
@Service
public class CancelarAgendamentosExpiradosJobImpl implements CancelarAgendamentosExpiradosJob {

    private static Log LOG = LogFactory.getLog(CancelarAgendamentosExpiradosJobImpl.class);

    @Autowired
    private AgendamentoDao agendamentoDao;

    @Autowired
    private NotificacaoService notificacaoService;

    private static final int LIMIT_EXPIRED_IN_HOURS = -2;


    //delay de 30min e roda a cada 2:10 min
    @Scheduled(initialDelay=1800000,fixedRate=7560000)
    @Transactional(propagation = Propagation.REQUIRES_NEW )
    @Override
    public void execute() {
        LOG.info("Iniciando o cancelamento de agendamentos expirados...");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY,LIMIT_EXPIRED_IN_HOURS);

        List<AgendamentoEntity> agendamentosExpirados = agendamentoDao.listAgendamentosExpirados(c.getTime());
        LOG.info("Agendamentos a serem expirados = " + agendamentosExpirados.size());

        for (AgendamentoEntity agendamentoEntity : agendamentosExpirados){
            agendamentoEntity.setCancelado(true);
            agendamentoEntity.setDataCancelamento(DateUtils.now());
            agendamentoDao.save(agendamentoEntity);
            notificacaoService.criarNotificacao(NotificacaoEntity.NotificacaoType.NOTIFICACAO_CANCELAMENTO,
                    agendamentoEntity.getIdPaciente(),
                    agendamentoEntity.getId());
        }
        LOG.info("Fim da execucao...");
    }
}
