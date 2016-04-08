package lv.javaguru.java2.model.timelaps;


import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.service.TimeLapsChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Ruslan on 2016.04.04..
 */
@Component
public class TimeLapsModelImpl implements TimeLapsModel {
    @Autowired
    TimeLapsDAO timeLapsDAO;

    private TimeLapsChecks timeLapsChecks = new TimeLapsChecks();
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
                    new TimeLapsList(timelaps.getTimeLapsId(),timelaps.getTimeLapsName(),timelaps.getCompleteTime(),
                            timelaps.getShortDescription(),timelaps.getLongDescription(),timelaps.getCategory()))
                    .collect(Collectors.toList());
        } catch (DBException e) {
            throw new TimeLapsException(e.getMessage());
        }

    }

    @Override
    public Map<Object, String> addTimeLaps(TimeLaps timeLaps) {

        Map<Object,String> resultCheckMap = new HashMap<>();

        try{
            resultCheckMap.put("userIdCheckResult", timeLapsChecks.userIdCheck(String.valueOf(timeLaps.getUserId())));
            resultCheckMap.put("categoryCheckResult", timeLapsChecks.categoryCheck(timeLaps.getCategory()));
            resultCheckMap.put("nameCheckResult", timeLapsChecks.nameCheck(timeLaps.getTimeLapsName()));
            resultCheckMap.put("dateCheckResult", timeLapsChecks.dateCheck(String.valueOf(timeLaps.getCompleteTime())));
            resultCheckMap.put("sDescCheck", timeLapsChecks.descriptionCheck(timeLaps.getShortDescription(),100));
            resultCheckMap.put("lDescCheck", timeLapsChecks.descriptionCheck(timeLaps.getLongDescription(),1000));



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
    public Map<Object,String> editTimeLaps(TimeLaps timeLaps) {
        Map<Object,String> resultCheckMap = new HashMap<>();
        try {

            resultCheckMap.put("userIdCheckResult", timeLapsChecks.userIdCheck(String.valueOf(timeLaps.getUserId())));
            resultCheckMap.put("categoryCheckResult", timeLapsChecks.categoryCheck(timeLaps.getCategory()));
            resultCheckMap.put("nameCheckResult", timeLapsChecks.nameCheck(timeLaps.getTimeLapsName()));
            resultCheckMap.put("dateCheckResult", timeLapsChecks.dateCheck(String.valueOf(timeLaps.getCompleteTime())));
            resultCheckMap.put("sDescCheck", timeLapsChecks.descriptionCheck(timeLaps.getShortDescription(), 100));
            resultCheckMap.put("lDescCheck", timeLapsChecks.descriptionCheck(timeLaps.getLongDescription(), 1000));

            for (Map.Entry entry : resultCheckMap.entrySet()) {
                String value = (String) entry.getValue();
                if (!value.equalsIgnoreCase("ok")) throw new DBException("Error");
            }
            timeLapsDAO.update(timeLaps);
        }catch (DBException e){
            return resultCheckMap;
        }
        return resultCheckMap;
    }

    @Override
    public void deleteTimeLapsList(List<String> timeLapsList) {

    }

    @Override
    public void deleteTimeLaps(TimeLaps timeLaps) {
        try {
            timeLapsDAO.delete(timeLaps);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

}
