package br.com.wjaa.ranchucrutes.web.helper;

import br.com.wjaa.ranchucrutes.commons.vo.ProfissionalBasicoVo;
import br.com.wjaa.ranchucrutes.web.utils.RanchucrutesConstantes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wagner on 19/08/15.
 */
public class AuthHelper {


    public static boolean isAutenticado(HttpServletRequest request) {
        return request.getSession().getAttribute(RanchucrutesConstantes.LOGIN_SESSION) != null;
    }

    public static ProfissionalBasicoVo getProfissional(HttpServletRequest request) {
        return (ProfissionalBasicoVo) request.getSession().getAttribute(RanchucrutesConstantes.LOGIN_SESSION);
    }

    public static void sair(HttpServletRequest request) {
        request.getSession().removeAttribute(RanchucrutesConstantes.LOGIN_SESSION);
    }
}
