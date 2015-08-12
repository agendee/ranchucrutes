package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.ws.email.EmailTemplate;

/**
 * Created by wagner on 11/08/15.
 */
public interface EmailService {


    void sendEmailNovoMedico(String email, String... params);

}
