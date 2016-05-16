package br.com.wjaa.ranchucrutes.commons.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

/**
 * Created by wagner on 18/08/15.
 */
public class StringUtils {

    public static String createMD5(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
