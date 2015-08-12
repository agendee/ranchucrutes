package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.utils.EmailUtils;
import br.com.wjaa.ranchucrutes.commons.vo.EmailParamVo;
import br.com.wjaa.ranchucrutes.ws.email.EmailTemplate;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

/**
 * Created by wagner on 11/08/15.
 */
@Service
public class EmailServiceImpl implements EmailService {


    @Override
    public void sendEmailNovoMedico(String email, String... params) {
        EmailParamVo emailParamVo = new EmailParamVo();
        emailParamVo.setEmail(email);
        emailParamVo.setTitle("Confirme seu cadastro");
        emailParamVo.setFrom("notificacoes@mapdoctors.com.br");
        emailParamVo.setName("MapDoctors - Notificação");
        emailParamVo.setBody(EmailTemplate.NOVO_MEDICO.getBody(params));
        try {
            EmailUtils.send(emailParamVo,EmailUtils.scNotificacao);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
