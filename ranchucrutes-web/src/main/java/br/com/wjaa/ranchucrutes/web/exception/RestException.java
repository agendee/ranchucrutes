package br.com.wjaa.ranchucrutes.web.exception;

import br.com.wjaa.ranchucrutes.web.vo.ErrorMessageVo;

/**
 * Created by wagner on 16/06/15.
 */
public class RestException extends Throwable {

    private ErrorMessageVo errorMessage;

    public RestException(ErrorMessageVo errorMessage) {
        this.errorMessage = errorMessage;

    }

    public ErrorMessageVo getErrorMessage() {
        return errorMessage;
    }


}
