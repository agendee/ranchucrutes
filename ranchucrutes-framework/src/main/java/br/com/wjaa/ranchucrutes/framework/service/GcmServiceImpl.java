package br.com.wjaa.ranchucrutes.framework.service;

import br.com.wjaa.ranchucrutes.commons.utils.ObjectUtils;
import br.com.wjaa.ranchucrutes.commons.vo.GcmResponseVo;
import br.com.wjaa.ranchucrutes.commons.vo.GcmResultVo;
import br.com.wjaa.ranchucrutes.commons.vo.NotificationVo;
import br.com.wjaa.ranchucrutes.framework.exception.GcmServiceException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by wagner on 24/01/16.
 */
@Service
public class GcmServiceImpl implements GcmService {

    private static final String GCM_URL = "https://gcm-http.googleapis.com/gcm/send";
    private static final Log LOG = LogFactory.getLog(GcmServiceImpl.class);

    @Autowired
    private RanchucrutesService ranchucrutesService;

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public GcmResponseVo sendNotification(NotificationVo notificationVo, String keyDeviceGcm) throws GcmServiceException {

        if (StringUtils.isEmpty(keyDeviceGcm)){
            throw new GcmServiceException("Sender está com KeyDeviceGcm nulo, não foi possivel enviar a notificacao");
        }

        if (notificationVo == null){
            throw new GcmServiceException("NotificationVo está nulo, não foi possivel enviar a notificacao");
        }

        return sendNotification(ObjectUtils.toJson(notificationVo),keyDeviceGcm);

    }

    @Override
    public GcmResponseVo sendNotification(String jsonNotification, String keyDeviceGcm) throws GcmServiceException {
        if (StringUtils.isEmpty(keyDeviceGcm)){
            throw new GcmServiceException("Sender esta com KeyDeviceGcm nulo, não foi possivel enviar a notificacao");
        }

        if (StringUtils.isBlank(jsonNotification)){
            throw new GcmServiceException("jsonNotification está nulo, não foi possivel enviar a notificacao");
        }

        try {
            GcmResultVo gcmResultVo = postJson(jsonNotification,keyDeviceGcm);
            GcmResponseVo gcmResponseVo = new GcmResponseVo();
            gcmResponseVo.setSent(gcmResultVo.getSuccess() > 0);
            return gcmResponseVo;

        } catch (Exception e) {
            LOG.error("Erro ao enviar a notificao", e);
            return new GcmResponseVo(false);
        }

    }


    private GcmResultVo postJson(String jsonData, String keyDevice) throws Exception {


        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(GCM_URL);
            //TODO TIRAR ESSE CODIGO DAQUI COLOCAR EM UM ARQUIVO DE CONFIG.
            httpPost.setHeader("Authorization","key=AIzaSyAG_m6OpG6_JWch7CvdDAuL44c7Jgq4DLg");
            httpPost.setHeader("Content-Type","application/json");


            StringBuilder json = new StringBuilder();
            json.append("{\"to\":\"" + keyDevice + "\",\"data\":" + jsonData + "}");

            httpPost.setEntity(
                    new StringEntity(json.toString(), ContentType.create("application/json", Consts.UTF_8)));

            response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();

            if ( statusCode >= 400 && statusCode < 600){
                LOG.error("Error code:" + statusCode + " response: " + response);
                throw new Exception(EntityUtils.toString(response.getEntity()));
            }

            LOG.debug("m=getJsonWithParamPath Response: " + response.getStatusLine());

            return mapper.readValue(EntityUtils.toString(response.getEntity()), GcmResultVo.class);

        }catch (JsonMappingException | JsonParseException e) {
            throw new Exception(e.getMessage(), e);
        } catch (IOException e) {
            throw new Exception(e.getMessage(), e);
        }   finally {
            try{
                if (response != null){
                    response.close();
                }
            }catch(Exception ex){
                LOG.error("Erro ao fechar a conexao.", ex);
            }

        }
    }

}
