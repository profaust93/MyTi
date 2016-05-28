package lv.javaguru.java2.timelaps.service;

import lv.javaguru.java2.timelaps.database.TimeLapsDAO;
import lv.javaguru.java2.timelaps.domain.TimeLaps;
import lv.javaguru.java2.timelaps.domain.TimeLapsList;
import lv.javaguru.java2.timelaps.exception.TimeLapsException;

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
