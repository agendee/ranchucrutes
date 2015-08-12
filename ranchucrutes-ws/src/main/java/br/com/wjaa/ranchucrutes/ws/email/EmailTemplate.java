package br.com.wjaa.ranchucrutes.ws.email;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by wagner on 10/08/15.
 */
public enum EmailTemplate {
    NOVO_MEDICO("novo-medico.html");


    private StringBuilder template = new StringBuilder();

    private EmailTemplate (String html){
        InputStream inTemplate = EmailTemplate.class.getClassLoader().getResourceAsStream(html);

        try {
            List<String> lines = IOUtils.readLines(inTemplate);
            for (String l : lines) {
                template.append(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getBody(String ... params) {
        if (NOVO_MEDICO.equals(this)){

            Assert.isTrue(params != null && params.length == 2,"Parametros invalidos");

            return StringUtils.replaceEach(template.toString(),
                    new String[]{
                            "{NOME_CLIENTE}",
                            "{CODE}"
                    },
                    params);
        }
        return "";
    }
}
