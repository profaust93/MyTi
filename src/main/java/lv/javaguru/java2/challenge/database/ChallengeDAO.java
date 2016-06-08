package lv.javaguru.java2.challenge.database;

import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.database.DBException;

import java.util.List;


public interface ChallengeDAO {

    void saveOrUpdate(Challenge challenge) throws DBException;

    void delete(Challenge challenge) throws DBException;


    Challenge getChallengeById(Long challengeId) throws DBException;

    List<Challenge> getAllChallengesByUserId(Long userId) throws DBException;

    List<Challenge> getAllChallengesForUserView(Long userId, Integer limit, Integer offset) throws DBException;
}
