package br.com.wjaa.ranchucrutes.jobs.service;

import br.com.wjaa.ranchucrutes.commons.utils.DateUtils;
import br.com.wjaa.ranchucrutes.commons.vo.NotificationVo;
import br.com.wjaa.ranchucrutes.framework.exception.GcmServiceException;
import br.com.wjaa.ranchucrutes.framework.service.GcmService;
import br.com.wjaa.ranchucrutes.framework.service.GenericServiceImpl;
import br.com.wjaa.ranchucrutes.framework.service.RanchucrutesService;
import br.com.wjaa.ranchucrutes.jobs.dao.NotificacaoDao;
import br.com.wjaa.ranchucrutes.jobs.entity.AgendamentoEntity;
import br.com.wjaa.ranchucrutes.jobs.entity.NotificacaoEntity;
import br.com.wjaa.ranchucrutes.jobs.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.jobs.entity.ProfissionalEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wagner on 04/11/15.
 */
@Service
public class NotificacaoServiceImpl extends GenericServiceImpl<NotificacaoEntity, Long> implements NotificacaoService {


    private static final Log LOG = LogFactory.getLog(NotificacaoServiceImpl.class);


    @Autowired
    private RanchucrutesService ranchucrutesService;

    @Autowired
    private GcmService gcmService;

    private NotificacaoDao notificacaoDao;

    @Autowired
    public NotificacaoServiceImpl(NotificacaoDao notificacaoDao) {
        super(notificacaoDao);
        this.notificacaoDao = notificacaoDao;
    }


    @Override
    public void enviarNotificacaoCancelamento(AgendamentoEntity agendamentoEntity) {
        ProfissionalEntity profissionalEntity = ranchucrutesService.get(ProfissionalEntity.class, agendamentoEntity.getIdProfissional());
        PacienteEntity pacienteEntity = ranchucrutesService.get(PacienteEntity.class, agendamentoEntity.getIdPaciente());

        NotificationVo vo = new NotificationVo(NotificationVo.StatusNotification.CANCELLATION,
                "Sua consulta com o Dr(a) " + profissionalEntity.getNome() + " marcada para " +
                        DateUtils.formatddMMyyyy(agendamentoEntity.getDataAgendamento()) + ", foi cancelada!");


        try {
            LOG.info("Enviando notificao de cancelamento...");
            gcmService.sendNotification(vo,pacienteEntity.getKeyDeviceGcm());
        } catch (GcmServiceException e) {
            //TODO CASO DE ALGUM ERRO PRECISA ENVIAR PARA UMA FILA OU GRAVAR NO BANCO PARA TENTAR NOVAMENTE MAIS TARDE.
            LOG.error("Erro ao enviar notificacao de cancelamento", e);
        }
    }
}
