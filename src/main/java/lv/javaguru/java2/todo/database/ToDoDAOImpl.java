package lv.javaguru.java2.todo.database;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.hibernate.BaseDAO;
import lv.javaguru.java2.todo.domain.ToDo;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ToDoDAOImpl extends BaseDAO implements ToDoDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<ToDo> getToDoForUser(Long userId, Integer limit, Integer offset) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.createCriteria(ToDo.class)
                    .add(Restrictions.eq("userId", userId))
                    .setFirstResult(offset - 1)
                    .setMaxResults(limit)
                    .list();
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
    }

    @Override
    public Long getTotalToDoCount(Long userId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        try {
            return (Long)session.createCriteria(ToDo.class)
                    .add(Restrictions.eq("userId", userId))
                    .setProjection(Projections.rowCount()).uniqueResult();
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ToDo> getAllToDoByUserId(Long userId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.createCriteria(ToDo.class).add(Restrictions.eq("userId", userId)).list();
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }

    }

    @Override
    public void createOrUpdate(ToDo toDo) throws DBException {
        try {
            super.saveOrUpdate(toDo);
        } catch (Exception e ){
            throw new DBException(e.getMessage(),e);
        }
    }

    @Override
    public void update(ToDo toDo) throws DBException {
    }

    @Override
    public void deleteToD(ToDo toDo) throws DBException {
        try {
            super.delete(toDo);
        } catch (Exception e ){
            throw new DBException(e.getMessage(),e);
        }
    }


    @Override
    public ToDo getToDoById(Long id) throws DBException {
        try {
           return super.get(ToDo.class,id);
        } catch (Exception e ){
            throw new DBException(e.getMessage(),e);
        }
    }
}
