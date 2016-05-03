package lv.javaguru.java2.service.challenge;

import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.ChallengeList;
import lv.javaguru.java2.model.exceptions.ChallengeException;
import lv.javaguru.java2.service.validators.ValidatorException;
import lv.javaguru.java2.service.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class ChallengeServiceImpl implements ChallengeService {
    @Autowired
    @Qualifier("ORM_ChallengeDAO")
    ChallengeDAO challengeDAO;


    @Autowired
    Validators validators;

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
        Map<String,Object> resultCheckMap = new HashMap<>();
        challenge.setChallengeState("Pending");
        try{
            validators.challengeValidator(challenge);
            challengeDAO.create(challenge);
        } catch (ValidatorException e) {
            return e.getMap();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return resultCheckMap;
    }

    @Override
    public void changeChallengeState(Long challengeId,String state) throws ChallengeException {

        try {
            Challenge challenge = challengeDAO.getById(challengeId);
            if(state.equalsIgnoreCase("reject")){
                challengeDAO.delete(challenge);
            } else {
                challenge.setChallengeState(state);
                challengeDAO.update(challenge);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<ChallengeList> getAllChallengeFromUserId(Long fromUserId) throws ChallengeException {
        try {
            List<Challenge> list = challengeDAO.getAllChallengeFromUserId(fromUserId);
            return list.stream().map(challenge ->
                    new ChallengeList(challenge)).collect(Collectors.toList());
        } catch (DBException e) {
            throw new ChallengeException(e.getMessage());
        }
    }

    @Override
    public List<ChallengeList> getAllChallengeToUserId(Long toUserId) throws ChallengeException {
        try {
            List<Challenge> list = challengeDAO.getAllChallengeToUserId(toUserId);
            return list.stream().map(challenge -> new ChallengeList(
                    challenge)).collect(Collectors.toList());
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
