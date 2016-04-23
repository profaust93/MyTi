package lv.javaguru.java2.service.user;

        import lv.javaguru.java2.domain.UserMessage;

        import java.util.List;

public interface UserMessageService {

    void sendMessage(UserMessage message);

    void acceptMessage(Long messageId);

    List<UserMessage> getAllMessageForUser(Long UserId);

    void rejectMessage(Long messageId);


}