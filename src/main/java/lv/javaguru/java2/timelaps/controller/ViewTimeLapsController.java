package lv.javaguru.java2.timelaps.controller;

import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.security.UserSecurityEntity;
import lv.javaguru.java2.timelaps.domain.TimeLaps;
import lv.javaguru.java2.timelaps.domain.TimeLapsList;
import lv.javaguru.java2.timelaps.exception.TimeLapsException;
import lv.javaguru.java2.timelaps.service.TimeLapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ViewTimeLapsController {

    @Autowired
    TimeLapsService timeLapsService;

    @RequestMapping(value = "/timeLaps", method = RequestMethod.GET)
    public ModelAndView processGet(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView("viewTimeLaps");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSecurityEntity user =(UserSecurityEntity)authentication.getPrincipal();
        try {
            List<TimeLapsList> list = timeLapsService.getAllTimeLapsForUser(String.valueOf(user.getUserId()));
            modelAndView.addObject("data",list);
        } catch (TimeLapsException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    public MVCModel processPost(HttpServletRequest req) {
        String deleteTimeLapsById = req.getParameter("DeleteTimeLapsById");
        String deleteAllTimeLaps = req.getParameter("DeleteAllTimeLaps");
        String addTimeLaps = req.getParameter("AddTimeLaps");
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        if (deleteTimeLapsById != null) {
            try {
                TimeLaps timeLaps = timeLapsService.getTimeLapsById(Long.parseLong(deleteTimeLapsById));
                timeLapsService.deleteTimeLaps(timeLaps);
                return new MVCModel("/redirect.jsp", "viewTimeLaps");
            } catch (TimeLapsException e) {
                e.printStackTrace();
            }
        }

        if (deleteAllTimeLaps != null) {
            timeLapsService.deleteAllTimeLaps(userDTO.getUserId());
            return new MVCModel("/redirect.jsp", "viewTimeLaps");
        }

        if(addTimeLaps != null){
            return new MVCModel("/redirect.jsp","addTimeLaps");
        }
            return new MVCModel("/redirect.jsp", "editTimeLaps");
    }
}
