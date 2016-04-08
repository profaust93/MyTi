package lv.javaguru.java2.controller.timelaps;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.model.timelaps.TimeLapsModel;
import lv.javaguru.java2.model.timelaps.TimeLapsModelImpl;
import lv.javaguru.java2.service.TimeLapsChecks;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruslan on 2016.04.08..
 */
@Component
public class EditTimeLapsController implements MVCController{


    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        TimeLapsChecks timeLapsChecks = new TimeLapsChecks();
        TimeLapsModel timeLapsModel = new TimeLapsModelImpl();
        timeLapsModel.setTimeLapsDAO(new TimeLapsDAOImpl());

        Map<Object,String> map = new HashMap<>();

        String timeLapsId = "10";

        try {
            TimeLaps timeLaps = timeLapsModel.getTimeLapsById(Long.parseLong(timeLapsId));
            map.put("name",timeLaps.getTimeLapsName());
            map.put("date",timeLapsChecks.dateConvert(timeLaps.getCompleteTime()));
            map.put("category",timeLaps.getCategory());
            map.put("shortDesc",timeLaps.getShortDescription());
            map.put("longDesc",timeLaps.getLongDescription());
        } catch (TimeLapsException e) {
            e.printStackTrace();
        }
        return new MVCModel("/editTimeLaps.jsp",map);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
