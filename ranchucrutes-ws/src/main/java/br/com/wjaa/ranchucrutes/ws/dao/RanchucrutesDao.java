package br.com.wjaa.ranchucrutes.ws.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wagner on 18/06/15.
 */
public interface RanchucrutesDao {

    <T>T get(Class<T> clazz, Serializable id);

    <T>List<T> listAll(Class<T> clazz);

    <T> T save(T o);

    <T> List<T> getByProperties(Class<T> clazz, String paramName, Object value);

    <T> List<T> getByProperties(Class<T> clazz, String [] paramName, Object [] value);

    void remove(Object o);
}
