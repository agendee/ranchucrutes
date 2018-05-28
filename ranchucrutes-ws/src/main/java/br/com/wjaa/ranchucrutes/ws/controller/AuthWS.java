package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.form.LoginForm;
import br.com.wjaa.ranchucrutes.commons.form.ProfissionalForm;
import br.com.wjaa.ranchucrutes.commons.vo.ConfirmaCadastroVo;
import br.com.wjaa.ranchucrutes.commons.vo.ResultadoLoginVo;
import br.com.wjaa.ranchucrutes.ws.exception.LoginServiceException;
import br.com.wjaa.ranchucrutes.ws.exception.LoginSocialException;
import br.com.wjaa.ranchucrutes.ws.service.LoginService;
import br.com.wjaa.ranchucrutes.ws.adapter.ProfissionalAdapter;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissaoEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import br.com.wjaa.ranchucrutes.ws.exception.LoginNotConfirmationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wagner on 18/08/15.
 */
@RestController
public class AuthWS {

    private static final Log LOG = LogFactory.getLog(AuthWS.class);

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/auth/confirme/{code}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ConfirmaCadastroVo confirma(@PathVariable String code){
        return loginService.confirmaCadastro(code);
    }
    
    
    @RequestMapping(value = "/auth/confirmerecuperarsenha/{code}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ProfissionalForm confirmeRecuperarSenha(@PathVariable String code) throws LoginServiceException{
    	ProfissionalForm form =  ProfissionalAdapter.toProfissionalForm(loginService.confirmeRecuperarSenha(code));
    	return     	form;
    }
    

    @RequestMapping(value = "/auth/profissional", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8", method = RequestMethod.POST)
    public ResultadoLoginVo authProfissional(@RequestBody LoginForm form){
        ResultadoLoginVo resultadoLoginVo = new ResultadoLoginVo();
        try {
            resultadoLoginVo.setProfissional(loginService.autenticarProfissional(form.getLogin(), form.getSenha()));
            resultadoLoginVo.setStatus(ResultadoLoginVo.StatusLogin.SUCESSO);
            return resultadoLoginVo;
        } catch (LoginServiceException e) {
            LOG.error("Erro no login ", e);
            resultadoLoginVo.setStatus(ResultadoLoginVo.StatusLogin.ERRO);
            return resultadoLoginVo;
        } catch (LoginNotConfirmationException e) {
            LOG.error("Erro no login ", e);
            resultadoLoginVo.setStatus(ResultadoLoginVo.StatusLogin.ACESSO_NAO_CONFIRMADO);
        }
        return resultadoLoginVo;
    }

    @RequestMapping(value = "/auth/paciente", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8", method = RequestMethod.POST)
    public ResultadoLoginVo authPaciente(@RequestBody LoginForm form){
        ResultadoLoginVo resultadoLoginVo = new ResultadoLoginVo();
        try {
            resultadoLoginVo.setPaciente(loginService.autenticarPaciente(form));
            resultadoLoginVo.setStatus(ResultadoLoginVo.StatusLogin.SUCESSO);
            return resultadoLoginVo;
        } catch (LoginServiceException e) {
            LOG.error("Erro no login ", e);
            resultadoLoginVo.setStatus(ResultadoLoginVo.StatusLogin.ERRO);
            return resultadoLoginVo;
        } catch (LoginSocialException e) {
            LOG.error("Erro no login ", e);
            resultadoLoginVo.setStatus(ResultadoLoginVo.StatusLogin.ERRO_SOCIAL);
            return resultadoLoginVo;
        }

    }

}
