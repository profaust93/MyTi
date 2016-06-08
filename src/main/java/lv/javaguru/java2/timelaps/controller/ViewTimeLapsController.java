package lv.javaguru.java2.timelaps.controller;

import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.security.SecurityService;
import lv.javaguru.java2.security.UserSecurityEntity;
import lv.javaguru.java2.timelaps.domain.TimeLaps;
import lv.javaguru.java2.timelaps.domain.TimeLapsList;
import lv.javaguru.java2.timelaps.exception.TimeLapsException;
import lv.javaguru.java2.timelaps.service.TimeLapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ViewTimeLapsController {

    @Autowired
    TimeLapsService timeLapsService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/viewTimeLaps", method = RequestMethod.GET)
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

    @RequestMapping(value = "/deleteAllTimeLaps", method = RequestMethod.GET)
    public String processPost() {

            try {
                timeLapsService.deleteAllTimeLaps(securityService.getCurrentUserId());
            } catch (Exception e) {
                e.printStackTrace();
            }

        return "redirect:/viewTimeLaps";
    }

    @RequestMapping(value = "/deleteTimeLaps/{id}", method = RequestMethod.GET)
    public String deleteTimeLaps(@PathVariable("id") Long id) {

        try {
            TimeLaps timeLaps = timeLapsService.getTimeLapsById(id);
            timeLapsService.deleteTimeLaps(timeLaps);
        } catch (TimeLapsException e) {
            e.printStackTrace();
        }
        return "redirect:/viewTimeLaps";
    }
}
