package lv.javaguru.java2.model.timelaps;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.model.exceptions.TimeLapsException;

import java.util.List;

/**
 * Created by Ruslan on 2016.04.04..
 */
public class TimeLapsModelImpl implements TimeLapsModel {

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
    public List<TimeLaps> getAllTimeLapsForUser(String userId) throws TimeLapsException {
        List<TimeLaps> allTimeLaps = timeLapsDAO.getAllTimeLaps()
        return
    }

    @Override
    public void addTimeLaps(TimeLaps timeLaps) {

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
