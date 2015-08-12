package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.ws.exception.LoginServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wagner on 10/08/15.
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Log LOG = LogFactory.getLog(LoginServiceImpl.class);


    @Override
    public String createHashPass(String pass) throws LoginServiceException {
        try {
            byte[] bytesOfMessage = pass.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(bytesOfMessage);
            return new String(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOG.error("Erro ao gerar o hash da senha", e);
            throw new LoginServiceException("Erro ao gerar o hash da senha");
        }
    }
}
