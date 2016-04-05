package lv.javaguru.java2.controller;

import lv.javaguru.java2.model.MVCModel;

import javax.servlet.http.HttpServletRequest;


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
