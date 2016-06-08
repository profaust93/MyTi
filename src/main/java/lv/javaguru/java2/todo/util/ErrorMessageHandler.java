package lv.javaguru.java2.todo.util;

import lv.javaguru.java2.todo.exception.ToDoError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ErrorMessageHandler {

    public static void handleError(RedirectAttributes redirectAttributes, ToDoError toDoError) {
        switch (toDoError) {
            case TO_DO_ERROR:
                redirectAttributes.addFlashAttribute("error", "Service error");
                break;
            case VALIDATION_FAILS:
                redirectAttributes.addFlashAttribute("error", "Validation fails");
                break;
            default:
                redirectAttributes.addFlashAttribute("error", "Service Error");
                break;
        }
    }
}
