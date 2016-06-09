package lv.javaguru.java2.challenge.database;


import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.hibernate.BaseDAO;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ChallengeDAOImpl extends BaseDAO implements ChallengeDAO {

    @Override
    public void create(Challenge challenge) throws DBException {
        super.saveOrUpdate(challenge);
    }

    @Override
    public void delete(Challenge challenge) throws DBException {
        super.delete(challenge);
    }

    @Override
    public Challenge getById(Long id) throws DBException {
        return super.get(Challenge.class,id);
    }

    @Override
    public void update(Challenge challenge) throws DBException {
        super.saveOrUpdate(challenge);
    }

    @Override
    public List<Challenge> getAllChallenge() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Challenge.class).list();
    }

    @Override
    public List<Challenge> getAllChallengeFromUserId(Long fromUserId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Challenge.class).add(Restrictions.eq("fromUserId",fromUserId)).list();
    }

    @Override
    public List<Challenge> getAllChallengeToUserId(Long toUserId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Challenge.class).add(Restrictions.eq("toUserId",toUserId)).list();
    }

    @Override
    public Long getTotalChallengeCount(Long userId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        try {
            return (Long)session.createCriteria(Challenge.class)
                    .add(Restrictions.eq("toUserId", userId))
                    .setProjection(Projections.rowCount()).uniqueResult();
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
    }
}
