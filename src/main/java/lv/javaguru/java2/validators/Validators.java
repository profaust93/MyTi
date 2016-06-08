package lv.javaguru.java2.validators;

import lv.javaguru.java2.timelaps.domain.TimeLaps;

public interface Validators {

    void timeLapsValidator(TimeLaps timeLaps) throws ValidatorException;

}
