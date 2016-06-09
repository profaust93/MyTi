package lv.javaguru.java2.timelaps.controller;

import lv.javaguru.java2.security.SecurityService;
import lv.javaguru.java2.timelaps.domain.TimeLaps;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.timelaps.exception.TimeLapsException;
import lv.javaguru.java2.timelaps.service.TimeLapsService;
import lv.javaguru.java2.timelaps.validator.ModelChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EditTimeLapsController{

    @Autowired
    TimeLapsService timeLapsService;

    @Autowired
    private SecurityService securityService;

    private ModelChecks modelChecks = new ModelChecks();


    private List<Map> list = new ArrayList<>();
    private Map<String,Object> dataMap = new HashMap<>();
    private Map<String,Object> resultCheckMap = new HashMap<>();

    @RequestMapping(value = "/editTimeLaps", method = RequestMethod.GET)
    public ModelAndView processGet(HttpServletRequest req) throws RedirectException {

        String timeLapsId = req.getParameter("TimeLapsId");


        try {
            TimeLaps timeLaps = timeLapsService.getTimeLapsById(Long.parseLong(timeLapsId));
            dataMap.put("timeLapsId",timeLaps.getTimeLapsId());
            dataMap.put("name",timeLaps.getTimeLapsName());
            dataMap.put("date", modelChecks.dateConvert(timeLaps.getCompleteTime()));
            dataMap.put("category",timeLaps.getCategory());
            dataMap.put("shortDesc",timeLaps.getShortDescription());
            dataMap.put("longDesc",timeLaps.getLongDescription());

        } catch (TimeLapsException e) {
            e.printStackTrace();
        }
        list.add(dataMap);
        list.add(resultCheckMap);
        return new ModelAndView("editTimeLaps").addObject("data",list);
    }

    @RequestMapping(value = "/editTimeLaps", method = RequestMethod.POST)
    public ModelAndView processPost(HttpServletRequest req) {


        try {
            TimeLaps timeLaps = timeLapsService.getTimeLapsById(Long.parseLong(dataMap.get("timeLapsId").toString()));

            timeLaps.setUserId(securityService.getCurrentUserId());
            timeLaps.setTimeLapsName(req.getParameter("name"));
            timeLaps.setCompleteTime(modelChecks.dateConvert(req.getParameter("date")));
            timeLaps.setCategory(req.getParameter("category"));
            timeLaps.setShortDescription(req.getParameter("shortDescription"));
            timeLaps.setLongDescription(req.getParameter("longDescription"));

            dataMap.put("name",timeLaps.getTimeLapsName());
            dataMap.put("date", modelChecks.dateConvert(timeLaps.getCompleteTime()));
            dataMap.put("category",timeLaps.getCategory());
            dataMap.put("shortDesc",timeLaps.getShortDescription());
            dataMap.put("longDesc",timeLaps.getLongDescription());

            resultCheckMap = timeLapsService.editTimeLaps(timeLaps);

            list.add(dataMap);
            list.add(resultCheckMap);

                if(resultCheckMap.size() != 0) return new ModelAndView("editTimeLaps").addObject("data",list);


        }catch (TimeLapsException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/viewTimeLaps");
    }
}
