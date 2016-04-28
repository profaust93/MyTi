package lv.javaguru.java2.service.user;

import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.domain.UserMessageList;
import lv.javaguru.java2.model.exceptions.UserMessageException;
import lv.javaguru.java2.web.form.model.MessageModel;

import java.util.List;

public interface UserMessageService {

    void sendMessage(UserMessage userMessage);

    void acceptMessage(Long messageId);

    List<UserMessageList> getAllMessageForUser(Long userId) throws UserMessageException;

    void rejectMessage(Long messageId);


}