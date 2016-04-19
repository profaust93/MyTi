package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ruslan on 16.19.4.
 */
@Component("ORM_TimeLapsDAO")
@Transactional
public class TimeLapsDAOImpl implements TimeLapsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(TimeLaps timeLaps) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(timeLaps);

    }

    @Override
    public void update(TimeLaps timeLaps) throws DBException {

    }

    @Override
    public void delete(TimeLaps timeLaps) throws DBException {

    }

    @Override
    public TimeLaps getById(Long id) throws DBException {
        return null;
    }

    @Override
    public List<TimeLaps> getAllTimeLaps() throws DBException {
        return null;
    }

    @Override
    public List<TimeLaps> getAllTimeLapsByUserId(Long userId) throws DBException {
        return null;
    }

    @Override
    public void deleteAllTimeLaps(Long userId) throws DBException {

    }
}
