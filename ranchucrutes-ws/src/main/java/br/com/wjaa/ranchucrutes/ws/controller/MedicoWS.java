package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.form.FindMedicoForm;
import br.com.wjaa.ranchucrutes.commons.form.MedicoForm;
import br.com.wjaa.ranchucrutes.ws.adapter.MedicoAdapter;
import br.com.wjaa.ranchucrutes.commons.vo.ResultadoBuscaMedicoVo;
import br.com.wjaa.ranchucrutes.ws.entity.*;
import br.com.wjaa.ranchucrutes.ws.exception.CepNotFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.LocationDuplicateFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.LocationNotFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.MedicoServiceException;
import br.com.wjaa.ranchucrutes.ws.service.MedicoService;
import br.com.wjaa.ranchucrutes.ws.vo.ErrorMessageVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wagner on 12/06/15.
 */
@RestController
public class MedicoWS extends BaseWS {

    private static final Log LOG = LogFactory.getLog(MedicoWS.class);


    @Autowired
    private MedicoService medicoService;

    @RequestMapping(value = "/medico/{id}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public MedicoEntity getMedicoById(@PathVariable Long id) {
        return this.medicoService.get(id);
    }

    @RequestMapping(value = "/medico/search", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultadoBuscaMedicoVo findMedico(@RequestBody FindMedicoForm form) throws CepNotFoundException,
            LocationDuplicateFoundException, LocationNotFoundException {
       return this.medicoService.find(form);
    }


    @RequestMapping(value = "/medico/save", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody MedicoForm saveMedico(@RequestBody final MedicoForm form) throws MedicoServiceException {
        MedicoEntity medico = MedicoAdapter.fromMedicoForm(form);
        MedicoEntity entity = this.medicoService.save(medico);
        return MedicoAdapter.toMedicoForm(entity);
    }


    @ExceptionHandler(CepNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(CepNotFoundException e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Cep não encontrado.");
    }

    @ExceptionHandler(LocationDuplicateFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(LocationDuplicateFoundException e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Encontrado mais de uma localidade para o cep.");
    }

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(LocationNotFoundException e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Não encontrou nenhuma localidade para o cep.");
    }


    @ExceptionHandler(MedicoServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(MedicoServiceException e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

}
