package br.com.wjaa.ranchucrutes.ws.exception;

import br.com.wjaa.ranchucrutes.commons.vo.ErrorMessageVo;

/**
 * Created by wagner on 16/06/15.
 */
public class RestException extends Exception {

    private ErrorMessageVo errorMessageVo;

    public RestException(String message, Exception e) {
        super(message,e);
    }

    public RestException(ErrorMessageVo errorMessageVo) {
        this.errorMessageVo = errorMessageVo;
    }

    public ErrorMessageVo getErrorMessageVo() {
        return errorMessageVo;
    }
}
