package lv.javaguru.java2.model.exceptions;


import java.util.List;

public class RegisterException extends Exception {
    private List<String> badFields;
    public RegisterException(String message) {
        super(message);
    }
    public RegisterException(String message, List<String> badFields) {

        super(message);
        this.badFields = badFields;
    }

    public List<String> getBadFields() {
        return badFields;
    }

    public void setBadFields(List<String> badFields) {
        this.badFields = badFields;
    }
}
