package lv.javaguru.java2.controller.user;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ruslan on 16.18.4.
 */
public class UserListController implements MVCController {
    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        return null;
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) throws RedirectException {
        return null;
    }
}
