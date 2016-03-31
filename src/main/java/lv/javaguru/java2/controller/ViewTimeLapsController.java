package lv.javaguru.java2.controller;

import lv.javaguru.java2.model.MVCModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ruslan on 16.29.3.
 */
public class ViewTimeLapsController implements MVCController{
    @Override
    public MVCModel processGet(HttpServletRequest req) {

        return new MVCModel("/viewTimeLaps.jsp","View Time Laps");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
