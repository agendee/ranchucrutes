package br.com.wjaa.ranchucrutes.commons.utils;

import br.com.wjaa.ranchucrutes.commons.vo.EmailParamVo;
import br.com.wjaa.ranchucrutes.commons.vo.EmailServerConfigVo;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by wagner on 10/08/15.
 */
public class EmailUtils {


    public static EmailServerConfigVo scContato = new EmailServerConfigVo();
    public static EmailServerConfigVo scNotificacao = new EmailServerConfigVo();
    static{
        scContato = ObjectUtils.fromJSON("{\"smtp\":\"smtp.gmail.com\",\"port\":465,\"user\":\"wagner@wjaa.com.br\",\"pass\":\"*456852*\",\"ssl\":true}"
                , EmailServerConfigVo.class);

        scNotificacao = ObjectUtils.fromJSON("{\"smtp\":\"smtp.gmail.com\",\"port\":465,\"user\":\"wagner@wjaa.com.br\",\"pass\":\"*456852*\",\"ssl\":true}"
                , EmailServerConfigVo.class);
    }

    public static void send(EmailParamVo p, EmailServerConfigVo sc) throws EmailException {
        HtmlEmail mail = new HtmlEmail();
        mail.addTo(p.getEmail());
        mail.setHostName(sc.getSmtp());
        mail.setSmtpPort(sc.getPort());
        mail.setAuthentication(sc.getUser(), sc.getPass());
        mail.setFrom(p.getFrom(), p.getName(), "UTF-8");
        mail.setBoolHasAttachments(true);
        mail.setHtmlMsg(p.getBody());
        mail.setSubject(p.getTitle());
        mail.setSSLOnConnect(new Boolean(sc.getSsl()));
        mail.setCharset("UTF-8");
        mail.send();
    }

}
