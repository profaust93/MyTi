package lv.javaguru.java2.controller.profile;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Camille on 07.04.2016.
 */
public class EditUserProfileController implements MVCController {
    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        return null;
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
