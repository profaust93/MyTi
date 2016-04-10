
package lv.javaguru.java2.controller.timelaps;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.service.timelaps.TimeLapsModel;
import lv.javaguru.java2.service.timelaps.TimeLapsModelImpl;
import lv.javaguru.java2.service.timelaps.TimeLapsChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by ruslan on 16.29.3.
 */
@Component
public class AddTimeLapsController implements MVCController {

    @Autowired
    TimeLapsDAO timeLapsDAO;
    @Autowired
    TimeLapsModel timeLapsModel;
    @Autowired
    TimeLapsChecks timeLapsChecks;


    @Override
    public MVCModel processGet(HttpServletRequest req) {
        Map<String,Object> resultCheckMap = new HashMap<>();

        return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        Map<String,Object> resultCheckMap;

        TimeLaps timeLaps = new TimeLaps();


        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        timeLaps.setUserId(userDTO.getUserId());
        timeLaps.setTimeLapsName(req.getParameter("name"));
        timeLaps.setCompleteTime(timeLapsChecks.dateConvert(req.getParameter("date")));
        timeLaps.setCategory(req.getParameter("category"));
        timeLaps.setShortDescription(req.getParameter("shortDescription"));
        timeLaps.setLongDescription(req.getParameter("longDescription"));


        resultCheckMap = timeLapsModel.addTimeLaps(timeLaps);

        for(Map.Entry entry:resultCheckMap.entrySet()){
            String value = (String) entry.getValue();
            if(!value.equalsIgnoreCase("ok")) return new MVCModel("/addTimeLaps.jsp",resultCheckMap);
        }

        return new MVCModel("/redirect.jsp","viewTimeLaps");
    }
}
