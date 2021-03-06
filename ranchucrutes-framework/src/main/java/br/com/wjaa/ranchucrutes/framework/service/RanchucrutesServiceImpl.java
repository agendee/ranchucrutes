package br.com.wjaa.ranchucrutes.framework.service;

import br.com.wjaa.ranchucrutes.framework.dao.RanchucrutesDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wagner on 18/06/15.
 */
@Service
public class RanchucrutesServiceImpl implements RanchucrutesService {
    private static Log LOG = LogFactory.getLog(RanchucrutesServiceImpl.class);

    @Autowired
    private RanchucrutesDao ranchucrutesDao;

    @Override
    public <T> T get(Class<T> clazz, Serializable id) {
        return this.ranchucrutesDao.get(clazz, id);
    }

    @Override
    public <T> List<T> listAll(Class<T> clazz) {
        return this.ranchucrutesDao.listAll(clazz);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T> T save(T o) {
        return this.ranchucrutesDao.save(o);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public <T> T saveWithRequied(T o) {
        return this.ranchucrutesDao.save(o);
    }

    @Override
    public <T> List<T> getByProperties(Class<T> clazz, String property, Object value) {
        LOG.debug("m=getByProperties, clazz=" + clazz + ", property="+ property + ", value=" + value);
        return this.ranchucrutesDao.getByProperties(clazz,property, value);
    }

    @Override
    public <T> List<T> getByProperties(Class<T> clazz, String [] properties, Object [] values) {
        return this.ranchucrutesDao.getByProperties(clazz,properties, values);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeByProperties(Class clazz, String property, Object value) {
        List<?> list = this.ranchucrutesDao.getByProperties(clazz,property, value);
        if (list.size() > 0 ){
            for (Object o : list){
                this.ranchucrutesDao.remove(o);
            }
        }

    }

}
