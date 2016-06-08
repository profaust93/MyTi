package lv.javaguru.java2.validators;

import java.util.Map;

public class ValidatorException extends Exception {

    private Map<String,Object> resultCheckMap;

    public ValidatorException(Map<String,Object> map, Throwable e) {
        super(e);
        this.resultCheckMap = map;
    }

    public Map<String,Object> getMap(){
        return resultCheckMap;
    }
}
