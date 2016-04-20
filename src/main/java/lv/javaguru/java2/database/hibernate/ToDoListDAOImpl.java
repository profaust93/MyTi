package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.ToDoListDAO;
import lv.javaguru.java2.domain.ToDoList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToDoListDAOImpl extends BaseDAO implements ToDoListDAO{

    @Override
    public void createOrUpdate(ToDoList toDoList) {
        super.saveOrUpdate(toDoList);
    }

    @Override
    public ToDoList getById(Long id) {
        return super.get(ToDoList.class,id);
    }

    @Override
    public void delete(ToDoList toDoList) {
        super.delete(toDoList);
    }

    @Override
    public List<ToDoList> getAllToDoList() {
        return super.getAll(ToDoList.class);
    }

    @Override
    public List<ToDoList> getAllToDoListByUserId(Long userId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ToDoList.class);
        return criteria.add(Restrictions.eq("userId",userId)).list();
    }
}
