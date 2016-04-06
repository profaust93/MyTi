package lv.javaguru.java2.controller.timelaps;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.model.timelaps.TimeLapsModel;
import lv.javaguru.java2.model.timelaps.TimeLapsModelImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruslan on 16.29.3.
 */
public class ViewTimeLapsController implements MVCController {

    private List<TimeLapsList> list;

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        String userId = (String) req.getSession().getAttribute("userId");
        TimeLapsModel timeLapsModel = new TimeLapsModelImpl();
        timeLapsModel.setTimeLapsDAO(new TimeLapsDAOImpl());
        try {
            list = timeLapsModel.getAllTimeLapsForUser(userId);

        } catch (TimeLapsException e) {
            e.printStackTrace();
        }

        return new MVCModel("/viewTimeLaps.jsp",list);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
