package lv.javaguru.java2.challenge.exception;

/**
 * Created by Ruslan on 2016.06.08..
 */
public class ChallengeException {

    ChallengeError challengeError;

    public ChallengeException() {
        this.challengeError = ChallengeError.CHALLENGE_ERROR;
    }

    public ChallengeException(ChallengeError challengeError) {
        super();
        this.challengeError = challengeError;
    }

    public ChallengeError getChallengeError() {
        return challengeError;
    }
}
