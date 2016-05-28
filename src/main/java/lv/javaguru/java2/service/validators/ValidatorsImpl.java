package lv.javaguru.java2.service.validators;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.ChallengeMessage;
import lv.javaguru.java2.timelaps.domain.TimeLaps;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidatorsImpl implements Validators {
    private ModelChecks modelChecks = new ModelChecks();

    public void timeLapsValidator(TimeLaps timeLaps) throws ValidatorException {

        Map<String, Object> resultCheckMap = new HashMap<>();
        try {
            resultCheckMap.put("userIdCheckResult", modelChecks.userIdCheck(String.valueOf(timeLaps.getUserId())));
            resultCheckMap.put("categoryCheckResult", modelChecks.categoryCheck(timeLaps.getCategory()));
            resultCheckMap.put("nameCheckResult", modelChecks.nameCheck(timeLaps.getTimeLapsName()));
            resultCheckMap.put("dateCheckResult", modelChecks.dateCheck(String.valueOf(timeLaps.getCompleteTime())));
            resultCheckMap.put("sDescCheck", modelChecks.descriptionCheck(timeLaps.getShortDescription(), 100));
            resultCheckMap.put("lDescCheck", modelChecks.descriptionCheck(timeLaps.getLongDescription(), 1000));

            for (Map.Entry entry : resultCheckMap.entrySet()) {
                String value = (String) entry.getValue();
                if (!value.equalsIgnoreCase("ok")) throw new DBException("Error");
            }
        } catch (DBException e) {
            throw new ValidatorException(resultCheckMap,e);
        }
    }

    public void challengeValidator(Challenge challenge) throws ValidatorException {
        Map<String, Object> resultCheckMap = new HashMap<>();
        try {
            resultCheckMap.put("toUserIdCheck", modelChecks.userIdCheck(String.valueOf(challenge.getToUserId())));
            resultCheckMap.put("fromUserIdCheck", modelChecks.userIdCheck(String.valueOf(challenge.getFromUserId())));
            resultCheckMap.put("nameCheck", modelChecks.nameCheck(challenge.getChallengeName()));
            resultCheckMap.put("descriptionCheck", modelChecks.descriptionCheck(challenge.getDescription(), 1000));

            for (Map.Entry entry : resultCheckMap.entrySet()) {
                String value = (String) entry.getValue();
                if (!value.equalsIgnoreCase("ok")) throw new DBException("Error");
            }
        } catch (DBException e) {
            throw new ValidatorException(resultCheckMap, e);
        }
    }

    public void challengeMessageValidator(ChallengeMessage challengeMessage) throws ValidatorException{
        Map<String,Object> resultCheckMap = new HashMap<>();
        try{
            resultCheckMap.put("recipientIdCheck",modelChecks.userIdCheck(String.valueOf(challengeMessage.getRecipientId())));
            resultCheckMap.put("senderIdCheck",modelChecks.userIdCheck(String.valueOf(challengeMessage.getSenderId())));
            resultCheckMap.put("nameCheck",modelChecks.nameCheck(challengeMessage.getMessage()));
            resultCheckMap.put("descriptionCheck",modelChecks.descriptionCheck(challengeMessage.getMessage(),1000));

            for (Map.Entry entry : resultCheckMap.entrySet()) {
                String value = (String) entry.getValue();
                if (!value.equalsIgnoreCase("ok")) throw new DBException("Error");
            }
        } catch (DBException e) {
            throw new ValidatorException(resultCheckMap, e);
        }
    }
}
