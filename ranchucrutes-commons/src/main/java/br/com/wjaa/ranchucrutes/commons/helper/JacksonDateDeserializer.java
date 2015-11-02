package br.com.wjaa.ranchucrutes.commons.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wagner on 27/10/15.
 */
public class JacksonDateDeserializer extends JsonDeserializer<Date> {
    private static final Log LOG = LogFactory.getLog(JacksonDateDeserializer.class);
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return sdf.parse(jp.getText());
        } catch (ParseException e) {
            LOG.error("Erro no parse", e);
        }
        return null;
    }
}
