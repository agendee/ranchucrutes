package br.com.wjaa.ranchucrutes.ws.rest;

import br.com.wjaa.ranchucrutes.ws.exception.RestRequestUnstable;
import br.com.wjaa.ranchucrutes.ws.exception.RestException;
import br.com.wjaa.ranchucrutes.ws.exception.RestResponseUnsatisfiedException;
import br.com.wjaa.ranchucrutes.commons.vo.ErrorMessageVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by wagner on 16/06/15.
 */
public class RestUtils {

    private static final Log LOG = LogFactory.getLog(RestUtils.class);
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Integer TIMEOUT = 7000;

    public static <T>T getJsonWithParamPath(Class<T> clazzReturn, String targetUrl, String ... params) throws
            RestResponseUnsatisfiedException, RestException, RestRequestUnstable {

        HttpGet httpGet = new HttpGet("http://" + targetUrl + "/" + RestUtils.createParamsPath(params));
        return executeGet(clazzReturn,httpGet);
    }

    public static <T>T getJsonWithParamPathAndParam(Class<T> clazzReturn, String targetUrl, Map<String,String> params,
                                                    String ... paramsPath) throws
            RestResponseUnsatisfiedException, RestException, RestRequestUnstable {
        HttpGet httpGet = new HttpGet("http://" + targetUrl + RestUtils.createParamsPath(paramsPath) +
                RestUtils.createParams(params));
        return executeGet(clazzReturn, httpGet);
    }

    private static <T> T executeGet(Class<T> clazzReturn, HttpGet httpGet) throws RestResponseUnsatisfiedException, RestRequestUnstable, RestException {
        CloseableHttpResponse response = null;
        try {
            httpGet.setConfig( RequestConfig.custom().setConnectionRequestTimeout(TIMEOUT)
                    .setConnectTimeout(TIMEOUT)
                    .setSocketTimeout(TIMEOUT)
                    .build());
            response = httpclient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            if ( statusCode >= 400 && statusCode < 500){
                throw new RestRequestUnstable("Servico está fora do ar.");
            }

            if (statusCode >= 500 && statusCode < 600){
                throw new RestException(mapper.readValue(EntityUtils.toString(response.getEntity()), ErrorMessageVo.class));
            }

            LOG.debug("m=getJsonWithParamPath Response: " + response.getStatusLine());

            return mapper.readValue(EntityUtils.toString(response.getEntity()), clazzReturn);

        }catch (JsonMappingException | JsonParseException e) {
            throw new RestResponseUnsatisfiedException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RestRequestUnstable(e.getMessage(), e);
        }  catch (Exception e) {
            throw new RestException(e.getMessage(),e);
        } finally {
            try{
                if (response != null){
                    response.close();
                }
            }catch(Exception ex){
                LOG.error("Erro ao fechar a conexao.", ex);
            }

        }
    }


    private static String createParamsPath(String[] params) {
        String result = "";
        for(String p : params){
            result += "/" + p;
        }
        return result;

    }

    private static String createParams(Map<String,String> params) {
        String result = "?";
        for(String p : params.keySet()){
            result +=  p + "=" + params.get(p) + "&";
        }
        return result;

    }

}
