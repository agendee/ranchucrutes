package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.vo.ConfirmaCadastroVo;
import br.com.wjaa.ranchucrutes.commons.vo.ResultadoLoginVo;
import br.com.wjaa.ranchucrutes.ws.exception.LoginNotConfirmationException;
import br.com.wjaa.ranchucrutes.ws.exception.LoginServiceException;
import br.com.wjaa.ranchucrutes.ws.service.LoginService;
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


    @RequestMapping(value = "/auth/confirme/{code}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public ConfirmaCadastroVo confirma(@PathVariable String code){
        return loginService.confirmaCadastro(code);
    }

    @RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8", method = RequestMethod.POST)
    public ResultadoLoginVo auth(@RequestParam String emailOuCrm, @RequestParam String senha){
        ResultadoLoginVo resultadoLoginVo = new ResultadoLoginVo();
        try {
            resultadoLoginVo.setMedico(loginService.autenticarMedico(emailOuCrm, senha));
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

}
