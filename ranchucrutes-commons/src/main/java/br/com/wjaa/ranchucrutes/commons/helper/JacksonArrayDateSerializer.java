package br.com.wjaa.ranchucrutes.commons.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wagner on 22/10/15.
 */
public class JacksonArrayDateSerializer extends JsonSerializer<Date[]> {
    @Override
    public void serialize(Date[] values, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartArray();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (Date date : values) {
            jgen.writeString(df.format(date));
        }
        jgen.writeEndArray();
    }
}
