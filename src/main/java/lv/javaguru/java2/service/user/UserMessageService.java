package lv.javaguru.java2.service.user;

        import lv.javaguru.java2.domain.UserMessage;
        import lv.javaguru.java2.web.form.model.MessageModel;

        import java.util.List;

public interface UserMessageService {

    void sendMessage(MessageModel message);

    void acceptMessage(Long messageId);

    List<MessageModel> getAllMessageForUser(Long UserId);

    void rejectMessage(Long messageId);


}