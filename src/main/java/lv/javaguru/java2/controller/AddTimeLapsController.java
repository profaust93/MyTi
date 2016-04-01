
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
import java.util.*;


/**
 * Created by ruslan on 16.29.3.
 */
public class AddTimeLapsController implements MVCController {

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
        String category = req.getParameter("category");
        String shortDescription = req.getParameter("shortDescription");
        String longDescription = req.getParameter("longDescription");
        TimeLaps timeLaps = new TimeLaps();


        TimeLapsDAO timeLapsDAO = new TimeLapsDAOImpl();
        try {
            resultCheckMap.put("userId", timeLapsServices.userIdCheck(userId));
            resultCheckMap.put("category",timeLapsServices.categoryCheck(category));
            if(timeLapsServices.userIdCheck(userId).equalsIgnoreCase("ok")){
                timeLaps.setUserId(Long.parseLong(userId));
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");



            timeLaps.setCompleteTime(LocalDateTime.now());
            if(timeLapsServices.categoryCheck(category).equalsIgnoreCase("ok")){
                timeLaps.setCategory(category);
            }

            timeLaps.setShortDescription(shortDescription);
            timeLaps.setLongDescription(longDescription);

            Iterator it = resultCheckMap.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry<String,String> entry = (Map.Entry<String, String>) it.next();
                if(entry.getValue().equalsIgnoreCase("ok")== false) throw new DBException("Error");
            }

            timeLapsDAO.create(timeLaps);


        } catch (DBException e) {
            return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
        }

        return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
    }
}
