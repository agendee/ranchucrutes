package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.vo.GcmResponseVo;
import br.com.wjaa.ranchucrutes.ws.exception.GcmServiceException;

/**
 * Created by wagner on 24/01/16.
 */
public interface GcmService {

    GcmResponseVo sendNotification(Long idSender, String jsonRequest) throws GcmServiceException;

}
