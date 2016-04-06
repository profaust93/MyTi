
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

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {

        String userId = (String) req.getSession().getAttribute("userId");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String category = req.getParameter("category");
        String shortDescription = req.getParameter("shortDescription");
        String longDescription = req.getParameter("longDescription");

        TimeLapsModel timeLapsModel = new TimeLapsModelImpl();
        resultCheckMap = timeLapsModel.addTimeLaps(userId,name,date,category,
                shortDescription,longDescription);

        return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
    }
}
