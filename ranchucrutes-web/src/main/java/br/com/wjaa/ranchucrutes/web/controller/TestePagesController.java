package br.com.wjaa.ranchucrutes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wagner on 19/08/15.
 */
@Controller
public class TestePagesController {

    @RequestMapping(value = "/medico/testeConfirme", method = RequestMethod.GET)
    public String testeConfirme() {
        return "medico/confirme";
    }

    @RequestMapping(value = "/auth/testeConfirme", method = RequestMethod.GET)
    public String authTesteConfirme() {
        return "auth/confirmacao";
    }

    @RequestMapping(value = "/medico/testeAdmin", method = RequestMethod.GET)
    public String medicoAdmin() {
        return "medico/admin";
    }

}
