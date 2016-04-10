package lv.javaguru.java2.controller.timelaps;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.service.timelaps.TimeLapsModel;
import lv.javaguru.java2.service.timelaps.TimeLapsModelImpl;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ruslan on 16.29.3.
 */
@Component
public class ViewTimeLapsController implements MVCController {

    private List<TimeLapsList> list;

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        UserDTO userDTO =(UserDTO) req.getSession().getAttribute("user");
        TimeLapsModel timeLapsModel = new TimeLapsModelImpl();
        timeLapsModel.setTimeLapsDAO(new TimeLapsDAOImpl());
        try {
            list = timeLapsModel.getAllTimeLapsForUser(String.valueOf(userDTO.getUserId()));

        } catch (TimeLapsException e) {
            e.printStackTrace();
        }
        return new MVCModel("/viewTimeLaps.jsp",list);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return new MVCModel("/redirect.jsp","editTimeLaps");
    }
}
