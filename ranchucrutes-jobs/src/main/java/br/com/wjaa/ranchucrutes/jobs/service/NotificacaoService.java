package br.com.wjaa.ranchucrutes.jobs.service;

import br.com.wjaa.ranchucrutes.framework.service.GenericService;
import br.com.wjaa.ranchucrutes.jobs.entity.NotificacaoEntity;

/**
 * Created by wagner on 04/11/15.
 */
public interface NotificacaoService extends GenericService<NotificacaoEntity,Long> {


    void criarNotificacao(NotificacaoEntity.NotificacaoType type, Long idLogin, Long idReferencia);



}
