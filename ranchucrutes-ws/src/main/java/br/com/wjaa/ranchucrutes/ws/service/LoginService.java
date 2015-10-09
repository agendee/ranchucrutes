package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.form.LoginForm;
import br.com.wjaa.ranchucrutes.commons.vo.ConfirmaCadastroVo;
import br.com.wjaa.ranchucrutes.commons.vo.ProfissionalBasicoVo;
import br.com.wjaa.ranchucrutes.commons.vo.PacienteVo;
import br.com.wjaa.ranchucrutes.ws.exception.LoginNotConfirmationException;
import br.com.wjaa.ranchucrutes.ws.exception.LoginServiceException;
import br.com.wjaa.ranchucrutes.ws.exception.LoginSocialException;

/**
 * Created by wagner on 10/08/15.
 */
public interface LoginService {

    String createHashPass(String pass);

    String createCodeConfirmation(String email, Integer crm);

    ConfirmaCadastroVo confirmaCadastro(String code);

    ProfissionalBasicoVo autenticarProfissional(String emailOrCrm, String pass) throws LoginServiceException, LoginNotConfirmationException;

    PacienteVo autenticarPaciente(LoginForm form) throws LoginServiceException, LoginSocialException;


}
