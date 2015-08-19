package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.utils.StringUtils;
import br.com.wjaa.ranchucrutes.commons.vo.ConfirmaCadastroVo;
import br.com.wjaa.ranchucrutes.commons.vo.MedicoBasicoVo;
import br.com.wjaa.ranchucrutes.ws.adapter.MedicoAdapter;
import br.com.wjaa.ranchucrutes.ws.dao.LoginDao;
import br.com.wjaa.ranchucrutes.ws.dao.RanchucrutesDao;
import br.com.wjaa.ranchucrutes.ws.entity.MedicoEntity;
import br.com.wjaa.ranchucrutes.ws.exception.LoginNotConfirmationException;
import br.com.wjaa.ranchucrutes.ws.exception.LoginServiceException;
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

        MedicoEntity medicoEntity = dao.getSingleRecordByProperties(MedicoEntity.class, "codeConfirmacao", code);
        if (medicoEntity == null){
            return new ConfirmaCadastroVo(ConfirmaCadastroVo.StatusConfirmacaoCadastro.CODIGO_INVALIDO);
        }

        if (medicoEntity.getDataConfirmacao() != null){
            return new ConfirmaCadastroVo(ConfirmaCadastroVo.StatusConfirmacaoCadastro.CADASTRO_JA_CONFIRMADO);
        }

        medicoEntity.setDataConfirmacao(new Date());
        medicoEntity.setAtivo(true);
        dao.save(medicoEntity);

        return new ConfirmaCadastroVo(ConfirmaCadastroVo.StatusConfirmacaoCadastro.SUCESSO);
    }

    @Override
    public MedicoBasicoVo autenticarMedico(String emailOuCrm, String pass) throws LoginServiceException, LoginNotConfirmationException {
        MedicoEntity medicoEntity;
        if (org.apache.commons.lang.StringUtils.isNumeric(emailOuCrm)){
            medicoEntity = this.loginDao.autenticarMedico(Integer.valueOf(emailOuCrm), this.createHashPass(pass));
        }else{
            medicoEntity = this.loginDao.autenticarMedico(emailOuCrm, this.createHashPass(pass));
        }

        if (medicoEntity == null){
            throw new LoginServiceException("Login ou senha inválido.");
        }

        if (medicoEntity.getDataConfirmacao() == null){
            throw new LoginNotConfirmationException("Você não confirmou o seu acesso.");
        }

        if (medicoEntity.getAtivo() == null || !medicoEntity.getAtivo()){
            throw new LoginServiceException("Seu acesso está inativado, contate o nosso suporte técnico.");
        }

        return MedicoAdapter.toMedicoBasico(medicoEntity);
    }
}
