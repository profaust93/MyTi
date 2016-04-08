package lv.javaguru.java2.controller;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.model.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HelloWorldController implements MVCController {
    @Autowired
    private UserDAO userDAO;
    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/index.jsp","Hello From MVC");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
