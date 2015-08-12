package br.com.wjaa.ranchucrutes.web.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by wagner on 16/06/15.
 */
public class RestResponseUnsatisfiedException extends Exception {
    public RestResponseUnsatisfiedException(String message, JsonProcessingException e) {
        super(message,e);
    }
}
