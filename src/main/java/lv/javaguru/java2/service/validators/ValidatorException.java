package lv.javaguru.java2.service.validators;

import java.util.Map;

/**
 * Created by Ruslan on 2016.04.20..
 */
public class ValidatorException extends Exception {

    private Map<String,Object> resultCheckMap;

    public ValidatorException(Map<String,Object> map) {
        this.resultCheckMap = map;
    }

    public Map<String,Object> getMap(){
        return resultCheckMap;
    }
}
