package lv.javaguru.java2.controller.todo;


import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;

import javax.servlet.http.HttpServletRequest;

public class ToDoViewController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        return null;
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
