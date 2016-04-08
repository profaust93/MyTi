package lv.javaguru.java2.controller;

import lv.javaguru.java2.model.MVCModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ErrorController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/error.jsp","404 BAD");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
