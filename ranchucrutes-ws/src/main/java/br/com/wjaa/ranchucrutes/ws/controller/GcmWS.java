package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.form.LoginForm;
import br.com.wjaa.ranchucrutes.commons.vo.ErrorMessageVo;
import br.com.wjaa.ranchucrutes.commons.vo.GcmResponseVo;
import br.com.wjaa.ranchucrutes.commons.vo.PacienteVo;
import br.com.wjaa.ranchucrutes.ws.adapter.PacienteAdapter;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.exception.GcmServiceException;
import br.com.wjaa.ranchucrutes.ws.service.GcmService;
import br.com.wjaa.ranchucrutes.ws.service.LoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wagner on 22/01/16.
 */
@RestController
public class GcmWS {
    private static final Log LOG = LogFactory.getLog(GcmWS.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private GcmService gcmService;


    @RequestMapping(value = "/gcm/register", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody
    PacienteVo register(@RequestParam Long idLogin, @RequestParam String keyDevice) {
        return loginService.registerGcm(idLogin,keyDevice);
    }

    @RequestMapping(value = "/gcm/send", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody
    GcmResponseVo sendNotification(@RequestParam Long idLogin, @RequestParam String jsonNotification) throws GcmServiceException {
        return gcmService.sendNotification(idLogin,jsonNotification);
    }


    @ExceptionHandler(GcmServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(GcmServiceException e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(Exception e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }
}
