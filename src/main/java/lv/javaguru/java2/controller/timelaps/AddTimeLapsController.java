
package lv.javaguru.java2.controller.timelaps;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.LoginException;
import lv.javaguru.java2.model.user.UserModel;
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

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        String userId = (String) req.getSession().getAttribute("userId");
        resultCheckMap.put("getUserId",userId);
        return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {

        String userId = (String) req.getSession().getAttribute("userId");
        String date = req.getParameter("date");
        String category = req.getParameter("category");
        String shortDescription = req.getParameter("shortDescription");
        String longDescription = req.getParameter("longDescription");
        TimeLaps timeLaps = new TimeLaps();


        TimeLapsDAO timeLapsDAO = new TimeLapsDAOImpl();
        try {
            resultCheckMap.put("userIdCheckResult", timeLapsServices.userIdCheck(userId));
            resultCheckMap.put("categoryCheckResult",timeLapsServices.categoryCheck(category));
            resultCheckMap.put("dateCheckResult",timeLapsServices.dateCheck(date));

            if(timeLapsServices.userIdCheck(userId).equalsIgnoreCase("ok")){
                timeLaps.setUserId(Long.parseLong(userId));
            }

            if(timeLapsServices.dateCheck(date).equalsIgnoreCase("ok")){
                timeLaps.setCompleteTime(timeLapsServices.dateConvert(date));
            }

            if(timeLapsServices.categoryCheck(category).equalsIgnoreCase("ok")){
                timeLaps.setCategory(category);
            }

            timeLaps.setShortDescription(shortDescription);
            timeLaps.setLongDescription(longDescription);



            timeLapsDAO.create(timeLaps);

            System.out.println(timeLapsDAO.getById(timeLaps.getTimeLapsId()));


        } catch (DBException e) {
            return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
        }

        return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
    }
}
