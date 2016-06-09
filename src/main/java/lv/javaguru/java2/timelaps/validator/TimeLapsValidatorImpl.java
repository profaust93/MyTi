package lv.javaguru.java2.timelaps.validator;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.timelaps.database.TimeLapsDAO;
import lv.javaguru.java2.timelaps.domain.TimeLaps;
import lv.javaguru.java2.timelaps.exception.TimeLapsError;
import lv.javaguru.java2.timelaps.exception.TimeLapsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Created by Ruslan on 2016.06.09..
 */
public class TimeLapsValidatorImpl implements TimeLapsValidator{

    @Autowired
    TimeLapsDAO timeLapsDAO;

    @Override
    public void validateTimeLaps(TimeLaps timeLaps, Long userId) throws TimeLapsException {
        if(Optional.ofNullable(timeLaps.getCategory()).isPresent() || StringUtils.isBlank(timeLaps.getCategory())){
            throw new TimeLapsException(TimeLapsError.VALIDATION_FAILS);
        }
        if(Optional.ofNullable(timeLaps.getTimeLapsName()).isPresent() || StringUtils.isBlank(timeLaps.getTimeLapsName())){
            throw new TimeLapsException(TimeLapsError.VALIDATION_FAILS);
        }

        if(timeLaps.getTimeLapsId() != null){
            checkIfBelongToUser(timeLaps.getTimeLapsId(),userId);
        }
    }

    @Override
    public void validateTimeLapsForDelete(Long timeLapsId, Long userId) throws TimeLapsException {
        if(timeLapsId != null){
            checkIfBelongToUser(timeLapsId,userId);
        }
    }

    public void checkIfBelongToUser(Long timeLapsId, Long userId) throws TimeLapsException {
        try {
            if (!timeLapsDAO.checkIfBelongToUser(timeLapsId, userId)) {
                throw new TimeLapsException();
            }
        } catch (DBException e) {
            throw new TimeLapsException();
        }
    }
}
