package lv.javaguru.java2.challenge.database;


import lv.javaguru.java2.challenge.domain.ChallengeMessage;
import lv.javaguru.java2.database.hibernate.BaseDAO;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component("ORM_ChallengeMessageDAO")
@Transactional
public class ChallengeMessageDAOImpl extends BaseDAO implements ChallengeMessageDAO {
    @Override
    public void create(ChallengeMessage challengeMessage) {
        super.saveOrUpdate(challengeMessage);
    }

    @Override
    public ChallengeMessage getById(Long id) {
        return super.get(ChallengeMessage.class,id);
    }

    @Override
    public void delete(ChallengeMessage challengeMessage) {
        super.delete(challengeMessage);
    }

    @Override
    public List<ChallengeMessage> getAllByRecipientId(Long recipientId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(ChallengeMessage.class).add(Restrictions.eq("recipientId",recipientId)).list();
    }
}
