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

    public RestException(ErrorMessageVo ... errorMessageVo) {
       this.errorMessageVo = (ErrorMessageVo) errorMessageVo[0];
    }
    public RestException(ErrorMessageVo errorMessageVo) {
       this.errorMessageVo = (ErrorMessageVo) errorMessageVo;
    }

    public ErrorMessageVo getErrorMessageVo() {
        return errorMessageVo;
    }

    @Override
    public String getMessage() {
        return errorMessageVo != null ?
                errorMessageVo.getErrorMessage() :
                super.getMessage();
    }
}
