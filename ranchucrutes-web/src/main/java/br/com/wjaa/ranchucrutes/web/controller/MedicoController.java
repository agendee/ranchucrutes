package br.com.wjaa.ranchucrutes.web.controller;

import br.com.wjaa.ranchucrutes.commons.form.FindMedicoForm;
import br.com.wjaa.ranchucrutes.commons.form.MedicoForm;
import br.com.wjaa.ranchucrutes.commons.utils.ObjectUtils;
import br.com.wjaa.ranchucrutes.commons.vo.ResultadoBuscaMedicoVo;
import br.com.wjaa.ranchucrutes.web.exception.RestException;
import br.com.wjaa.ranchucrutes.web.exception.RestRequestUnstable;
import br.com.wjaa.ranchucrutes.web.exception.RestResponseUnsatisfiedException;
import br.com.wjaa.ranchucrutes.web.rest.RestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
            ResultadoBuscaMedicoVo resultado = RestUtils.postJson(ResultadoBuscaMedicoVo.class, "rest.marcmed.com.br", "medico/search", json);
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
            MedicoForm resultado = RestUtils.postJson(MedicoForm.class, "rest.marcmed.com.br", "medico/save", json);
            mav.addObject("result", resultado);
        } catch (RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao buscar medicos ", e);
            mav.setViewName("medico/cadastro");
            mav.addObject("errorMessage", "Ocorreu um erro interno, tente novamente mais tarde.");
        } catch (RestException e) {
            LOG.error("Erro ao buscar medicos: ErrorMessage " + e.getErrorMessage(), e);
            mav.setViewName("medico/cadastro");
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
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("medico/login");
        return mav;
    }



}
