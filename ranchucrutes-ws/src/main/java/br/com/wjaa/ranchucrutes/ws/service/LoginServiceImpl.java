package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.form.LoginForm;
import br.com.wjaa.ranchucrutes.commons.utils.StringUtils;
import br.com.wjaa.ranchucrutes.commons.vo.ConfirmaCadastroVo;
import br.com.wjaa.ranchucrutes.commons.vo.ProfissionalBasicoVo;
import br.com.wjaa.ranchucrutes.commons.vo.PacienteVo;
import br.com.wjaa.ranchucrutes.framework.dao.RanchucrutesDao;
import br.com.wjaa.ranchucrutes.ws.adapter.PacienteAdapter;
import br.com.wjaa.ranchucrutes.ws.adapter.ProfissionalAdapter;
import br.com.wjaa.ranchucrutes.ws.dao.LoginDao;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import br.com.wjaa.ranchucrutes.ws.entity.RedeSocialEnum;
import br.com.wjaa.ranchucrutes.ws.exception.LoginNotConfirmationException;
import br.com.wjaa.ranchucrutes.ws.exception.LoginServiceException;
import br.com.wjaa.ranchucrutes.ws.exception.LoginSocialException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by wagner on 10/08/15.
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Log LOG = LogFactory.getLog(LoginServiceImpl.class);


    @Autowired
    private RanchucrutesDao dao;

    @Autowired
    private LoginDao loginDao;


    @Override
    public String createHashPass(String pass){
        return StringUtils.createMD5(pass);
    }

    @Override
    public String createCodeConfirmation(String email, Integer crm){
        //criando um md5 com base no email + crm e milisegundo atual.
        return StringUtils.createMD5(email + "|" + crm + "|" + new Date().getTime());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ConfirmaCadastroVo confirmaCadastro(String code) {

        ProfissionalEntity profissionalEntity = dao.getSingleRecordByProperties(ProfissionalEntity.class, "codeConfirmacao", code);
        if (profissionalEntity == null){
            return new ConfirmaCadastroVo(ConfirmaCadastroVo.StatusConfirmacaoCadastro.CODIGO_INVALIDO);
        }

        if (profissionalEntity.getDataConfirmacao() != null){
            return new ConfirmaCadastroVo(ConfirmaCadastroVo.StatusConfirmacaoCadastro.CADASTRO_JA_CONFIRMADO);
        }

        profissionalEntity.setDataConfirmacao(new Date());
        profissionalEntity.setAtivo(true);
        dao.save(profissionalEntity);

        return new ConfirmaCadastroVo(ConfirmaCadastroVo.StatusConfirmacaoCadastro.SUCESSO);
    }

    @Override
    public ProfissionalBasicoVo autenticarProfissional(String emailOuCrm, String pass) throws LoginServiceException, LoginNotConfirmationException {
        ProfissionalEntity profissionalEntity;
        if (org.apache.commons.lang.StringUtils.isNumeric(emailOuCrm)){
            profissionalEntity = this.loginDao.autenticarProfissional(Integer.valueOf(emailOuCrm), this.createHashPass(pass));
        }else{
            profissionalEntity = this.loginDao.autenticarProfissional(emailOuCrm, this.createHashPass(pass));
        }

        if (profissionalEntity == null){
            throw new LoginServiceException("Login ou senha inválido.");
        }

        if (profissionalEntity.getDataConfirmacao() == null){
            throw new LoginNotConfirmationException("Você não confirmou o seu acesso.");
        }

        if (profissionalEntity.getAtivo() == null || !profissionalEntity.getAtivo()){
            throw new LoginServiceException("Seu acesso está inativado, contate o nosso suporte técnico.");
        }

        return ProfissionalAdapter.toProfissionalBasico(profissionalEntity);
    }

    @Override
    public PacienteVo autenticarPaciente(LoginForm form) throws LoginServiceException, LoginSocialException {

        if (form.getType() == null){
            throw new LoginServiceException("Rede Social não encontrada!");
        }


        PacienteEntity pacienteEntity;
        if (LoginForm.AuthType.AUTH_RANCHUCRUTES.equals(form.getType())){

            pacienteEntity = this.loginDao.autenticarPaciente(form.getLogin(), this.createHashPass(form.getSenha()));

            if (pacienteEntity == null){
                throw new LoginServiceException("Usuário ou senha inválida");
            }

        }else{
            pacienteEntity = this.loginDao.autenticarPaciente(RedeSocialEnum.adapterSocialType(form.getType()),
                    form.getKeySocial());

            if(pacienteEntity == null){
                throw new LoginSocialException("Usuário não cadastrado!");
            }
        }

        return PacienteAdapter.toPacienteVo(pacienteEntity);
    }
}
