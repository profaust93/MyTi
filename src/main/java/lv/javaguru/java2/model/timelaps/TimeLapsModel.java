package lv.javaguru.java2.model.timelaps;

import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.model.exceptions.TimeLapsException;

import java.util.List;
import java.util.Map;

/**
 * Created by Ruslan on 2016.04.04..
 */
public interface TimeLapsModel {

    void setTimeLapsDAO(TimeLapsDAO timeLapsDAO);

    TimeLaps getTimeLapsById(Long timeLapsId) throws TimeLapsException;

    List<TimeLapsList> getAllTimeLapsForUser(String userId) throws TimeLapsException;

    Map<Object,String> addTimeLaps(TimeLaps timeLaps);

    Map<Object,String> editTimeLaps(TimeLaps timeLaps);

    void deleteTimeLapsList(List<String> timeLapsIdList);

    void deleteTimeLaps(TimeLaps timeLaps);

}
