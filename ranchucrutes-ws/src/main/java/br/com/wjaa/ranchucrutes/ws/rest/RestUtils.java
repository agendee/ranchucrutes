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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
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
        return executeMethod(clazzReturn,httpGet, ErrorMessageVo.class);
    }

    public static <T>T getJsonWithParamPathAndParam(Class<T> clazzReturn, String targetUrl, Map<String,String> params,
                                                    String ... paramsPath) throws
            RestResponseUnsatisfiedException, RestException, RestRequestUnstable {
        HttpGet httpGet = new HttpGet("http://" + targetUrl + RestUtils.createParamsPath(paramsPath) +
                RestUtils.createParams(params));
        return executeMethod(clazzReturn, httpGet, ErrorMessageVo.class);
    }

    public static <T>T postJson(Class<T> clazzReturn, Class<? extends ErrorMessageVo> clazzError, String targetUrl, String uri, String json) throws
            RestResponseUnsatisfiedException, RestException, RestRequestUnstable {
        HttpPost httpPost = new HttpPost("http://" + targetUrl + uri);
        httpPost.setEntity(new StringEntity(json,"UTF-8"));

        return executeMethod(clazzReturn, httpPost, clazzError);

    }


    private static <T> T executeMethod(Class<T> clazzReturn, HttpRequestBase httpGet, Class<? extends ErrorMessageVo> clazzError) throws RestResponseUnsatisfiedException, RestRequestUnstable, RestException {
        CloseableHttpResponse response = null;
        try {
            httpGet.setConfig( RequestConfig.custom().setConnectionRequestTimeout(TIMEOUT)
                    .setConnectTimeout(TIMEOUT)
                    .setSocketTimeout(TIMEOUT)
                    .build());
            response = httpclient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            if ( statusCode >= 404 && statusCode < 500){
                throw new RestRequestUnstable("Servico está fora do ar.");
            }

            if (statusCode == 400 && statusCode < 404){
                throw new RestException(mapper.readValue(EntityUtils.toString(response.getEntity()), clazzError));
            }

            if (statusCode >= 500 && statusCode < 600){
                throw new RestException(mapper.readValue(EntityUtils.toString(response.getEntity()), clazzError));
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
