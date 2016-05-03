package lv.javaguru.java2.controller.challenge;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.domain.ChallengeList;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.ChallengeException;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.service.challenge.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class ViewChallengeController implements MVCController {
    @Autowired
    ChallengeService challengeService;

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        List<ChallengeList> list = new ArrayList<>();
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        try {
            list = challengeService.getAllChallengeToUserId(userDTO.getUserId());
        } catch (ChallengeException e) {
            e.printStackTrace();
        }
        return new MVCModel("/viewChallenge.jsp",list);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) throws RedirectException {
        return null;
    }
}
