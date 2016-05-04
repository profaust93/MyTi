package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserProfileDAO;
import lv.javaguru.java2.domain.UserProfile;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_UserProfileDAO")
@Transactional
public class UserProfileDAOImpl extends BaseDAO implements UserProfileDAO {
    @Override
    public void create(UserProfile userProfile) throws DBException {
        super.saveOrUpdate(userProfile);
    }

    @Override
    public UserProfile getById(Long profileId) throws DBException {
        return super.get(UserProfile.class,profileId);
    }

    @Override
    public void delete(Long profileId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        super.delete(session.createCriteria(UserProfile.class).add(Restrictions.eq("profileId",profileId)));
    }

    @Override
    public void update(UserProfile userProfile) throws DBException {
        super.saveOrUpdate(userProfile);
    }

    @Override
    public List<UserProfile> getAllUserProfile() throws DBException {
        return super.getAll(UserProfile.class);
    }
}
