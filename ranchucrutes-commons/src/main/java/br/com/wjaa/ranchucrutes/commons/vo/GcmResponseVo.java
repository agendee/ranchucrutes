package br.com.wjaa.ranchucrutes.commons.vo;

/**
 * Created by wagner on 24/01/16.
 */
public class GcmResponseVo {

    private Boolean sent;


    public GcmResponseVo() {
    }

    public GcmResponseVo(boolean sent) {
        this.sent = sent;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }
}
