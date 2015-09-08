package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.vo.AppConfigVo;
import br.com.wjaa.ranchucrutes.ws.entity.AppConfigEntity;

/**
 * Created by wagner on 06/09/15.
 */
public interface AppConfigService {


    AppConfigEntity getAppConfigByKey(String key);

    AppConfigEntity getAppConfigByKey(String key, String defaultValue);
}
