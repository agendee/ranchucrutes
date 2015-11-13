package br.com.wjaa.ranchucrutes.web.controller;

import br.com.wjaa.ranchucrutes.commons.form.*;
import br.com.wjaa.ranchucrutes.commons.utils.DateUtils;
import br.com.wjaa.ranchucrutes.commons.utils.NumberUtils;
import br.com.wjaa.ranchucrutes.commons.utils.ObjectUtils;
import br.com.wjaa.ranchucrutes.commons.vo.*;
import br.com.wjaa.ranchucrutes.web.exception.RestException;
import br.com.wjaa.ranchucrutes.web.exception.RestRequestUnstable;
import br.com.wjaa.ranchucrutes.web.exception.RestResponseUnsatisfiedException;
import br.com.wjaa.ranchucrutes.web.helper.AuthHelper;
import br.com.wjaa.ranchucrutes.web.rest.RestUtils;
import br.com.wjaa.ranchucrutes.web.utils.RanchucrutesConstantes;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 12/06/15.
 */
@Controller
public class ProfissionalController {

    private static final Log LOG = LogFactory.getLog(ProfissionalController.class);



    @RequestMapping("/profissional/find")
    public ModelAndView home(@ModelAttribute FindProfissionalForm form) {
        ModelAndView mav = new ModelAndView("findDoctor");
        String json = ObjectUtils.toJson(form);
        try {
            ResultadoBuscaProfissionalVo resultado = RestUtils.postJson(ResultadoBuscaProfissionalVo.class, RanchucrutesConstantes.HOST_WS, "profissional/search", json);
            mav.addObject("result", resultado);
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao buscar profissional ", e);
        } catch (RestException e) {
            LOG.error("Erro ao buscar profissionals: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage());

        }
        return mav;
    }

    @RequestMapping(value = "/profissional/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute ProfissionalForm form) {
        ModelAndView mav = new ModelAndView("profissional/confirme");
        mav.addObject("form", form);
        String json = ObjectUtils.toJson(form);
        try {
            ProfissionalForm resultado = RestUtils.postJson(ProfissionalForm.class, RanchucrutesConstantes.HOST_WS, "profissional/save", json);
            mav.addObject("result", resultado);
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao cadastrar o profissional", e);
            mav.setViewName("profissional/cadastro");
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao cadastrar o profissional: ErrorMessage " + e.getErrorMessage().getErrorMessage(), e);
            mav.setViewName("profissional/cadastro");
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage().getErrorMessage());
        }
        return mav;
    }


    @RequestMapping(value = "/profissional/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute ProfissionalFullForm form, @RequestParam MultipartFile file, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("profissional/admin");
        this.ignorarIndexVazioClinica(form);
        mav.addObject("form", form);
        try {
            doUploadFoto(file,form);
            String json = ObjectUtils.toJson(form);
            ProfissionalFullForm resultado = RestUtils.postJson(ProfissionalFullForm.class, RanchucrutesConstantes.HOST_WS, "profissional/update", json);
            mav.addObject("form", resultado);
            mav.addObject(RanchucrutesConstantes.SUCCESS_MESSAGE, "Cadastro alterado com sucesso!");
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao alterar os dados do profissional ", e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao alterar os dados do profissional: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage().getErrorMessage());
        } catch (Exception ex){
            LOG.error("Erro ao salvar o profissional",ex);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro ao salvar os seus dados.");
        }
        return mav;
    }



    @RequestMapping(value = "/profissional/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastro() {
        ModelAndView mav = new ModelAndView("profissional/cadastro");
        mav.addObject("form",new ProfissionalForm());
        return mav;
    }


    @RequestMapping(value = "/profissional/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("profissional/login");
        return mav;
    }

    @RequestMapping(value = "/profissional/admin", method = RequestMethod.GET)
    public ModelAndView admin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("profissional/admin");
        ProfissionalBasicoVo profissionalBasico = AuthHelper.getProfissional(request);
        try {
            ProfissionalFullForm form = RestUtils.getJsonWithParamPath(ProfissionalFullForm.class, RanchucrutesConstantes.HOST_WS,
                    "profissional",profissionalBasico.getId().toString());
            mav.addObject("form",form);
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao pegar um profissional pelo seu id ", e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao buscar um profissional pelo seu id: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage());
        }

        return mav;
    }


    @RequestMapping(value = "/profissional/horario", method = RequestMethod.GET)
    public ModelAndView agenda(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("profissional/horario");
        ProfissionalBasicoVo profissionalBasico = AuthHelper.getProfissional(request);
        try {
            ProfissionalFullForm form = RestUtils.getJsonWithParamPath(ProfissionalFullForm.class, RanchucrutesConstantes.HOST_WS,
                    "profissional",profissionalBasico.getId().toString());
            mav.addObject("form",form);
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao pegar um profissional pelo seu id ", e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao buscar um profissional pelo seu id: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage());
        }
        return mav;
    }


    @RequestMapping(value = "/profissional/horario/save", method = RequestMethod.POST)
    public ModelAndView saveHorario(@ModelAttribute ProfissionalFullForm form, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("profissional/horario");

        //TODO VERIFICAR UM MODO MELHOR, AQUI ESTAREI IGNORANDO OS OBJETOS QUE VIERAM NULOS NAS LISTA.
        this.ignorarIndexVazioAgendaHorario(form);

        mav.addObject("form", form);
        try {
            String json = ObjectUtils.toJson(form);
            ProfissionalFullForm resultado = RestUtils.postJson(ProfissionalFullForm.class, RanchucrutesConstantes.HOST_WS, "profissional/horario/save", json);
            mav.addObject("form", resultado);
            mav.addObject(RanchucrutesConstantes.SUCCESS_MESSAGE, "Horário alterado com sucesso!");
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao alterar os horários ", e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao alterar os horários: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage().getErrorMessage());
        } catch (Exception ex){
            LOG.error("Erro ao salvar os horários",ex);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro ao alterar os horários");
        }
        return mav;
    }

    /**
     * Ignorando os objetos vazios criados pelo spring quando nao se usa um index no formulario.
     * @param form
     */
    private void ignorarIndexVazioAgendaHorario(ProfissionalFullForm form) {
        if ( !CollectionUtils.isEmpty(form.getClinicas()) ){
            for (ClinicaForm c : form.getClinicas()){
                c.setAgendaHorarios(this.ignorarIndexVazioAgendaHorario(c.getAgendaHorarios()));
            }
        }
    }

    private List<HorarioForm> ignorarIndexVazioAgendaHorario(List<HorarioForm> agendaHorarios) {
        List<HorarioForm> hfs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(agendaHorarios)){
            for (HorarioForm ah :agendaHorarios){
                /*
                   Limpando os objetos vazios que foram criados pelo spring.
                    esses objetos são criados quando a view pula algum index da lista exemplo
                    clinica[0].horario[2].id
                    horario[0] e horario[1] serao criados objetos vazios pelos spring.
                */
                if (ah.getId() == null &&
                    ah.getDiasSemana() == null &&
                    StringUtils.isBlank(ah.getHoraFim()) &&
                    StringUtils.isBlank(ah.getHoraIni())){
                    continue;
                }
                hfs.add(ah);
            }
        }

        return hfs;
    }


    @RequestMapping(value = "/profissional/agenda", method = RequestMethod.GET)
    public ModelAndView calendario(@ModelAttribute CalendarioAgendamentoForm calendarioForm, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("profissional/agenda");

        try {
            CalendarioAgendamentoVo calendario = this.getCalendarioAgendamentoVo(calendarioForm, request);
            mav.addObject("calendario", calendario);
            mav.addObject("calendarioJson", ObjectUtils.toJson(calendario));
            mav.addObject("idProfissional",AuthHelper.getProfissional(request).getId());
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e ) {
            LOG.error("Erro ao buscar o calendario de agendamentos do profissional", e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Erro ao buscar o calendário de agendamentos");
        } catch (RestException e) {
            LOG.error("Erro ao buscar o calendario de agendamentos do profissional: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage().getErrorMessage());
        }
        return mav;
    }

    @RequestMapping(value = "/profissional/agenda/json", method = RequestMethod.POST)
    public @ResponseBody CalendarioAgendamentoVo calendarioJson(@ModelAttribute CalendarioAgendamentoForm calendarioForm, HttpServletRequest request) {
        try {
            return getCalendarioAgendamentoVo(calendarioForm,request);
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e ) {
            LOG.error("Erro ao buscar o calendario de agendamentos do profissional", e);
            return null;
        } catch (RestException e) {
            LOG.error("Erro ao buscar o calendario de agendamentos do profissional: ErrorMessage " + e.getErrorMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/profissional/agenda/rejeitar", method = RequestMethod.POST)
    public @ResponseBody AgendamentoVo rejeitarSolicitacao(@ModelAttribute RejeicaoSolicitacaoForm rejeicaoSolicitacaoForm, HttpServletRequest request)
            throws RestResponseUnsatisfiedException, RestRequestUnstable, RestException {
        String json = ObjectUtils.toJson(rejeicaoSolicitacaoForm);
        return RestUtils.postJson(AgendamentoVo.class, RanchucrutesConstantes.HOST_WS, "/agendamento/rejeitarSolicitacao/",
                    json);

    }

    @RequestMapping(value = "/profissional/agenda/confirmar", method = RequestMethod.POST)
    public @ResponseBody AgendamentoVo confirmarSolicitacao(@RequestParam Integer idAgendamento, HttpServletRequest request)
            throws RestResponseUnsatisfiedException, RestRequestUnstable, RestException {
        return RestUtils.getJsonWithParamPath(AgendamentoVo.class, RanchucrutesConstantes.HOST_WS, "/agendamento/aprovarSolicitacao/",
                idAgendamento.toString());

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
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, NumberUtils.getFormat(), true));
    }


    private void doUploadFoto(MultipartFile file, ProfissionalFullForm form) throws IOException, RestResponseUnsatisfiedException, RestRequestUnstable, RestException {

        if (file != null && !file.isEmpty()){
            String path = this.getCaminhoSaveFoto();
            byte [] in = file.getBytes();
            String originalName = file.getOriginalFilename();
            String ext = "";
            if (originalName.contains(".")){
                ext =  originalName.substring(originalName.lastIndexOf("."),originalName.length());
            }else{
                ext = originalName.substring(originalName.length()-3,originalName.length());
            }
            String name = form.getProfissional().getCrm() + ext.toLowerCase();
            OutputStream out = new FileOutputStream(new File(path + File.separator + name));

            IOUtils.write(in,out);
            //TODO RESIZE IMAGE
            form.getProfissional().setFoto(name);
        }

    }


    private CalendarioAgendamentoVo getCalendarioAgendamentoVo(@ModelAttribute CalendarioAgendamentoForm calendarioForm, HttpServletRequest request) throws RestResponseUnsatisfiedException, RestException, RestRequestUnstable {
        ProfissionalBasicoVo profissionalBasicoVo = AuthHelper.getProfissional(request);
        if (calendarioForm == null || calendarioForm.getIdProfissional() == null) {
            calendarioForm = new CalendarioAgendamentoForm();
            calendarioForm.setIdProfissional(profissionalBasicoVo.getId());
            calendarioForm.setDataIni(DateUtils.getFirstDayActualWeek());
            calendarioForm.setDataFim(DateUtils.getLastDayActualWeek());
        }

        if (calendarioForm.getIdClinica() == null){

            return RestUtils.getJsonWithParamPath(CalendarioAgendamentoVo.class, RanchucrutesConstantes.HOST_WS, "/profissional/agendamentos/",
                    calendarioForm.getIdProfissional().toString(),
                    DateUtils.formatyyyyMMdd(calendarioForm.getDataIni()),
                    DateUtils.formatyyyyMMdd(calendarioForm.getDataFim()));

        }
        return RestUtils.getJsonWithParamPath(CalendarioAgendamentoVo.class, RanchucrutesConstantes.HOST_WS, "/profissional/agendamentos/",
                calendarioForm.getIdProfissional().toString(),
                calendarioForm.getIdClinica().toString(),
                DateUtils.formatyyyyMMdd(calendarioForm.getDataIni()),
                DateUtils.formatyyyyMMdd(calendarioForm.getDataFim()));
    }

    private String getCaminhoSaveFoto() throws RestResponseUnsatisfiedException, RestRequestUnstable, RestException {
        AppConfigVo vo = RestUtils.getJsonWithParamPath(AppConfigVo.class, RanchucrutesConstantes.HOST_WS, "appconfig/" + "PATH_FOTO");
        if (vo != null && vo.getValor() != null){
            return vo.getValor();
        }
        //TODO ISSO DEVERIA ESTAR NA TABELA DE CONFIG.
        return "/var/www/agendee.com.br/f/";
    }


    /**
     * Ignoreando os objetos que veem vazios do form.
     * Se no form você pular algum index de uma lista o spring acaba criando o objeto nulo.
     * exemplo:
     * clinicas[2].nome
     *
     * clinicas[0] e clinicas[1] o spring criará objetos ClinicaForm vazios.
     *
     * DEVE HAVER UMA MANEIRA MELHOR DE ARRUMAR ISSO, MAS ESSA É A SOLUCAO DO MOMENTO.
     *
     * @param form
     */
    private void ignorarIndexVazioClinica(ProfissionalFullForm form) {
        if (!CollectionUtils.isEmpty(form.getClinicas())){
            List<ClinicaForm> clinicas = new ArrayList<>();
            for (ClinicaForm cf : form.getClinicas()){
                if (StringUtils.isBlank(cf.getNome()) &&
                        cf.getEndereco() == null &&
                        cf.getAgendaHorarios() == null &&
                        cf.getCategorias() == null &&
                        cf.getTempoConsultaEmMin() == null ){
                    continue;
                }
                clinicas.add(cf);
            }
            form.setClinicas(clinicas);
        }

    }



    @ExceptionHandler({RestResponseUnsatisfiedException.class, RestRequestUnstable.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(Exception e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return new ErrorMessageVo(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

    @ExceptionHandler(RestException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessageVo handleException(RestException e, HttpServletResponse response) {
        LOG.error("handleException",e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return e.getErrorMessage();
    }
}
