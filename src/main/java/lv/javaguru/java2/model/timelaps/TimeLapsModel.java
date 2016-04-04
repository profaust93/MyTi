package lv.javaguru.java2.model.timelaps;

import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.model.exceptions.TimeLapsException;

import java.util.List;

/**
 * Created by Ruslan on 2016.04.04..
 */
public interface TimeLapsModel {

    void setTimeLapsDAO(TimeLapsDAO timeLapsDAO);

    TimeLaps getTimeLapsById(Long timeLapsId) throws TimeLapsException;

    List<TimeLaps> getAllTimeLapsForUser(String userId) throws TimeLapsException;

    void addTimeLaps(TimeLaps timeLaps);

    void editTimeLaps(TimeLaps timeLaps);

    void deleteTimeLapsList(List<String> timeLapsIdList);

    void deleteTimeLaps(TimeLaps timeLaps);

}
