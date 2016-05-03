package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_TimeLapsDAO")
@Transactional
public class TimeLapsDAOImpl extends BaseDAO implements TimeLapsDAO {

    @Override
    public void create(TimeLaps timeLaps) throws DBException {
        super.saveOrUpdate(timeLaps);
    }

    @Override
    public void update(TimeLaps timeLaps) throws DBException {
        super.saveOrUpdate(timeLaps);
    }

    @Override
    public void delete(TimeLaps timeLaps) throws DBException {
        super.delete(timeLaps);
    }

    @Override
    public TimeLaps getById(Long id) throws DBException {
        return super.get(TimeLaps.class,id);
    }

    @Override
    public List<TimeLaps> getAllTimeLaps() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(TimeLaps.class).list();
    }

    @Override
    public List<TimeLaps> getAllTimeLapsByUserId(Long userId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(TimeLaps.class).add(Restrictions.eq("userId",userId)).list();
    }

    @Override
    public void deleteAllTimeLaps(Long userId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        TimeLaps timeLaps;
        List<TimeLaps> list = session.createCriteria(TimeLaps.class).add(Restrictions.eq("userId",userId)).list();
        for (int i = 0; i < list.size() ; i++) {
            timeLaps = list.get(i);
            session.delete(timeLaps);
        }
    }
}
