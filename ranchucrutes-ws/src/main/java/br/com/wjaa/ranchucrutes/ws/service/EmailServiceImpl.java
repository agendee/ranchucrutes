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
    public void sendEmailNovoProfissional(String email, String... params) {
        EmailParamVo emailParamVo = new EmailParamVo();
        emailParamVo.setEmail(email);
        emailParamVo.setTitle("Confirme seu cadastro");
        emailParamVo.setFrom("noreply@agendee.com.br");
        emailParamVo.setName("Agendee - Notificação");
        emailParamVo.setBody(EmailTemplate.NOVO_PROFISSIONAL.getBody(params));
        try {
            EmailUtils.send(emailParamVo,EmailUtils.scNotificacao);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
