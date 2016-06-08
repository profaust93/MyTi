package lv.javaguru.java2.challenge.validator;

import lv.javaguru.java2.challenge.exception.ChallengeException;
import lv.javaguru.java2.challenge.form.ChallengeFormModel;

public interface ChallengeValidator {

    void validateChallenge(ChallengeFormModel challengeFormModel, Long senderId, Long receiverId) throws ChallengeException;
}
