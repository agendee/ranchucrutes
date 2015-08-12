package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.ws.exception.LoginServiceException;

/**
 * Created by wagner on 10/08/15.
 */
public interface LoginService {

    String createHashPass(String pass) throws LoginServiceException;

}
