package br.com.wjaa.ranchucrutes.commons.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wagner on 18/08/15.
 */
public class StringUtils {

    private static final Log LOG = LogFactory.getLog(StringUtils.class);

    public static String createMD5(String str){
        try {

            if (str == null || "".equals(str)){
                throw new IllegalArgumentException("String nao pode ser vazia");
            }

            byte[] bytesOfMessage = str.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(bytesOfMessage);
            return new String(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOG.error("Erro ao gerar o hash da senha", e);
            throw new IllegalStateException("Erro ao gerar o md5");
        }
    }

}
