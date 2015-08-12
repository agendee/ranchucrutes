package br.com.wjaa.ranchucrutes.commons.vo;

/**
 * Created by wagner on 11/08/15.
 */
public class EmailParamVo {
    private String email;
    private String name;
    private String from;
    private String title;
    private String body;


    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
