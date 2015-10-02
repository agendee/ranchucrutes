package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.vo.PacienteVo;
import br.com.wjaa.ranchucrutes.ws.adapter.PacienteAdapter;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.exception.MedicoServiceException;
import br.com.wjaa.ranchucrutes.ws.exception.PacienteServiceException;
import br.com.wjaa.ranchucrutes.ws.service.PacienteService;
import br.com.wjaa.ranchucrutes.ws.vo.ErrorMessageVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wagner on 11/09/15.
 */
@RestController
public class PacienteWS {

    private static final Log LOG = LogFactory.getLog(PacienteWS.class);

    @Autowired
    private PacienteService pacienteService;


    @RequestMapping(value = "/paciente/{id}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public PacienteVo getPacienteById(@PathVariable Long id) {
        PacienteEntity entity = this.pacienteService.get(id);
        return PacienteAdapter.toPacienteVo(entity);
    }


    @RequestMapping(value = "/paciente/save", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody
    PacienteVo savePaciente(@RequestBody final PacienteVo pacienteVo) throws PacienteServiceException {
        PacienteEntity pacienteEntity = PacienteAdapter.fromPacienteVo(pacienteVo);
        pacienteEntity = this.pacienteService.savePaciente(pacienteEntity);
        return PacienteAdapter.toPacienteVo(pacienteEntity);
    }

    @RequestMapping(value = "/paciente/update", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody
    PacienteVo updatePaciente(@RequestBody final PacienteVo pacienteVo) throws PacienteServiceException {
        PacienteEntity pacienteEntity = PacienteAdapter.fromPacienteVo(pacienteVo);
        pacienteEntity = this.pacienteService.updatePaciente(pacienteEntity);
        return PacienteAdapter.toPacienteVo(pacienteEntity);
    }

    @ExceptionHandler(PacienteServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(PacienteServiceException e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(IllegalArgumentException e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

}
