package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.UserMessage;

import java.util.List;

/**
 * Created by Ruslan on 2016.04.24..
 */
public interface UserMessageDAO {

    void create(UserMessage userMessage) throws DBException;

    UserMessage getById(Long id) throws DBException;

    void delete(UserMessage userMessage) throws DBException;

    List<UserMessage> getAllByRecipientId(Long recipientId) throws DBException;

}
