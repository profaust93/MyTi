package lv.javaguru.java2.service.timelaps;


import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import lv.javaguru.java2.service.validators.ValidatorException;
import lv.javaguru.java2.service.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Ruslan on 2016.04.04..
 */
@Component
public class TimeLapsServiceImpl implements TimeLapsService {
    @Autowired
    @Qualifier("ORM_TimeLapsDAO")
    TimeLapsDAO timeLapsDAO;

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
    public Map<String,Object> addTimeLaps(TimeLaps timeLaps) {

        Map<String,Object> map = new HashMap<>();
        Validators validators = new Validators();
        try{
            validators.timeLapsValidator(timeLaps);
            timeLapsDAO.create(timeLaps);
        } catch (ValidatorException e) {
            return e.getMap();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String,Object> editTimeLaps(TimeLaps timeLaps) {
        Map<String,Object> resultCheckMap = new HashMap<>();
        Validators validators = new Validators();
        try {
            validators.timeLapsValidator(timeLaps);
            timeLapsDAO.update(timeLaps);
        }catch (DBException e){
            return resultCheckMap;
        } catch (ValidatorException e) {
            return e.getMap();
        }
        return resultCheckMap;
    }

    @Override
    public void deleteAllTimeLaps(Long userId) {

        try {
            timeLapsDAO.deleteAllTimeLaps(userId);
        } catch (DBException e) {
            e.printStackTrace();
        }
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
