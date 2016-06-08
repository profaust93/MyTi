package lv.javaguru.java2.challenge.service;

import lv.javaguru.java2.challenge.exception.ChallengeException;
import lv.javaguru.java2.challenge.form.ChallengeFormModel;

public interface ChallengeService {

    void upsertChallenge(ChallengeFormModel challengeFormModel) throws ChallengeException;

    ChallengeFormModel getChallengeById(Long challengeId) throws ChallengeException;

}
