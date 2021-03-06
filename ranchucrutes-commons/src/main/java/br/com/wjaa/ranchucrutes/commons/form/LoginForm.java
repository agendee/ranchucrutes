package br.com.wjaa.ranchucrutes.commons.form;

/**
 * Created by wagner on 19/08/15.
 */
public class LoginForm {

    public enum AuthType{
        AUTH_FACEBOOK,
        AUTH_GPLUS,
        AUTH_RANCHUCRUTES
    }


    private String login;
    private String senha;
    private String keySocial;
    private AuthType type;
    private String keyDevice;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getKeySocial() {
        return keySocial;
    }

    public void setKeySocial(String keySocial) {
        this.keySocial = keySocial;
    }

    public AuthType getType() {
        return type;
    }

    public void setType(AuthType type) {
        this.type = type;
    }

    public String getKeyDevice() {
        return keyDevice;
    }

    public void setKeyDevice(String keyDevice) {
        this.keyDevice = keyDevice;
    }
}
