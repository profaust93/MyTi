package lv.javaguru.java2.timelaps.exception;

/**
 * Created by Ruslan on 2016.04.04..
 */
public class TimeLapsException extends Exception {

    private TimeLapsError timeLapsError;

    public TimeLapsException(String message){
        super(message);
    }

    public TimeLapsException() {
        super();
        this.timeLapsError = TimeLapsError.TIME_LAPS_ERROR;
    }

    public TimeLapsException(TimeLapsError timeLapsError) {
        super();
        this.timeLapsError = timeLapsError;
    }

    public TimeLapsError getTimeLapsError() {
        return timeLapsError;
    }
}
