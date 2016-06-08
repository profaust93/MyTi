
package lv.javaguru.java2.timelaps.controller;

import lv.javaguru.java2.security.SecurityService;
import lv.javaguru.java2.timelaps.database.TimeLapsDAO;
import lv.javaguru.java2.timelaps.domain.TimeLaps;
import lv.javaguru.java2.timelaps.service.TimeLapsService;
import lv.javaguru.java2.validators.ModelChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AddTimeLapsController {

    @Autowired
    @Qualifier("ORM_TimeLapsDAO")
    TimeLapsDAO timeLapsDAO;
    @Autowired
    TimeLapsService timeLapsService;
    @Autowired
    ModelChecks modelChecks;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/addTimeLaps", method = RequestMethod.GET)
    public ModelAndView processGet(HttpServletRequest req) {
        Map<String,Object> resultCheckMap = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView("addTimeLaps");
        modelAndView.addObject("data",resultCheckMap);
       return modelAndView;
    }


    @RequestMapping(value = "/addTimeLaps", method = RequestMethod.POST)
    public String processPost(HttpServletRequest req) {
        Map<String,Object> resultCheckMap;

        TimeLaps timeLaps = new TimeLaps();


        try {
            timeLaps.setUserId(securityService.getCurrentUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        timeLaps.setTimeLapsName(req.getParameter("name"));
        timeLaps.setCompleteTime(modelChecks.dateConvert(req.getParameter("date")));
        timeLaps.setCategory(req.getParameter("category"));
        timeLaps.setShortDescription(req.getParameter("shortDescription"));
        timeLaps.setLongDescription(req.getParameter("longDescription"));


        resultCheckMap = timeLapsService.addTimeLaps(timeLaps);
        return "redirect:/viewTimeLaps";

    }
}
