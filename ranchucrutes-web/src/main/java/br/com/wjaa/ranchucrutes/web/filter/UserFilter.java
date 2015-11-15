package br.com.wjaa.ranchucrutes.web.filter;

import br.com.wjaa.ranchucrutes.web.helper.AuthHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by wagner on 12/11/15.
 */
@WebFilter(filterName = "UserFilter",
        urlPatterns = {"/profissional/*"},
        initParams = {
        @WebInitParam(name = "bypass", value = "profissional/login,profissional/cadastro")})
public class UserFilter implements Filter {

    private static final Log LOG = LogFactory.getLog(UserFilter.class);
    private String bypass;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        bypass = filterConfig.getInitParameter("bypass");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();

        String bybassArray [] = bypass.split(",");
        for (String bp : bybassArray){
            if (uri.contains(bp)){
                chain.doFilter(request,response);
                return;
            }
        }

        if ( !AuthHelper.isAutenticado((HttpServletRequest) request) ){
            LOG.info("usuario nao autenticado redirecionando");
            request.getRequestDispatcher("/profissional/login")
                    .forward(request, response);
        }else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
