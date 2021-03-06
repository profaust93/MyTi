package lv.javaguru.java2.controller.timelaps;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.service.timelaps.TimeLapsService;
import lv.javaguru.java2.service.validators.ModelChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EditTimeLapsController implements MVCController{

    @Autowired
    TimeLapsService timeLapsService;

    ModelChecks modelChecks = new ModelChecks();


    private List<Map> list = new ArrayList<>();
    private Map<String,Object> dataMap = new HashMap<>();
    private Map<String,Object> resultCheckMap = new HashMap<>();

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {

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
        return new MVCModel("/editTimeLaps.jsp",list);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {


        try {
            TimeLaps timeLaps = timeLapsService.getTimeLapsById(Long.parseLong(dataMap.get("timeLapsId").toString()));


            UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");

            timeLaps.setUserId(userDTO.getUserId());
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

                if(resultCheckMap.size() != 0) return new MVCModel("/editTimeLaps.jsp",list);


        }catch (TimeLapsException e){
            e.printStackTrace();
        }

        return new MVCModel("/redirect.jsp","viewTimeLaps");
    }
}
