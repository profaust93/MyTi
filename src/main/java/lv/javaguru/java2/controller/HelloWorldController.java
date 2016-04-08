package lv.javaguru.java2.controller;

import lv.javaguru.java2.model.MVCModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HelloWorldController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/index.jsp","Hello From MVC");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
