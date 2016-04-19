package lv.javaguru.java2.controller.timelaps;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.service.timelaps.TimeLapsModel;
import lv.javaguru.java2.service.timelaps.TimeLapsModelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ruslan on 16.29.3.
 */
@Component
public class ViewTimeLapsController implements MVCController {

    @Autowired
    TimeLapsDAO timeLapsDAO;
    @Autowired
    TimeLapsModel timeLapsModel;



    @Override
    public MVCModel processGet(HttpServletRequest req) {
        List<TimeLapsList> list = new ArrayList<>();
        UserDTO userDTO =(UserDTO) req.getSession().getAttribute("user");


        try {
            list = timeLapsModel.getAllTimeLapsForUser(String.valueOf(userDTO.getUserId()));

        } catch (TimeLapsException e) {
            e.printStackTrace();
        }
        return new MVCModel("/viewTimeLaps.jsp",list);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        String deleteTimeLapsById = req.getParameter("DeleteTimeLapsById");
        String deleteAllTimeLaps = req.getParameter("DeleteAllTimeLaps");
        String addTimeLaps = req.getParameter("AddTimeLaps");
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        if (deleteTimeLapsById != null) {
            try {
                TimeLaps timeLaps = timeLapsModel.getTimeLapsById(Long.parseLong(deleteTimeLapsById));
                timeLapsModel.deleteTimeLaps(timeLaps);
                return new MVCModel("/redirect.jsp", "viewTimeLaps");
            } catch (TimeLapsException e) {
                e.printStackTrace();
            }
        }

        if (deleteAllTimeLaps != null) {
            timeLapsModel.deleteAllTimeLaps(userDTO.getUserId());
            return new MVCModel("/redirect.jsp", "viewTimeLaps");
        }

        if(addTimeLaps != null){
            return new MVCModel("/redirect.jsp","addTimeLaps");
        }
            return new MVCModel("/redirect.jsp", "editTimeLaps");
    }
}
