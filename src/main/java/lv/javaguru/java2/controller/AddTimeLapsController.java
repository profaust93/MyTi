
package lv.javaguru.java2.controller;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.service.TimeLapsServices;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


/**
 * Created by ruslan on 16.29.3.
 */
public class AddTimeLapsController implements MVCController {

    private String answer;
    private TimeLapsServices timeLapsServices = new TimeLapsServices();
    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/addTimeLaps.jsp","Add Time Laps");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {

        String userId = req.getParameter("userId");
        String year = req.getParameter("year");
        String month = req.getParameter("month");
        String day = req.getParameter("day");
        String hour = req.getParameter("hour");
        String minute = req.getParameter("minute");
        String category = req.getParameter("category");
        String shortDescription = req.getParameter("shortDescription");
        String longDescription = req.getParameter("longDescription");
        TimeLaps timeLaps = new TimeLaps();


        TimeLapsDAO timeLapsDAO = new TimeLapsDAOImpl();
        try {
            if(timeLapsServices.isNotNull(userId).equalsIgnoreCase("ok")){
                timeLaps.setUserId(Long.parseLong(userId));
            } else throw new DBException(timeLapsServices.isNotNull(userId));

            timeLaps.setCompleteTime(LocalDateTime.of(Integer.parseInt(year),
                    Integer.parseInt(month),Integer.parseInt(day),
                    Integer.parseInt(hour),Integer.parseInt(minute)));
            timeLaps.setCategory(12);
            timeLaps.setShortDescription(shortDescription);
            timeLaps.setLongDescription(longDescription);
            timeLapsDAO.create(timeLaps);
            answer = "OK";
        } catch (DBException e) {
            answer = e.getMessage();
            return new MVCModel("/addTimeLaps.jsp",answer);
        }

        return new MVCModel("/addTimeLaps.jsp",answer);
    }
}
