package lv.javaguru.java2.service.challenge;

import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.ChallengeList;
import lv.javaguru.java2.model.exceptions.ChallengeException;
import lv.javaguru.java2.service.ModelChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Ruslan on 2016.04.17..
 */

public class ChallengeModelImpl implements ChallengeModel {
    @Autowired
    ChallengeDAO challengeDAO;
    @Autowired
    ModelChecks modelChecks;
    @Override
    public Challenge getChallengeById(Long challengeId) throws ChallengeException {
        try {
            return challengeDAO.getById(challengeId);
        } catch (DBException e){
            throw new ChallengeException(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> addChallenge(Challenge challenge){
        Map<String,Object> resultCheckMap = new HashMap();

        try {
            resultCheckMap.put("toUserIdCheck", modelChecks.userIdCheck(String.valueOf(challenge.getToUserId())));
            resultCheckMap.put("fromUserIdCheck", modelChecks.userIdCheck(String.valueOf(challenge.getFromUserId())));
            resultCheckMap.put("nameCheck", modelChecks.nameCheck(challenge.getChallengeName()));
            resultCheckMap.put("descriptionCheck", modelChecks.descriptionCheck(challenge.getDescription(), 1000));

            for (Map.Entry entry : resultCheckMap.entrySet()) {
                String value = (String) entry.getValue();
                if (!value.equalsIgnoreCase("ok")) throw new DBException("Error");
            }

            challengeDAO.create(challenge);
        }catch (DBException e){
            return resultCheckMap;
        }
        return resultCheckMap;
    }

    @Override
    public void changeChallengeState(Challenge challenge) throws ChallengeException {
        Map<String,Object> resultCheckMap = new HashMap<>();

        try {
            resultCheckMap.put("stateCheck",modelChecks.stateCheck(challenge.getChallengeState()));
            for (Map.Entry entry : resultCheckMap.entrySet()) {
                String value = (String) entry.getValue();
                if (!value.equalsIgnoreCase("ok")) throw new DBException("Error");
            }
            challengeDAO.changeState(challenge);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<ChallengeList> getAllChallengeFromUserId(Long fromUserId) throws ChallengeException {
        try {
            List<Challenge> list = challengeDAO.getAllChallengeFromUserId(fromUserId);
            return list.stream().map(challenge ->
                    new ChallengeList(challenge.getChallengeId(),challenge.getChallengeName(),
                            challenge.getFromUserId(),challenge.getToUserId(),challenge.getChallengeState(),
                            challenge.getDescription(),challenge.getEndTime())).collect(Collectors.toList());
        } catch (DBException e) {
            throw new ChallengeException(e.getMessage());
        }
    }

    @Override
    public List<ChallengeList> getAllChallengeToUserId(Long toUserId) throws ChallengeException {
        try {
            List<Challenge> list = challengeDAO.getAllChallengeToUserId(toUserId);
            return list.stream().map(challenge -> new ChallengeList(
                    challenge.getChallengeId(),challenge.getChallengeName(),challenge.getFromUserId(),
                    challenge.getToUserId(),challenge.getChallengeState(),challenge.getDescription(),challenge.getEndTime()
            )).collect(Collectors.toList());
        } catch (DBException e) {
            throw new ChallengeException(e.getMessage());
        }
    }

    @Override
    public void deleteChallenge(Challenge challenge) throws ChallengeException {
        try{
            challengeDAO.delete(challenge);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
