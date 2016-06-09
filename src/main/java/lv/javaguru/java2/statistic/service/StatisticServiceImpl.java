package lv.javaguru.java2.statistic.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.statistic.exception.StatisticException;
import lv.javaguru.java2.statistic.form.StatisticFormModel;
import lv.javaguru.java2.timelaps.database.TimeLapsDAO;
import lv.javaguru.java2.todo.database.ToDoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private ToDoDAO toDoDAO;

    @Autowired
    @Qualifier("ORM_TimeLapsDAO")
    private TimeLapsDAO timeLapsDAO;

    @Override
    public StatisticFormModel getStatisticForUser(Long userId) throws StatisticException {
        try {
            return new StatisticFormModel()
                    .setToDoCount(toDoDAO.getTotalToDoCount(userId))
                    .setTimeLapsCount(timeLapsDAO.getTimeLapsCount(userId));
        } catch (DBException e) {
            throw new StatisticException("ToDo Error");
        }
    }
}
