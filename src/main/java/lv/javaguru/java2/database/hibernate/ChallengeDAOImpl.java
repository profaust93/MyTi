package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Challenge;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ruslan on 2016.04.20..
 */
@Component("ORM_ChallengeDAO")
@Transactional
public class ChallengeDAOImpl extends BaseDAO implements ChallengeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Challenge challenge) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(challenge);
    }

    @Override
    public void delete(Challenge challenge) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.delete(challenge);
    }

    @Override
    public Challenge getById(Long id) throws DBException {
        return get(Challenge.class,id);
    }

    @Override
    public void update(Challenge challenge) throws DBException {

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
}
