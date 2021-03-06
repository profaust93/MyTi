package lv.javaguru.java2.service.timelaps;

import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.TimeLapsList;
import lv.javaguru.java2.model.exceptions.TimeLapsException;

import java.util.List;
import java.util.Map;

public interface TimeLapsService {

    void setTimeLapsDAO(TimeLapsDAO timeLapsDAO);

    TimeLaps getTimeLapsById(Long timeLapsId) throws TimeLapsException;

    List<TimeLapsList> getAllTimeLapsForUser(String userId) throws TimeLapsException;

    Map<String,Object> addTimeLaps(TimeLaps timeLaps);

    Map<String,Object> editTimeLaps(TimeLaps timeLaps);

    void deleteAllTimeLaps(Long userId);

    void deleteTimeLaps(TimeLaps timeLaps);

}
