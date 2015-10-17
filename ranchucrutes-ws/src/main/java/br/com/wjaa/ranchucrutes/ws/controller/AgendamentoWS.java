package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.form.AgendamentoForm;
import br.com.wjaa.ranchucrutes.commons.vo.AgendaVo;
import br.com.wjaa.ranchucrutes.commons.vo.ConfirmarAgendamentoVo;
import br.com.wjaa.ranchucrutes.commons.vo.ErrorMessageVo;
import br.com.wjaa.ranchucrutes.ws.exception.AgendamentoServiceException;
import br.com.wjaa.ranchucrutes.ws.service.AgendamentoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wagner on 08/10/15.
 */
@RestController
public class AgendamentoWS {

    private static final Log LOG = LogFactory.getLog(AgendamentoWS.class);

    @Autowired
    private AgendamentoService agendamentoService;

    @RequestMapping(value = "/agendamento/{idProfissional}/{idClinica}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public AgendaVo getAgendaProfissional(@PathVariable Long idProfissional, @PathVariable Long idClinica) throws AgendamentoServiceException {
        return this.agendamentoService.getAgendaProfissional(idProfissional, idClinica);
    }

    @RequestMapping(value = "/agendamento/criar", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public ConfirmarAgendamentoVo criarAgendamento(@ModelAttribute AgendamentoForm agendamentoForm) throws AgendamentoServiceException {
        return this.agendamentoService.criarAgendamento(agendamentoForm);
    }


    @ExceptionHandler(AgendamentoServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(AgendamentoServiceException e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

}
