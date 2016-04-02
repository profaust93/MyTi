package lv.javaguru.java2.controller;


import lv.javaguru.java2.model.MVCModel;

import javax.servlet.http.HttpServletRequest;

public interface MVCController {
    MVCModel processGet(HttpServletRequest req);

    MVCModel processPost(HttpServletRequest req);
}
