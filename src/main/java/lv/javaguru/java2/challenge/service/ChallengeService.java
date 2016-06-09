package lv.javaguru.java2.challenge.service;


import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.challenge.domain.ChallengeList;
import lv.javaguru.java2.challenge.exception.ChallengeException;

import java.util.List;
import java.util.Map;


public interface ChallengeService {

    Challenge getChallengeById(Long challengeId) throws ChallengeException;

    Map<String,Object> addChallenge(Challenge challenge);

    void changeChallengeState(Long challengeId,String state) throws ChallengeException;

    List<ChallengeList> getAllChallengeFromUserId(Long fromUserId) throws ChallengeException;

    List<ChallengeList> getAllChallengeToUserId(Long toUserId) throws ChallengeException;

    void deleteChallenge(Challenge challenge) throws ChallengeException;

}
