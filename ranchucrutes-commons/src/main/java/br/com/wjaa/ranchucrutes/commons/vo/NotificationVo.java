package br.com.wjaa.ranchucrutes.commons.vo;

/**
 * Created by wagner on 24/01/16.
 */
public class NotificationVo {

    public enum StatusNotification{
        UNKNOW,
        CONFIRMATION,
        CANCELLATION
    }

    public NotificationVo(){
    }

    public NotificationVo(StatusNotification status,String msg){
        this.status = status;
        this.msg = msg;
    }

    private StatusNotification status;
    private String msg;

    public StatusNotification getStatus() {
        return status;
    }

    public void setStatus(StatusNotification status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
