package lv.javaguru.java2.challenge.database;

import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.hibernate.BaseDAO;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class ChallengeDAOImpl extends BaseDAO implements ChallengeDAO {


    @Override
    public void saveOrUpdate(Challenge challenge) throws DBException {
        if (challenge.getCreateTime() == null) {
            challenge.setCreateTime(LocalDateTime.now());
        }
        try {
            super.saveOrUpdate(challenge);
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Challenge challenge) throws DBException {
        try {
            super.delete(challenge);
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public Long getTotalChallengeCount(Long userId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        try{
            return (Long) session.createCriteria(Challenge.class)
                    .add(Restrictions.eq("userId",userId))
                    .setProjection(Projections.rowCount()).uniqueResult();
        } catch (Exception e){
            throw new DBException(e.getMessage(),e);
        }
    }

    @Override
    public Challenge getChallengeById(Long challengeId) throws DBException {
        try {
            return super.get(Challenge.class, challengeId);
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public List<Challenge> getAllChallengesByUserId(Long userId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.createCriteria(Challenge.class).add(Restrictions.eq("userId", userId)).list();
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public List<Challenge> getAllChallengesForUserView(Long userId, Integer limit, Integer offset) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.createCriteria(Challenge.class)
                    .add(Restrictions.eq("userId", userId))
                    .setFirstResult(offset - 1)
                    .setMaxResults(limit)
                    .list();
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean checkIfBelongToUser(Long challengeId, Long userId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        try{
            Long count = (Long) session.createCriteria(Challenge.class)
                    .add(Restrictions.eq("userdId",userId))
                    .add(Restrictions.eq("id",challengeId))
                    .setProjection(Projections.rowCount()).uniqueResult();
            return count > 0;
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }
}
