package br.com.wjaa.ranchucrutes.ws.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wagner on 18/06/15.
 */
public interface RanchucrutesService {

    <T>T get(Class<T> clazz, Serializable id);

    <T>List<T> listAll(Class<T> clazz);

    <T>T save(T o);

    <T> T saveWithRequied(T o);

    <T>List<T> getByProperties(Class<T> clazz,String property, Object value);

    void removeByProperties(Class clazz,String property, Object value);
}
