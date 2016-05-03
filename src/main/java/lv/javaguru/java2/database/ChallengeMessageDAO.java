package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.ChallengeMessage;

import java.util.List;

public interface ChallengeMessageDAO {

    void create(ChallengeMessage challengeMessage) throws DBException;

    ChallengeMessage getById(Long id) throws DBException;

    void delete(ChallengeMessage challengeMessage) throws DBException;

    List<ChallengeMessage> getAllByRecipientId(Long recipientId) throws DBException;

}
