package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.TimeLaps;

import java.util.List;

/**
 * Created by ruslan on 16.20.3.
 */
public interface TimeLapsDAO {

    void create(TimeLaps timeLaps) throws DBException;

    void update(TimeLaps timeLaps) throws DBException;

    void delete(TimeLaps timeLaps) throws DBException;

    TimeLaps getById(Long id) throws DBException;

    List<TimeLaps> getAllTimeLaps() throws DBException;

    List<TimeLaps> getAllTimeLapsByUserId(Long userId) throws DBException;


}
