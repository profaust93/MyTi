
package lv.javaguru.java2.controller.timelaps;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.service.timelaps.TimeLapsModel;
import lv.javaguru.java2.service.timelaps.TimeLapsModelImpl;
import lv.javaguru.java2.service.timelaps.TimeLapsChecks;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by ruslan on 16.29.3.
 */
@Component
public class AddTimeLapsController implements MVCController {

    private Map<String,Object> resultCheckMap = new HashMap<>();
    private List<TimeLapsList> list;

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {

        TimeLaps timeLaps = new TimeLaps();
        TimeLapsChecks timeLapsChecks = new TimeLapsChecks();

        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        timeLaps.setUserId(userDTO.getUserId());
        timeLaps.setTimeLapsName(req.getParameter("name"));
        timeLaps.setCompleteTime(timeLapsChecks.dateConvert(req.getParameter("date")));
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
            list = timeLapsModel.getAllTimeLapsForUser(String.valueOf(userDTO.getUserId()));
        } catch (TimeLapsException e) {
            e.printStackTrace();
        }

        return new MVCModel("/redirect.jsp","viewTimeLaps");
    }
}
