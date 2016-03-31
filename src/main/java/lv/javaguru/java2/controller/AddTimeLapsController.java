
package lv.javaguru.java2.controller;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.service.TimeLapsServices;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ruslan on 16.29.3.
 */
public class AddTimeLapsController implements MVCController {

    private String answer;
    private TimeLapsServices timeLapsServices = new TimeLapsServices();
    private Map<String,String> resultCheckMap = new HashMap<>();

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
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
            resultCheckMap.put(userId,timeLapsServices.userIdCheck(userId));
            if(timeLapsServices.userIdCheck(userId).equalsIgnoreCase("ok")){
                timeLaps.setUserId(Long.parseLong(userId));
            } else{

                throw new DBException(timeLapsServices.userIdCheck(userId));
            }

            timeLaps.setCompleteTime(LocalDateTime.of(Integer.parseInt(year),
                    Integer.parseInt(month),Integer.parseInt(day),
                    Integer.parseInt(hour),Integer.parseInt(minute)));
            timeLaps.setCategory(category);
            timeLaps.setShortDescription(shortDescription);
            timeLaps.setLongDescription(longDescription);
            timeLapsDAO.create(timeLaps);
            answer = "OK";

        } catch (DBException e) {
            answer = e.getMessage();
            return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
        }

        return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
    }
}
