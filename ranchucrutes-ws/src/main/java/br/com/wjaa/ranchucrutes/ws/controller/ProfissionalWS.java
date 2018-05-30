package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.form.FindClinicaForm;
import br.com.wjaa.ranchucrutes.commons.form.FindProfissionalForm;
import br.com.wjaa.ranchucrutes.commons.form.ProfissionalForm;
import br.com.wjaa.ranchucrutes.commons.form.ProfissionalFullForm;
import br.com.wjaa.ranchucrutes.commons.utils.NumberUtils;
import br.com.wjaa.ranchucrutes.commons.vo.*;
import br.com.wjaa.ranchucrutes.ws.adapter.ProfissionalAdapter;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import br.com.wjaa.ranchucrutes.ws.exception.*;
import br.com.wjaa.ranchucrutes.ws.service.AgendamentoService;
import br.com.wjaa.ranchucrutes.ws.service.ClinicaService;
import br.com.wjaa.ranchucrutes.ws.service.ProfissionalService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 12/06/15.
 */
@RestController
public class ProfissionalWS extends BaseWS {

    private static final Log LOG = LogFactory.getLog(ProfissionalWS.class);


    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private ClinicaService clinicaService;

    @Autowired
    private AgendamentoService agendamentoService;


    @RequestMapping(value = "/profissional/{id}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8", method = {RequestMethod.GET, RequestMethod.POST})
    public ProfissionalFullForm getProfissionalById(@PathVariable Long id) {
        ProfissionalEntity entity = this.profissionalService.get(id);
        ProfissionalFullForm profissionalForm = ProfissionalAdapter.toProfissionalFullForm(entity);
        
        System.out.println("SENHA" + profissionalForm.getProfissional().getSenha());

        return ProfissionalAdapter.toProfissionalFullForm(entity);
    }

    @RequestMapping(value = "/profissional/basico/{id}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8", method = {RequestMethod.GET, RequestMethod.POST})
    public ProfissionalBasicoVo getProfissionalBasico(@PathVariable Long id) {
        return this.profissionalService.getProfissionalBasico(id);

    }

    @RequestMapping(value = "/profissional/search", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultadoBuscaProfissionalVo findProfissional(@RequestBody FindProfissionalForm form) throws CepNotFoundException,
            LocationDuplicateFoundException, LocationNotFoundException {
       return this.profissionalService.find(form);
    }

    @RequestMapping(value = "/profissional/name/search", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<ProfissionalBasicoVo> findProfissional(@RequestParam String startName) throws CepNotFoundException,
            LocationDuplicateFoundException, LocationNotFoundException {
        return this.profissionalService.findByStartName(startName);
    }

    @RequestMapping(value = "/profissional/clinica/search", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultadoBuscaClinicaVo findProfissionalInClinica(@RequestBody FindClinicaForm form) throws ClinicaServiceException {
        return this.clinicaService.find(form);
    }


    @RequestMapping(value = "/profissional/save", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody
    ProfissionalForm saveProfissional(@RequestBody final ProfissionalForm form) throws ProfissionalServiceException {
        ProfissionalEntity profissional = ProfissionalAdapter.fromProfissionalForm(form);
        ProfissionalEntity entity = this.profissionalService.saveProfissional(profissional);
        
        return ProfissionalAdapter.toProfissionalForm(entity);
    }
    
    @RequestMapping(value = "/profissional/recuperarsenha", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody
    ProfissionalForm recuperarSenhaProfissional(@RequestBody final ProfissionalForm form) throws ProfissionalServiceException {
        ProfissionalEntity profissional = ProfissionalAdapter.fromProfissionalForm(form);
        ProfissionalEntity entity =  this.profissionalService.recuperarSenha(profissional);
        return ProfissionalAdapter.toProfissionalForm(entity);
    }
    
    
    @RequestMapping(value = "/profissional/alterarsenha", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody
    ProfissionalForm alterarSenhaProfissional(@RequestBody final ProfissionalForm form) throws ProfissionalServiceException {
    	ProfissionalEntity profissional = ProfissionalAdapter.fromProfissionalForm(form);
        ProfissionalEntity entity =  this.profissionalService.alterarSenha(profissional);
        return ProfissionalAdapter.toProfissionalForm(entity);
    }
    
    


    @RequestMapping(value = "/profissional/update", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody
    ProfissionalFullForm update(@RequestBody final ProfissionalFullForm form) throws ProfissionalServiceException {
    	try {
        ProfissionalEntity profissional = ProfissionalAdapter.fromProfissionalFullForm(form);
        ProfissionalEntity profissionalUpdated = this.profissionalService.update(profissional);
        //a atualizacao do profissional é feita em 2 transacoes
        //buscando os dados atualizados.
        profissionalUpdated = this.profissionalService.get(profissionalUpdated.getIdLogin());
        return ProfissionalAdapter.toProfissionalFullForm(profissionalUpdated);
    	}catch(Exception e ) {
    		e.printStackTrace();
    	}
    	return null;
    }


    @RequestMapping(value = "/profissional/horario/save", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody
    ProfissionalFullForm saveHorario(@RequestBody final ProfissionalFullForm form) throws ProfissionalServiceException {
        ProfissionalEntity profissional = ProfissionalAdapter.fromProfissionalFullForm(form);
        this.profissionalService.saveAgendaHorarios(profissional.getClinicas());
        ProfissionalEntity profissionalUpdated = this.profissionalService.get(form.getProfissional().getIdLogin());
        return ProfissionalAdapter.toProfissionalFullForm(profissionalUpdated);
    }

    @RequestMapping(value = "/profissional/agendamentos/{idProfissional}/{dateIni}/{dateFim}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"
            ,method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    CalendarioAgendamentoVo getAgendamentos (@PathVariable Long idProfissional,
                                             @PathVariable Date dateIni, @PathVariable Date dateFim) throws AgendamentoServiceException {
        return agendamentoService.getAgendamentosProfissional(idProfissional, dateIni, dateFim);
    }

    @RequestMapping(value = "/profissional/agendamentos/{idProfissional}/{idClinica}/{dateIni}/{dateFim}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
            method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    CalendarioAgendamentoVo getAgendamentos (@PathVariable Long idProfissional,@PathVariable Long idClinica,
                                             @PathVariable Date dateIni, @PathVariable Date dateFim) throws AgendamentoServiceException {
        return agendamentoService.getAgendamentosProfissional(idProfissional,idClinica, dateIni, dateFim);
    }




    @ExceptionHandler(CepNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(CepNotFoundException e, HttpServletResponse response) {
        LOG.error("handleException, error=" + e.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Cep não encontrado.");
    }

    @ExceptionHandler(LocationDuplicateFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(LocationDuplicateFoundException e, HttpServletResponse response) {
        LOG.error("handleException, error=" + e.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Encontrado mais de uma localidade para o cep.");
    }

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(LocationNotFoundException e, HttpServletResponse response) {
        LOG.error("handleException, error=" + e.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Não encontrou nenhuma localidade para o cep.");
    }


    @ExceptionHandler(ProfissionalServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(ProfissionalServiceException e, HttpServletResponse response) {
        LOG.error("handleException, error=" + e.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

    @ExceptionHandler(ClinicaServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(ClinicaServiceException e, HttpServletResponse response) {
        LOG.error("handleException, error=" + e.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

    @ExceptionHandler(AgendamentoServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(AgendamentoServiceException e, HttpServletResponse response) {
        LOG.error("handleException, error=" + e.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }


    @InitBinder
    public void binder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf,true){

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try{
                    super.setAsText(text);
                }catch (IllegalArgumentException ex) {
                    LOG.warn("Erro no parse da data=" + text);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d;
                    try {
                        d = sdf.parse(text);
                        setValue(d);
                    } catch (ParseException e) {
                        LOG.warn("Erro no parse da data=" + text);
                        throw new IllegalArgumentException("Could not parse date: " + e.getMessage(), e);
                    }

                }

            }
        });
    }


}
