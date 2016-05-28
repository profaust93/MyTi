package lv.javaguru.java2.service.validators;

import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.ChallengeMessage;
import lv.javaguru.java2.timelaps.domain.TimeLaps;

public interface Validators {

    void timeLapsValidator(TimeLaps timeLaps) throws ValidatorException;

    void challengeValidator(Challenge challenge) throws ValidatorException;

    void challengeMessageValidator(ChallengeMessage challengeMessage) throws ValidatorException;

}
