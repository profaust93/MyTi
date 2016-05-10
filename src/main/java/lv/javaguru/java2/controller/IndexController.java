package lv.javaguru.java2.controller;

import lv.javaguru.java2.model.MVCModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String processGet(HttpServletRequest req) {
        return "index";
    }


    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}

