package lv.javaguru.java2.controller.message;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.domain.UserMessageList;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.UserMessageException;
import lv.javaguru.java2.service.user.UserMessageService;
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
    UserMessageService userMessageService;

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        List<UserMessageList> list = new ArrayList<>();
        UserDTO userDTO =(UserDTO) req.getSession().getAttribute("user");

        try {
            list = userMessageService.getAllMessageForUser(userDTO.getUserId());
        } catch (UserMessageException e) {
            e.printStackTrace();
        }

        return new MVCModel("/viewMessage.jsp",list);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) throws RedirectException {

        if(req.getParameter("AcceptMessageId")!= null){
            userMessageService.acceptMessage(Long.parseLong(req.getParameter("AcceptMessageId")));
        }

        if(req.getParameter("RejectMessageId") != null){
            userMessageService.rejectMessage(Long.parseLong(req.getParameter("RejectMessageId")));
        }
        return new MVCModel("/redirect.jsp","viewMessage");
    }
}
