package lv.javaguru.java2.model.timelaps;

import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.model.exceptions.TimeLapsException;

import java.util.List;

/**
 * Created by Ruslan on 2016.04.04..
 */
public class TimeLapsModelImpl implements TimeLapsModel {

    private String jspName;
    private Object data;

    public TimeLapsModelImpl(String jspName, Object data){
        this.jspName = jspName;
        this.data = data;
    }

    @Override
    public void setTimeLapsDAO(TimeLapsDAO timeLapsDAO) {

    }

    @Override
    public TimeLaps getTimeLapsById(Long timeLapsId) throws TimeLapsException {
        return null;
    }

    @Override
    public List<TimeLaps> getAllTimeLapsForUser(String userId) throws TimeLapsException {
        return null;
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
