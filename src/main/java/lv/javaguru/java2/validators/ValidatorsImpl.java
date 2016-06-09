package lv.javaguru.java2.validators;

import lv.javaguru.java2.database.DBException;

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
//            resultCheckMap.put("userIdCheckResult", modelChecks.userIdCheck(String.valueOf(timeLaps.getUserId())));
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
            throw new ValidatorException(resultCheckMap, e);
        }
    }
}
