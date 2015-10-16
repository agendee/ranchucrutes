package br.com.wjaa.ranchucrutes.ws.service;
import java.io.Serializable;
import java.util.List;

import br.com.wjaa.ranchucrutes.ws.dao.GenericDao;
import br.com.wjaa.ranchucrutes.ws.entity.AgendaCanceladaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.AgendaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.AgendamentoEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wagner
 * @param <T>
 * @param <PK>
 */
public abstract class GenericServiceImpl<T, PK extends Serializable> implements
        GenericService<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass())
     * from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * GenericDao instance, set by constructor of this class
     */
    protected GenericDao<T, PK> genericDao;

    /**
     * Public constructor for creating a new GenericServiceImpl.
     *
     * @param genericDao the GenericDao to use for persistence
     */
    public GenericServiceImpl(final GenericDao<T, PK> genericDao) {
        this.genericDao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return this.genericDao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return this.genericDao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return this.genericDao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public T save(T object){
        return this.genericDao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void remove(PK id) {
        this.genericDao.remove(id);
    }


}
