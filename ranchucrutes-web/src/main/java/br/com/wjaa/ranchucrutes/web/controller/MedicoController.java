package br.com.wjaa.ranchucrutes.web.controller;

import br.com.wjaa.ranchucrutes.commons.form.FindMedicoForm;
import br.com.wjaa.ranchucrutes.commons.form.MedicoForm;
import br.com.wjaa.ranchucrutes.commons.form.MedicoFullForm;
import br.com.wjaa.ranchucrutes.commons.utils.NumberUtils;
import br.com.wjaa.ranchucrutes.commons.utils.ObjectUtils;
import br.com.wjaa.ranchucrutes.commons.vo.AppConfigVo;
import br.com.wjaa.ranchucrutes.commons.vo.MedicoBasicoVo;
import br.com.wjaa.ranchucrutes.commons.vo.ResultadoBuscaMedicoVo;
import br.com.wjaa.ranchucrutes.web.exception.RestException;
import br.com.wjaa.ranchucrutes.web.exception.RestRequestUnstable;
import br.com.wjaa.ranchucrutes.web.exception.RestResponseUnsatisfiedException;
import br.com.wjaa.ranchucrutes.web.helper.AuthHelper;
import br.com.wjaa.ranchucrutes.web.rest.RestUtils;
import br.com.wjaa.ranchucrutes.web.utils.RanchucrutesConstantes;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wagner on 12/06/15.
 */
@Controller
public class MedicoController {

    private static final Log LOG = LogFactory.getLog(MedicoController.class);



    @RequestMapping("/medico/find")
    public ModelAndView home(@ModelAttribute FindMedicoForm form) {
        ModelAndView mav = new ModelAndView("findDoctor");
        String json = ObjectUtils.toJson(form);
        try {
            ResultadoBuscaMedicoVo resultado = RestUtils.postJson(ResultadoBuscaMedicoVo.class, RanchucrutesConstantes.HOST_WS, "medico/search", json);
            mav.addObject("result", resultado);
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao buscar medicos ", e);
        } catch (RestException e) {
            LOG.error("Erro ao buscar medicos: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage());

        }
        return mav;
    }

    @RequestMapping(value = "/medico/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute MedicoForm form) {
        ModelAndView mav = new ModelAndView("medico/confirme");
        mav.addObject("form", form);
        String json = ObjectUtils.toJson(form);
        try {
            MedicoForm resultado = RestUtils.postJson(MedicoForm.class, RanchucrutesConstantes.HOST_WS, "medico/save", json);
            mav.addObject("result", resultado);
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao cadastrar o medico", e);
            mav.setViewName("medico/cadastro");
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao cadastrar o medico: ErrorMessage " + e.getErrorMessage().getErrorMessage(), e);
            mav.setViewName("medico/cadastro");
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage().getErrorMessage());
        }
        return mav;
    }


    @RequestMapping(value = "/medico/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute MedicoFullForm form, @RequestParam MultipartFile file, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("medico/admin");

        if (!AuthHelper.isAutenticado(request)){
            mav.setViewName("redirect:/medico/login");
            return mav;
        }

        mav.addObject("form", form);
        try {
            doUploadFoto(file,form);
            String json = ObjectUtils.toJson(form);
            MedicoFullForm resultado = RestUtils.postJson(MedicoFullForm.class, RanchucrutesConstantes.HOST_WS, "medico/update", json);
            mav.addObject("form", resultado);
            mav.addObject(RanchucrutesConstantes.SUCCESS_MESSAGE, "Cadastro alterado com sucesso!");
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao alterar os dados do medico ", e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao alterar os dados do medico: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage());
        } catch (Exception ex){
            LOG.error("Erro ao salvar o médico",ex);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro ao salvar os seus dados.");
        }
        return mav;
    }


    @RequestMapping(value = "/medico/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastro() {
        ModelAndView mav = new ModelAndView("medico/cadastro");
        mav.addObject("form",new MedicoForm());
        return mav;
    }


    @RequestMapping(value = "/medico/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("medico/login");

        if (AuthHelper.isAutenticado(request)){
            mav.setViewName("redirect:/medico/agenda");
        }
        return mav;
    }

    @RequestMapping(value = "/medico/admin", method = RequestMethod.GET)
    public ModelAndView admin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("medico/admin");

        if (!AuthHelper.isAutenticado(request)){
            mav.setViewName("redirect:/medico/login");
            return mav;
        }
        MedicoBasicoVo medicoBasico = AuthHelper.getMedico(request);
        try {
            MedicoFullForm form = RestUtils.getJsonWithParamPath(MedicoFullForm.class, RanchucrutesConstantes.HOST_WS,
                    "medico",medicoBasico.getId().toString());
            mav.addObject("form",form);
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao pegar um medico pelo seu id ", e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao buscar um medico pelo seu id: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage());
        }

        return mav;
    }


    @RequestMapping(value = "/medico/horario" +
            "", method = RequestMethod.GET)
    public ModelAndView agenda(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("medico/horario");

        if (!AuthHelper.isAutenticado(request)){
            mav.setViewName("redirect:/medico/login");
            return mav;
        }
        MedicoBasicoVo medicoBasico = AuthHelper.getMedico(request);
        try {
            MedicoFullForm form = RestUtils.getJsonWithParamPath(MedicoFullForm.class, RanchucrutesConstantes.HOST_WS,
                    "medico",medicoBasico.getId().toString());
            mav.addObject("form",form);
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao pegar um medico pelo seu id ", e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao buscar um medico pelo seu id: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject(RanchucrutesConstantes.ERROR_MESSAGE, e.getErrorMessage());
        }
        return mav;
    }


    @RequestMapping(value = "/medico/horario/save", method = RequestMethod.POST)
    public ModelAndView saveHorario(@ModelAttribute MedicoFullForm form, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("medico/horario");

        if (!AuthHelper.isAutenticado(request)){
            mav.setViewName("redirect:/medico/login");
            return mav;
        }

        mav.addObject("form", form);
        try {
            String json = ObjectUtils.toJson(form);
            MedicoFullForm resultado = RestUtils.postJson(MedicoFullForm.class, RanchucrutesConstantes.HOST_WS, "medico/horario/save", json);
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


    @RequestMapping(value = "/medico/agenda", method = RequestMethod.GET)
    public ModelAndView calendario(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("medico/agenda");

        if (!AuthHelper.isAutenticado(request)){
            mav.setViewName("redirect:/medico/login");
        }
        return mav;
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


    private void doUploadFoto(MultipartFile file, MedicoFullForm form) throws IOException, RestResponseUnsatisfiedException, RestRequestUnstable, RestException {

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
            String name = form.getMedico().getCrm() + ext.toLowerCase();
            OutputStream out = new FileOutputStream(new File(path + File.separator + name));

            IOUtils.write(in,out);
            //TODO RESIZE IMAGE
            form.getMedico().setFoto(name);
        }

    }

    public String getCaminhoSaveFoto() throws RestResponseUnsatisfiedException, RestRequestUnstable, RestException {
        AppConfigVo vo = RestUtils.getJsonWithParamPath(AppConfigVo.class, RanchucrutesConstantes.HOST_WS, "appconfig/" + "PATH_FOTO");
        if (vo != null && vo.getValor() != null){
            return vo.getValor();
        }
        return "/var/www/marcmed.com.br/f/";
    }
}
