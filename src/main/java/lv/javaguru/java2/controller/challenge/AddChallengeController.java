package lv.javaguru.java2.controller.challenge;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.service.validators.ModelChecks;
import lv.javaguru.java2.service.challenge.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruslan on 16.18.4.
 */
@Component
public class AddChallengeController implements MVCController {
    @Autowired
    ModelChecks modelChecks;
    @Autowired
    ChallengeService challengeService;
    @Autowired
    @Qualifier("ORM_ChallengeDAO")
    ChallengeDAO challengeDAO;
    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        Map<String,Object> resultCheckMap =new HashMap<>();
        return new MVCModel("/addChallenge.jsp",resultCheckMap);
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

        resultCheckMap = challengeService.addChallenge(challenge);


        for(Map.Entry entry:resultCheckMap.entrySet()){
            String value = (String) entry.getValue();
            if(!value.equalsIgnoreCase("ok")) return new MVCModel("/addChallenge.jsp",resultCheckMap);
        }

        return new MVCModel("/addChallenge.jsp",resultCheckMap);
    }
}
