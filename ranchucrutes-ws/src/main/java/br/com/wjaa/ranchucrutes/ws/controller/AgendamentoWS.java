package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.form.AgendamentoForm;
import br.com.wjaa.ranchucrutes.commons.form.RejeicaoSolicitacaoForm;
import br.com.wjaa.ranchucrutes.commons.vo.AgendaVo;
import br.com.wjaa.ranchucrutes.commons.vo.AgendamentoVo;
import br.com.wjaa.ranchucrutes.commons.vo.ConfirmarAgendamentoVo;
import br.com.wjaa.ranchucrutes.commons.vo.ErrorMessageVo;
import br.com.wjaa.ranchucrutes.ws.adapter.AgendamentoAdapter;
import br.com.wjaa.ranchucrutes.ws.adapter.RanchucrutesAdapter;
import br.com.wjaa.ranchucrutes.ws.exception.AgendamentoServiceException;
import br.com.wjaa.ranchucrutes.ws.exception.ParceiroIntegracaoServiceException;
import br.com.wjaa.ranchucrutes.ws.service.AgendamentoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wagner on 08/10/15.
 */
@RestController
public class AgendamentoWS {

    private static final Log LOG = LogFactory.getLog(AgendamentoWS.class);

    @Autowired
    private AgendamentoService agendamentoService;

    @RequestMapping(value = "/agendamento/{idProfissional}/{idClinica}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public AgendaVo getAgendaProfissional(@PathVariable Long idProfissional, @PathVariable Long idClinica) throws AgendamentoServiceException, ParceiroIntegracaoServiceException {
        return this.agendamentoService.getAgendaProfissional(idProfissional, idClinica);
    }

    @RequestMapping(value = "/agendamento/criar", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public ConfirmarAgendamentoVo criarAgendamento(@RequestBody AgendamentoForm agendamentoForm) throws AgendamentoServiceException {
        return this.agendamentoService.criarAgendamento(agendamentoForm);
    }

    @RequestMapping(value = "/agendamento/confirmarSolicitacao/{idAgendamento}/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public AgendamentoVo confirmarSolicitacao(@PathVariable Long idAgendamento, @PathVariable String codigo) throws AgendamentoServiceException {
        return this.agendamentoService.confirmarSolicitacao(idAgendamento, codigo);
    }

    @RequestMapping(value = "/agendamento/aprovarSolicitacao/{idAgendamento}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public AgendamentoVo aprovarSolicitacao(@PathVariable Long idAgendamento) throws AgendamentoServiceException {
        return this.agendamentoService.aprovarSolicitacao(idAgendamento);
    }

    @RequestMapping(value = "/agendamento/rejeitarSolicitacao", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8", method = RequestMethod.POST)
    public AgendamentoVo rejeitarSolicitacao(@RequestBody RejeicaoSolicitacaoForm rejeitacaoSolicitacao) throws AgendamentoServiceException {
        return this.agendamentoService.rejeitarSolicitacao(rejeitacaoSolicitacao);
    }

    @RequestMapping(value = "/agendamento/confirmarConsulta/{idAgendamento}/{confirma}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public AgendamentoVo confirmarConsulta(@PathVariable Long idAgendamento, @PathVariable Boolean confirma) throws AgendamentoServiceException, ParceiroIntegracaoServiceException {
        return this.agendamentoService.confirmarConsulta(idAgendamento, confirma);
    }

    @RequestMapping(value = "/agendamento/cancelarConsulta/{idAgendamento}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public AgendamentoVo cancelarConsulta(@PathVariable Long idAgendamento) throws AgendamentoServiceException, ParceiroIntegracaoServiceException {
        return this.agendamentoService.confirmarConsulta(idAgendamento, false);
    }

    @RequestMapping(value = "/agendamento/{idAgendamento}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public AgendamentoVo getAgendamentoById(@PathVariable Long idAgendamento) throws AgendamentoServiceException {
        return this.agendamentoService.getAgendamento(idAgendamento);
    }

    @RequestMapping(value = "/agendamento/list/{idPaciente}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public List<AgendamentoVo> getAgendamentos(@PathVariable Long idPaciente) throws AgendamentoServiceException {
        return this.agendamentoService.getAgendamentosPaciente(idPaciente);
    }


    @ExceptionHandler(AgendamentoServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(AgendamentoServiceException e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

    @ExceptionHandler(ParceiroIntegracaoServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(ParceiroIntegracaoServiceException e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

}
