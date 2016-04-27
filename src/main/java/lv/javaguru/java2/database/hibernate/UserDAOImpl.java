package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kemran on 27/04/2016.
 */


@Component("ORM_UserDAO")
@Transactional
public class UserDAOImpl extends BaseDAO implements UserDAO {


    @Override
    public void create(User user) throws DBException {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public User getById(Long id) throws DBException {
        return super.get(User.class, id);
    }

    @Override
    public void delete(Long id) throws DBException {
        User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
        super.delete(user);
    }

    @Override
    public void update(User user) throws DBException {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public List<User> getAll() throws DBException {
        return super.getAll(User.class);
    }

    @Override
    public User getUserByEmailOrLogin(String userCred) throws DBException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("userCred", userCred)).uniqueResult();
    }
}
