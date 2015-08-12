package br.com.wjaa.ranchucrutes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wagner on 12/06/15.
 */
@Controller
public class HomeController {

    //private RanchucrutesCache ranchucrutesCache = RanchucrutesCache.getInstance();

    @RequestMapping("/")
    public ModelAndView home(Model model) {
        ModelAndView mav = new ModelAndView("index");
        //mav.addObject("especialidades");
        return mav;
    }

}
