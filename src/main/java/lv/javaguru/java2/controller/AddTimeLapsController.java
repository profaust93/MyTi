
package lv.javaguru.java2.controller;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.service.TimeLapsServices;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private Map<Object,String> resultCheckMap = new HashMap<>();

    //KEYS
    String userIdKey = "userId";
    String yearKey = "year";
    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {

        String userId = req.getParameter("userId");
        String date = req.getParameter("datepicker");
        String hour = req.getParameter("hour");
        String minute = req.getParameter("minute");
        String category = req.getParameter("category");
        String shortDescription = req.getParameter("shortDescription");
        String longDescription = req.getParameter("longDescription");
        TimeLaps timeLaps = new TimeLaps();


        TimeLapsDAO timeLapsDAO = new TimeLapsDAOImpl();
        try {
            resultCheckMap.put("userId", timeLapsServices.userIdCheck(userId));
            if(timeLapsServices.userIdCheck(userId).equalsIgnoreCase("ok")){
                timeLaps.setUserId(Long.parseLong(userId));
            } else throw new DBException(timeLapsServices.userIdCheck(userId));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime dateTime = LocalDateTime.parse(date,formatter);
            timeLaps.setCompleteTime(dateTime);

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
