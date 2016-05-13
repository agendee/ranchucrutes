package br.com.wjaa.ranchucrutes.ws.integracao.camtwo.vo;

import br.com.wjaa.ranchucrutes.commons.vo.ErrorMessageVo;

/**
 * Created by wagner on 5/13/16.
 */
public class ErrorCamtwoVO extends ErrorMessageVo {

    private String category;
    private String message;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        this.setErrorCode(400);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.setErrorMessage(message);
    }
}
