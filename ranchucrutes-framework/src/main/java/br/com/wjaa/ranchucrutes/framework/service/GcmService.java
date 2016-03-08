package br.com.wjaa.ranchucrutes.framework.service;

import br.com.wjaa.ranchucrutes.commons.vo.GcmResponseVo;
import br.com.wjaa.ranchucrutes.commons.vo.NotificationVo;
import br.com.wjaa.ranchucrutes.framework.exception.GcmServiceException;

/**
 * Created by wagner on 24/01/16.
 */
public interface GcmService {

    GcmResponseVo sendNotification(NotificationVo notificationVo, String keyDeviceGcm) throws GcmServiceException;

    GcmResponseVo sendNotification(String jsonNotification,String keyDeviceGcm)  throws GcmServiceException;
}
