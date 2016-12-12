package br.com.wjaa.ranchucrutes.framework.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate4.HibernateTemplate;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wagner on 18/06/15.
 */
@Repository
public class RanchucrutesDaoImpl implements RanchucrutesDao {

    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;

    @Override
    public <T> T get(Class<T> clazz, Serializable id) {
        return this.hibernateTemplate.get(clazz,id);
    }

    @Override
    public <T> List<T> listAll(Class<T> clazz) {
        return this.hibernateTemplate.loadAll(clazz);
    }

    @Override
    public <T> T save(T o) {
        o = this.hibernateTemplate.merge(o);
        this.hibernateTemplate.flush();
        return o;
    }

    @Override
    public <T> List<T> getByProperties(Class<T> clazz, String paramName, Object value) {
        Assert.notNull(paramName,"paramName nao pode estar nulo");
        Assert.notNull(value,"value nao pode estar nulo");
        StringBuilder sb = new StringBuilder();
        sb.append(" from " + clazz.getSimpleName());
        sb.append( String.format(" c where c.%s = :arg1",paramName));
        return (List<T>) this.hibernateTemplate.findByNamedParam(sb.toString(), "arg1", value);
    }

    @Override
    public <T> List<T> getByProperties(Class<T> clazz, String [] paramsName, Object [] values) {
        Assert.noNullElements(paramsName, "paramName nao pode estar nulo");
        Assert.noNullElements(values, "value nao pode estar nulo");
        StringBuilder sb = new StringBuilder();
        sb.append(" from " + clazz.getSimpleName());
        sb.append(" c where 1 = 1 ");
        String [] args = new String[paramsName.length];
        int index = 0;
        for(String param : paramsName){
            String arg = "args" + index;
            sb.append(String.format(" and c.%s = :%s",param,arg));
            args[index] = arg;
            index++;
        }
        return (List<T>) this.hibernateTemplate.findByNamedParam(sb.toString(), args, values);
    }

    @Override
    public <T> T getSingleRecordByProperties(Class<T> clazz, String paramName, Object value) {
        List<T> result = this.getByProperties(clazz, paramName,value);
        if (result.size() > 0){
            return result.get(0);
        }
        return null;
    }

    @Override
    public <T> T getSingleRecordByProperties(Class<T> clazz, String[] paramsName, Object[] values) {
        List<T> result = this.getByProperties(clazz, paramsName,values);
        if (result.size() > 0){
            return result.get(0);
        }
        return null;
    }

    @Override
    public void remove(Object o) {
        this.hibernateTemplate.delete(o);
    }

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
}
