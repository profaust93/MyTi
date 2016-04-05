package lv.javaguru.java2.model.timelaps;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.service.TimeLapsServices;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Ruslan on 2016.04.04..
 */
public class TimeLapsModelImpl implements TimeLapsModel {

    TimeLapsDAO timeLapsDAO;
    private TimeLapsServices timeLapsServices = new TimeLapsServices();
    @Override
    public void setTimeLapsDAO(TimeLapsDAO timeLapsDAO) {
        this.timeLapsDAO = timeLapsDAO;
    }

    @Override
    public TimeLaps getTimeLapsById(Long timeLapsId) throws TimeLapsException {
        try {
            return timeLapsDAO.getById(timeLapsId);
        } catch (DBException e) {
            throw new TimeLapsException(e.getMessage());
        }
    }

    @Override
    public List<TimeLapsList> getAllTimeLapsForUser(String userId) throws TimeLapsException {
        try {
            List<TimeLaps> allTimeLaps  = timeLapsDAO.getAllTimeLapsByUserId(Long.parseLong(userId));
            return allTimeLaps.stream().map(timelaps ->
                    new TimeLapsList(timelaps.getTimeLapsId(),timelaps.getTimeLapsName(),timelaps.getCompleteTime()))
                    .collect(Collectors.toList());
        } catch (DBException e) {
            throw new TimeLapsException(e.getMessage());
        }

    }

    @Override
    public Map<Object, String> addTimeLaps(HttpServletRequest req) {

        Map<Object,String> resultCheckMap = new HashMap<>();

        String userId = (String) req.getSession().getAttribute("userId");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String category = req.getParameter("category");
        String shortDescription = req.getParameter("shortDescription");
        String longDescription = req.getParameter("longDescription");

        try{
            resultCheckMap.put("userIdCheckResult", timeLapsServices.userIdCheck(userId));
            resultCheckMap.put("categoryCheckResult",timeLapsServices.categoryCheck(category));
            resultCheckMap.put("nameCheckResult",timeLapsServices.nameCheck(name));
            resultCheckMap.put("dateCheckResult",timeLapsServices.dateCheck(date));

            TimeLapsDAO timeLapsDAO = new TimeLapsDAOImpl();
            TimeLaps timeLaps = new TimeLaps();

            if(timeLapsServices.userIdCheck(userId).equalsIgnoreCase("ok")){
                timeLaps.setUserId(Long.parseLong(userId));
            }

            if(timeLapsServices.dateCheck(date).equalsIgnoreCase("ok")){
                timeLaps.setCompleteTime(timeLapsServices.dateConvert(date));
            }

            if(timeLapsServices.categoryCheck(category).equalsIgnoreCase("ok")){
                timeLaps.setCategory(category);
            }

            if(timeLapsServices.nameCheck(name).equalsIgnoreCase("ok")){
                timeLaps.setTimeLapsName(name);
            }

            timeLaps.setShortDescription(shortDescription);
            timeLaps.setLongDescription(longDescription);


            for(Map.Entry entry:resultCheckMap.entrySet()){
                String value = (String) entry.getValue();
                if(!value.equalsIgnoreCase("ok")) throw new DBException("Error");
            }

            timeLapsDAO.create(timeLaps);

        } catch (DBException e) {
            return resultCheckMap;
        }


        return resultCheckMap;
    }

    @Override
    public void editTimeLaps(TimeLaps timeLaps) {

    }

    @Override
    public void deleteTimeLapsList(List<String> timeLapsIdList) {

    }

    @Override
    public void deleteTimeLaps(TimeLaps timeLaps) {

    }

}
