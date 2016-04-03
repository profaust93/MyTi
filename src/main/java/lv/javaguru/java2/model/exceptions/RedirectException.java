package lv.javaguru.java2.model.exceptions;

public class RedirectException extends Exception{
    private String urlToRedirect;

    public RedirectException(String message,String urlToRedirect) {

        super(message);
        this.urlToRedirect = urlToRedirect;
    }

    public String getUrlToRedirect() {
        return urlToRedirect;
    }
}
