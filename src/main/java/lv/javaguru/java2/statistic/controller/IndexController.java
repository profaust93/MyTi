package lv.javaguru.java2.statistic.controller;

import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.security.SecurityService;
import lv.javaguru.java2.statistic.exception.StatisticException;
import lv.javaguru.java2.statistic.service.StatisticService;
import lv.javaguru.java2.todo.exception.ToDoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView processGet() {
        ModelAndView modelAndView = new ModelAndView("statistic");
        try {
            modelAndView.addObject("statistic", statisticService.getStatisticForUser(securityService.getCurrentUserId()));
        } catch (Exception e) {
            modelAndView.addObject("error",e.getMessage());
        }
        return modelAndView;
    }

    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}

