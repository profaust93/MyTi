package lv.javaguru.java2.timelaps.service;


import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.timelaps.database.TimeLapsDAO;
import lv.javaguru.java2.timelaps.domain.TimeLaps;
import lv.javaguru.java2.timelaps.domain.TimeLapsList;
import lv.javaguru.java2.timelaps.exception.TimeLapsException;
import lv.javaguru.java2.validators.ValidatorException;
import lv.javaguru.java2.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TimeLapsServiceImpl implements TimeLapsService {
    @Autowired
    @Qualifier("ORM_TimeLapsDAO")
    TimeLapsDAO timeLapsDAO;

    @Autowired
    Validators validators;

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
                    new TimeLapsList(timelaps))
                    .collect(Collectors.toList());
        } catch (DBException e) {
            throw new TimeLapsException(e.getMessage());
        }

    }

    @Override
    public Map<String,Object> addTimeLaps(TimeLaps timeLaps) {

        Map<String,Object> map = new HashMap<>();

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
