package lv.javaguru.java2.timelaps.validator;

import lv.javaguru.java2.timelaps.domain.TimeLaps;
import lv.javaguru.java2.timelaps.exception.TimeLapsException;

public interface TimeLapsValidator {

    void validateTimeLaps(TimeLaps timeLaps,Long userId) throws TimeLapsException;

    void validateTimeLapsForDelete(Long timeLapsId, Long userId) throws TimeLapsException;

}
