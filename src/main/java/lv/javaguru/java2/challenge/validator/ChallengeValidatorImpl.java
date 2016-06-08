package lv.javaguru.java2.challenge.validator;

import lv.javaguru.java2.challenge.database.ChallengeDAO;
import lv.javaguru.java2.challenge.exception.ChallengeError;
import lv.javaguru.java2.challenge.exception.ChallengeException;
import lv.javaguru.java2.challenge.form.ChallengeFormModel;
import lv.javaguru.java2.database.DBException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChallengeValidatorImpl implements ChallengeValidator {

    @Autowired
    ChallengeDAO challengeDAO;

    @Override
    public void validateChallenge(ChallengeFormModel challengeFormModel, Long senderId, Long receiverId) throws ChallengeException {
        if (!Optional.ofNullable(challengeFormModel.getChallengeName()).isPresent() || StringUtils.isBlank(challengeFormModel.getChallengeName())) {
            throw new ChallengeException(ChallengeError.CHALLENGE_ERROR);
        }
        if (challengeFormModel.getChallengeId() != null) {
            checkIfChallengeBelongToUser(challengeFormModel.getChallengeId(), challengeFormModel.getReceiverId());
        }

        if (challengeFormModel.getSubChallengeForms() == null || challengeFormModel.getSubChallengeForms().size() == 0) {
            throw new ChallengeException(ChallengeError.VALIDATION_FAILS);
        }

    }

    private void checkIfChallengeBelongToUser(Long challengeId, Long userId) throws ChallengeException {
        try{
            if(!challengeDAO.checkIfBelongToUser(challengeId,userId)) throw new ChallengeException();
        } catch (DBException e) {
            throw new ChallengeException();
        }
    }
}
