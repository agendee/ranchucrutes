package br.com.wjaa.ranchucrutes.web.helper;

import br.com.wjaa.ranchucrutes.commons.vo.MedicoBasicoVo;
import br.com.wjaa.ranchucrutes.web.utils.RanchucrutesConstantes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wagner on 19/08/15.
 */
public class AuthHelper {


    public static boolean isAutenticado(HttpServletRequest request) {
        return request.getSession().getAttribute(RanchucrutesConstantes.LOGIN_SESSION) != null;
    }

    public static MedicoBasicoVo getMedico(HttpServletRequest request) {
        return (MedicoBasicoVo) request.getSession().getAttribute(RanchucrutesConstantes.LOGIN_SESSION);
    }
}
