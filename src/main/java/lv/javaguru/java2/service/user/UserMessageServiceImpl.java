package lv.javaguru.java2.service.user;

import lv.javaguru.java2.web.form.model.MessageModel;

import java.util.List;

/**
 * Created by Ruslan on 2016.04.24..
 */
public class UserMessageServiceImpl implements UserMessageService {
    @Override
    public void sendMessage(MessageModel message) {

    }

    @Override
    public void acceptMessage(Long messageId) {

    }

    @Override
    public List<MessageModel> getAllMessageForUser(Long UserId) {
        return null;
    }

    @Override
    public void rejectMessage(Long messageId) {

    }
}
