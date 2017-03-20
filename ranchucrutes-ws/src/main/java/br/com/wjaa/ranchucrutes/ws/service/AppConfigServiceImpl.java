package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.framework.dao.RanchucrutesDao;
import br.com.wjaa.ranchucrutes.ws.entity.AppConfigEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wagner on 06/09/15.
 */
@Service
public class AppConfigServiceImpl implements AppConfigService {

    private static Log LOG = LogFactory.getLog(AppConfigServiceImpl.class);

    @Autowired
    private RanchucrutesDao ranchucrutesDao;

    @Override
    public AppConfigEntity getAppConfigByKey(String key) {
        LOG.debug("m=findWithoutTryCatch key=" + key);
        List<AppConfigEntity> list = ranchucrutesDao.getByProperties(AppConfigEntity.class,"chave",key);
        if (list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public AppConfigEntity getAppConfigByKey(String key, String defaultValue) {
        LOG.debug("m=findWithoutTryCatch key=" + key + " defaultValue=" + defaultValue);
        List<AppConfigEntity> list = ranchucrutesDao.getByProperties(AppConfigEntity.class,"chave",key);
        if (list.size() > 0){
            return list.get(0);
        }

        AppConfigEntity app = new AppConfigEntity();
        app.setChave(key);
        app.setValor(defaultValue);
        return app;

    }
}
