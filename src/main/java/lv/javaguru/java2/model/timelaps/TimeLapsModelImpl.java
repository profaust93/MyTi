package lv.javaguru.java2.model.timelaps;


import com.mysql.jdbc.StringUtils;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.service.TimeLapsServices;

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
                    new TimeLapsList(timelaps.getTimeLapsId(),timelaps.getTimeLapsName(),timelaps.getCompleteTime(),
                            timelaps.getShortDescription(),timelaps.getLongDescription()))
                    .collect(Collectors.toList());
        } catch (DBException e) {
            throw new TimeLapsException(e.getMessage());
        }

    }

    @Override
    public Map<Object, String> addTimeLaps(TimeLaps timeLaps) {

        Map<Object,String> resultCheckMap = new HashMap<>();



        try{
            resultCheckMap.put("userIdCheckResult", timeLapsServices.userIdCheck(String.valueOf(timeLaps.getUserId())));
            resultCheckMap.put("categoryCheckResult",timeLapsServices.categoryCheck(timeLaps.getCategory()));
            resultCheckMap.put("nameCheckResult",timeLapsServices.nameCheck(timeLaps.getTimeLapsName()));
            resultCheckMap.put("dateCheckResult",timeLapsServices.dateCheck(String.valueOf(timeLaps.getCompleteTime())));
            resultCheckMap.put("sDescCheck",timeLapsServices.descriptionCheck(timeLaps.getShortDescription(),100));
            resultCheckMap.put("lDescCheck",timeLapsServices.descriptionCheck(timeLaps.getLongDescription(),1000));

            TimeLapsDAO timeLapsDAO = new TimeLapsDAOImpl();

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
