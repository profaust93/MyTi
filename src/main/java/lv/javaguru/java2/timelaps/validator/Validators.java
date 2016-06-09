package lv.javaguru.java2.timelaps.validator;

import lv.javaguru.java2.timelaps.domain.TimeLaps;

public interface Validators {

    void timeLapsValidator(TimeLaps timeLaps) throws ValidatorException;

}
