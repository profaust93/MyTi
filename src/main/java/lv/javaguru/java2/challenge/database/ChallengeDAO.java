package lv.javaguru.java2.challenge.database;

import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.database.DBException;

import java.util.List;

public interface ChallengeDAO {

    void create(Challenge challenge)throws DBException;

    void delete(Challenge challenge)throws DBException;

    Challenge getById(Long id)throws DBException;

    void update(Challenge challenge) throws DBException;

    List<Challenge> getAllChallenge() throws DBException;

    List<Challenge> getAllChallengeFromUserId(Long fromUserId) throws DBException;

    List<Challenge> getAllChallengeToUserId(Long userId) throws DBException;

    Long getTotalChallengeCount(Long userId) throws DBException;
}
