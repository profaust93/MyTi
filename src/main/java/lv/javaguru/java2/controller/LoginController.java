package lv.javaguru.java2.controller;

import lv.javaguru.java2.model.exceptions.RedirectException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() throws RedirectException {
        return  "login";
    }

}
