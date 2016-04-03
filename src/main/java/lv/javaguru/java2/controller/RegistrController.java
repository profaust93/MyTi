package lv.javaguru.java2.controller;

import lv.javaguru.java2.model.MVCModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kemran on 02/04/2016.
 */
public class RegistrController implements MVCController {


    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/login.jsp", "Login");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {

        return new MVCModel("/login.jsp", null );

    }
}
