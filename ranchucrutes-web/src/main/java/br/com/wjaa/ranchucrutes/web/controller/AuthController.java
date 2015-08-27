package br.com.wjaa.ranchucrutes.web.controller;

import br.com.wjaa.ranchucrutes.commons.form.LoginForm;
import br.com.wjaa.ranchucrutes.commons.utils.ObjectUtils;
import br.com.wjaa.ranchucrutes.commons.vo.ConfirmaCadastroVo;
import br.com.wjaa.ranchucrutes.commons.vo.ResultadoLoginVo;
import br.com.wjaa.ranchucrutes.web.exception.RestException;
import br.com.wjaa.ranchucrutes.web.exception.RestRequestUnstable;
import br.com.wjaa.ranchucrutes.web.exception.RestResponseUnsatisfiedException;
import br.com.wjaa.ranchucrutes.web.helper.AuthHelper;
import br.com.wjaa.ranchucrutes.web.rest.RestUtils;
import br.com.wjaa.ranchucrutes.web.utils.RanchucrutesConstantes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wagner on 18/08/15.
 */
@Controller
public class AuthController {

    private static final Log LOG = LogFactory.getLog(AuthController.class);

    @RequestMapping(value = "/auth/confirme/{code}", method = RequestMethod.GET)
    public ModelAndView confirmar(@PathVariable String code){
        ModelAndView mav = new ModelAndView("auth/confirmacao");
        try {
            ConfirmaCadastroVo c = RestUtils.getJsonWithParamPath(ConfirmaCadastroVo.class, RanchucrutesConstantes.HOST_WS,
                    "auth/confirme", code);
            mav.addObject("confirmaCadastro",c);
        } catch (RestResponseUnsatisfiedException | RestException | RestRequestUnstable e) {
            LOG.error("Erro na requisicao", e);
            mav.addObject("confirmaCadastro", new ConfirmaCadastroVo(ConfirmaCadastroVo.StatusConfirmacaoCadastro.UNKOWN_ERROR));
        }

        return mav;
    }

    @RequestMapping(value = "/auth/medico", method = RequestMethod.POST)
    public ModelAndView loginMedico(@ModelAttribute LoginForm loginForm, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("redirect:/medico/agenda");
        try {

            if ( AuthHelper.isAutenticado(request) ){
                mav.addObject("medico", AuthHelper.getMedico(request));
                return mav;
            }

            mav.addObject("form",loginForm);
            String json = ObjectUtils.toJson(loginForm);
            ResultadoLoginVo resultadoLogin = RestUtils.postJson(ResultadoLoginVo.class, RanchucrutesConstantes.HOST_WS,
                    "auth", json);

            if (ResultadoLoginVo.StatusLogin.SUCESSO.equals(resultadoLogin.getStatus())){
                mav.addObject("medico", resultadoLogin.getMedico());
                HttpSession session = request.getSession();
                session.setAttribute(RanchucrutesConstantes.LOGIN_SESSION, resultadoLogin.getMedico());
                //20 minutos
                session.setMaxInactiveInterval(20*60);
                return mav;
            }else if (ResultadoLoginVo.StatusLogin.ERRO.equals(resultadoLogin.getStatus())){
                mav.setViewName("medico/login");
                mav.addObject("errorMessage", resultadoLogin.getStatus().getMsg());
            }else if (ResultadoLoginVo.StatusLogin.ACESSO_NAO_CONFIRMADO.equals(resultadoLogin.getStatus())){
                mav.setViewName("medico/login");
                mav.addObject("errorMessage", resultadoLogin.getStatus().getMsg());
                //TODO ADICIONAR ALGO AQUI PRA QUE NA TELA O USUARIO TENHA UM LINK PARA RENVIAR O EMAIL CASO ELE DESEJE.
            }

        } catch (RestResponseUnsatisfiedException | RestException | RestRequestUnstable e) {
            LOG.error("Erro na requisicao", e);
            mav.addObject("error", e.getMessage());
        }

        return mav;
    }


    @RequestMapping(value = "/auth/sair", method = RequestMethod.GET)
    public ModelAndView sair(HttpServletRequest request){
        if ( AuthHelper.isAutenticado(request) ){
            AuthHelper.sair(request);
        }
        return new ModelAndView("redirect:/medico/login");
    }

}
