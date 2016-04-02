package lv.javaguru.java2.controller;


import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;

import javax.servlet.http.HttpServletRequest;

public interface MVCController {
    MVCModel processGet(HttpServletRequest req) throws RedirectException;

    MVCModel processPost(HttpServletRequest req);
}
