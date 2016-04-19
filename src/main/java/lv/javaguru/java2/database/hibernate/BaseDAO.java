package lv.javaguru.java2.database.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by germans.kuzmins on 2016.04.19..
 */

@Component
public class BaseDAO {

    @Autowired
    SessionFactory sessionFactory;

    public void delete(final Object object){
        sessionFactory.getCurrentSession().delete(object);
    }

    public <T> T get(final Class<T> type, final Long id){
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    public <T> void saveOrUpdate(final T o){
        sessionFactory.getCurrentSession().saveOrUpdate(o);
    }

    public <T> List<T> getAll(final Class<T> type) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(type).list();
    }
}
