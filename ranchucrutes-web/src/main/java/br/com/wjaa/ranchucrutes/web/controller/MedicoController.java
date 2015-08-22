package br.com.wjaa.ranchucrutes.web.controller;

import br.com.wjaa.ranchucrutes.commons.form.FindMedicoForm;
import br.com.wjaa.ranchucrutes.commons.form.MedicoForm;
import br.com.wjaa.ranchucrutes.commons.form.MedicoFullForm;
import br.com.wjaa.ranchucrutes.commons.utils.ObjectUtils;
import br.com.wjaa.ranchucrutes.commons.vo.MedicoBasicoVo;
import br.com.wjaa.ranchucrutes.commons.vo.ResultadoBuscaMedicoVo;
import br.com.wjaa.ranchucrutes.web.exception.RestException;
import br.com.wjaa.ranchucrutes.web.exception.RestRequestUnstable;
import br.com.wjaa.ranchucrutes.web.exception.RestResponseUnsatisfiedException;
import br.com.wjaa.ranchucrutes.web.helper.AuthHelper;
import br.com.wjaa.ranchucrutes.web.rest.RestUtils;
import br.com.wjaa.ranchucrutes.web.utils.RanchucrutesConstantes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
            mav.addObject("errorMessage", e.getErrorMessage());

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
            mav.addObject("errorMessage", "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao cadastrar o medico: ErrorMessage " + e.getErrorMessage(), e);
            mav.setViewName("medico/cadastro");
            mav.addObject("errorMessage", e.getErrorMessage());
        }
        return mav;
    }


    @RequestMapping(value = "/medico/safefull", method = RequestMethod.POST)
    public ModelAndView savefull(@ModelAttribute MedicoFullForm form, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("medico/admin");

        if (!AuthHelper.isAutenticado(request)){
            mav.setViewName("redirect:/medico/login");
            return mav;
        }

        mav.addObject("form", form);
        String json = ObjectUtils.toJson(form);
        try {
            MedicoFullForm resultado = RestUtils.postJson(MedicoFullForm.class, RanchucrutesConstantes.HOST_WS, "medico/update", json);
            mav.addObject("form", resultado);
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao alterar os dados do medico ", e);
            mav.addObject("errorMessage", "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao alterar os dados do medico: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject("errorMessage", e.getErrorMessage());
        }
        return mav;
    }



    @RequestMapping(value = "/medico/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastro() {
        ModelAndView mav = new ModelAndView("medico/cadastro");
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
            mav.addObject("errorMessage", "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao buscar um medico pelo seu id: ErrorMessage " + e.getErrorMessage(), e);
            mav.addObject("errorMessage", e.getErrorMessage());
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



}
