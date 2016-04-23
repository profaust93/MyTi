package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.ToDoListDAO;
import lv.javaguru.java2.domain.ToDo;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToDoListDAOImpl extends BaseDAO implements ToDoListDAO{

    @Override
    public void createOrUpdate(ToDo toDo) {
        super.saveOrUpdate(toDo);
    }

    @Override
    public ToDo getById(Long id) {
        return super.get(ToDo.class,id);
    }

    @Override
    public void delete(ToDo toDo) {
        super.delete(toDo);
    }

    @Override
    public List<ToDo> getAllToDoList() {
        return super.getAll(ToDo.class);
    }

    @Override
    public List<ToDo> getAllToDoListByUserId(Long userId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ToDo.class);
        return criteria.add(Restrictions.eq("userId",userId)).list();
    }
}
