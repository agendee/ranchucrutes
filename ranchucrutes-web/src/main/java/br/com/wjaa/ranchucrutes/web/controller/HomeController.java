package br.com.wjaa.ranchucrutes.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wagner on 12/06/15.
 */
@Controller
public class HomeController {

    private static final Log LOG = LogFactory.getLog(HomeController.class);
    //private RanchucrutesCache ranchucrutesCache = RanchucrutesCache.getInstance();

    @RequestMapping("/")
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("index");
        LOG.info(request.getSession().getId());
        //mav.addObject("especialidades");
        return mav;
    }

}
