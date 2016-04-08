
package lv.javaguru.java2.controller.timelaps;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.LoginException;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.model.timelaps.TimeLapsModel;
import lv.javaguru.java2.model.timelaps.TimeLapsModelImpl;
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

    private Map<Object,String> resultCheckMap = new HashMap<>();
    private List<TimeLapsList> list;

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) throws RedirectException {

        TimeLaps timeLaps = new TimeLaps();
        TimeLapsServices timeLapsServices = new TimeLapsServices();

        String userId = (String) req.getSession().getAttribute("userId");
        timeLaps.setUserId(Long.parseLong(userId));
        timeLaps.setTimeLapsName(req.getParameter("name"));
        timeLaps.setCompleteTime(timeLapsServices.dateConvert(req.getParameter("date")));
        timeLaps.setCategory(req.getParameter("category"));
        timeLaps.setShortDescription(req.getParameter("shortDescription"));
        timeLaps.setLongDescription(req.getParameter("longDescription"));


        TimeLapsModel timeLapsModel = new TimeLapsModelImpl();
        timeLapsModel.setTimeLapsDAO(new TimeLapsDAOImpl());
        resultCheckMap = timeLapsModel.addTimeLaps(timeLaps);

        for(Map.Entry entry:resultCheckMap.entrySet()){
            String value = (String) entry.getValue();
            if(!value.equalsIgnoreCase("ok")) return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
        }
        try {
            list = timeLapsModel.getAllTimeLapsForUser(userId);
        } catch (TimeLapsException e) {
            e.printStackTrace();
        }
        throw new RedirectException("Don't need to login again","/java2/viewTimeLaps");
        //return new MVCModel("/viewTimeLaps.jsp",list);
    }
}
