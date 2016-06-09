package lv.javaguru.java2.statistic.service;

import lv.javaguru.java2.challenge.database.ChallengeDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.statistic.exception.StatisticException;
import lv.javaguru.java2.statistic.form.StatisticFormModel;
import lv.javaguru.java2.timelaps.database.TimeLapsDAO;
import lv.javaguru.java2.todo.database.ToDoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    ToDoDAO toDoDAO;

    @Autowired
    TimeLapsDAO timeLapsDAO;

    @Autowired
    ChallengeDAO challengeDAO;

    @Override
    public StatisticFormModel getStatisticForUser(Long userId) throws StatisticException {
        try {
            StatisticFormModel statisticFormModel = new StatisticFormModel()
                    .setToDoCount(toDoDAO.getTotalToDoCount(userId))
                    .setTimeLapsCount(timeLapsDAO.getTotalTimeLapsCount(userId))
                    .setChallengeCount(challengeDAO.getTotalChallengeCount(userId));
            return statisticFormModel;
        } catch (DBException e) {
            throw new StatisticException("Statistic Error");
        }
    }
}
