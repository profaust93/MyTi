package lv.javaguru.java2.controller.message;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.domain.ChallengeMessageList;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.UserMessageException;
import lv.javaguru.java2.service.challenge.ChallengeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan on 2016.04.25..
 */
@Component
public class ViewMessageController implements MVCController {

    @Autowired
    ChallengeMessageService challengeMessageService;

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        List<ChallengeMessageList> list = new ArrayList<>();
        UserDTO userDTO =(UserDTO) req.getSession().getAttribute("user");

        try {
            list = challengeMessageService.getAllMessageForUser(userDTO.getUserId());
        } catch (UserMessageException e) {
            e.printStackTrace();
        }

        return new MVCModel("/viewMessage.jsp",list);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) throws RedirectException {

        if(req.getParameter("AcceptMessageId")!= null){
            challengeMessageService.acceptMessage(Long.parseLong(req.getParameter("AcceptMessageId")));
        }

        if(req.getParameter("RejectMessageId") != null){
            challengeMessageService.rejectMessage(Long.parseLong(req.getParameter("RejectMessageId")));
        }
        return new MVCModel("/redirect.jsp","viewMessage");
    }
}
