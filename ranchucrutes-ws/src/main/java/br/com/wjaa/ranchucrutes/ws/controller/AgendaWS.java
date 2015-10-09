package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.vo.AgendaVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wagner on 08/10/15.
 */
@RestController
public class AgendaWS {

    @RequestMapping(value = "agenda/{idProfissional}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public AgendaVo getAgenda(@PathVariable String idProfissional){
        return null;
    }


}
