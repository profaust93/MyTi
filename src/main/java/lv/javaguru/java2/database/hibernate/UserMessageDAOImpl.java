package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.UserMessage;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ruslan on 2016.04.24..
 */
@Component("ORM_UserMessageDAO")
@Transactional
public class UserMessageDAOImpl extends BaseDAO implements UserMessageDAO {
    @Override
    public void create(UserMessage userMessage) {
        super.saveOrUpdate(userMessage);
    }

    @Override
    public UserMessage getById(Long id) {
        return super.get(UserMessage.class,id);
    }

    @Override
    public void delete(UserMessage userMessage) {
        super.delete(userMessage);
    }

    @Override
    public List<UserMessage> getAllByRecipientId(Long recipientId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(UserMessage.class).add(Restrictions.eq("recipientId",recipientId)).list();
    }
}
