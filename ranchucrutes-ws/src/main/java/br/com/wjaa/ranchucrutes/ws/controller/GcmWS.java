package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.form.LoginForm;
import br.com.wjaa.ranchucrutes.commons.vo.PacienteVo;
import br.com.wjaa.ranchucrutes.ws.adapter.PacienteAdapter;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.service.LoginService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wagner on 22/01/16.
 */
public class GcmWS {


    private LoginService loginService;

    @RequestMapping(value = "/gcm/register", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody
    PacienteVo register(@RequestBody String keyDevice, Long idLogin) {
        loginService.
    }

}
