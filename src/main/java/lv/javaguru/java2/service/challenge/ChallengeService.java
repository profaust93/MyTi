package lv.javaguru.java2.service.challenge;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.ChallengeList;
import lv.javaguru.java2.model.exceptions.ChallengeException;

import java.util.List;
import java.util.Map;

/**
 * Created by Ruslan on 2016.04.17..
 */
public interface ChallengeService {

    Challenge getChallengeById(Long challengeId) throws ChallengeException;

    Map<String,Object> addChallenge(Challenge challenge);

    void changeChallengeState(Long challengeId,String state) throws ChallengeException;

    List<ChallengeList> getAllChallengeFromUserId(Long fromUserId) throws ChallengeException;

    List<ChallengeList> getAllChallengeToUserId(Long toUserId) throws ChallengeException;

    void deleteChallenge(Challenge challenge) throws ChallengeException;

}
