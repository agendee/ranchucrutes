package br.com.wjaa.ranchucrutes.jobs.service;

import br.com.wjaa.ranchucrutes.commons.utils.DateUtils;
import br.com.wjaa.ranchucrutes.framework.service.GenericServiceImpl;
import br.com.wjaa.ranchucrutes.jobs.dao.NotificacaoDao;
import br.com.wjaa.ranchucrutes.jobs.entity.NotificacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wagner on 04/11/15.
 */
@Service
public class NotificacaoServiceImpl extends GenericServiceImpl<NotificacaoEntity, Long> implements NotificacaoService {


    private NotificacaoDao notificacaoDao;

    @Autowired
    public NotificacaoServiceImpl(NotificacaoDao notificacaoDao) {
        super(notificacaoDao);
        this.notificacaoDao = notificacaoDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void criarNotificacao(NotificacaoEntity.NotificacaoType type, Long idLogin, Long idReferencia){
        NotificacaoEntity ne = new NotificacaoEntity();
        ne.setDataCriacao(DateUtils.now());
        ne.setIdLogin(idLogin);
        ne.setNotificacaoType(type);
        ne.setIdReferencia(idReferencia);
        notificacaoDao.save(ne);
    }
}
