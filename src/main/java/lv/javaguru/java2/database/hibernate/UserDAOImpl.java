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

    @SuppressWarnings("JpaQlInspection")
    @Override
    public User findByUserName(String username) {
        List<User> users;

        users = sessionFactory.getCurrentSession()
                .createQuery("From User where Username=?")
                .setParameter(0, username)
                .list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
