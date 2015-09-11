package br.com.wjaa.ranchucrutes.ws.entity;

import br.com.wjaa.ranchucrutes.commons.form.LoginForm;

/**
 * Created by wagner on 11/09/15.
 */
public enum RedeSocialEnum {

    FACEBOOK,
    GPLUS;

    public static RedeSocialEnum adapterSocialType(LoginForm.AuthType type) {

        switch (type){
            case AUTH_FACEBOOK: return FACEBOOK;
            case AUTH_GPLUS: return GPLUS;
        }
        return null;
    }

    public LoginForm.AuthType getSocialType() {
        switch (this){
            case FACEBOOK: return LoginForm.AuthType.AUTH_FACEBOOK;
            case GPLUS: return LoginForm.AuthType.AUTH_GPLUS;
        }
        return null;
    }
}
