package lv.javaguru.java2.challenge.util;

import lv.javaguru.java2.challenge.exception.ChallengeError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ErrorMessageHandler {

    public static void handleError(RedirectAttributes redirectAttributes, ChallengeError challengeError){
        switch (challengeError){
            case CHALLENGE_ERROR:
                redirectAttributes.addFlashAttribute("error","Service error");
                break;
            case VALIDATION_FAILS:
                redirectAttributes.addFlashAttribute("error", "Validation fails");
                break;
            default:
                redirectAttributes.addFlashAttribute("error", "Service error");
                break;
        }
    }
}
