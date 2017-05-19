package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.vo.AppConfigVo;
import br.com.wjaa.ranchucrutes.ws.adapter.RanchucrutesAdapter;
import br.com.wjaa.ranchucrutes.ws.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wagner on 06/09/15.
 */
@RestController
public class AppConfigWS {

    @Autowired
    private AppConfigService appConfigService;


    @RequestMapping(value = "/appconfig/{key}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8",
            method = {RequestMethod.GET, RequestMethod.POST})
    public AppConfigVo getAppConfig(@PathVariable String key){
        return RanchucrutesAdapter.toAppConfigVo(appConfigService.getAppConfigByKey(key));
    }


    @RequestMapping(value = "/appconfig/{key}/{defaultValue}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8",
            method = {RequestMethod.GET, RequestMethod.POST})
    public AppConfigVo getAppConfig(String key,String defaultValue){
        return RanchucrutesAdapter.toAppConfigVo(appConfigService.getAppConfigByKey(key, defaultValue));
    }

}
