package lv.javaguru.java2.controller.challenge;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.service.validators.ModelChecks;
import lv.javaguru.java2.service.challenge.ChallengeModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by ruslan on 16.18.4.
 */

public class AddChallengeController implements MVCController {
    @Autowired
    ModelChecks modelChecks;
    @Autowired
    ChallengeModel challengeModel;
    @Autowired
    ChallengeDAO challengeDAO;
    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        return new MVCModel("/addChallenge.jsp",null);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) throws RedirectException {
        Map<String,Object> resultCheckMap;
        Challenge challenge = new Challenge();

        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        challenge.setFromUserId(userDTO.getUserId());
        challenge.setToUserId(Long.valueOf(req.getParameter("toUserId")));
        challenge.setChallengeName(req.getParameter("name"));
        challenge.setDescription(req.getParameter("description"));
        challenge.setEndTime(modelChecks.dateConvert(req.getParameter("date")));
        resultCheckMap = challengeModel.addChallenge(challenge);
        return null;
    }
}
