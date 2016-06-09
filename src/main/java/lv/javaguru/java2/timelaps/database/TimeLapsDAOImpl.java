package lv.javaguru.java2.timelaps.database;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.hibernate.BaseDAO;
import lv.javaguru.java2.timelaps.domain.TimeLaps;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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


    @Override
    public Long getTotalTimeLapsCount(Long userId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        try {
            return (Long)session.createCriteria(TimeLaps.class)
                    .add(Restrictions.eq("userId", userId))
                    .setProjection(Projections.rowCount()).uniqueResult();
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
    }

    @Override
    public Boolean checkIfBelongToUser(Long timeLapsId, Long userId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        try {
            Long count =  (Long)session.createCriteria(TimeLaps.class)
                    .add(Restrictions.eq("userId", userId))
                    .add(Restrictions.eq("id", timeLapsId))
                    .setProjection(Projections.rowCount()).uniqueResult();
            return count > 0;
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
    }
}
