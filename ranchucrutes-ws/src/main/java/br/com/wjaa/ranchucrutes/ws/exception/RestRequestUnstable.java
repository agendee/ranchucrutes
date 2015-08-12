package br.com.wjaa.ranchucrutes.ws.exception;

/**
 * Created by wagner on 16/06/15.
 */
public class RestRequestUnstable extends Exception {

    public RestRequestUnstable(String message) {
        super(message);
    }

    public RestRequestUnstable(String message, Exception e) {
        super(message,e);
    }
}
