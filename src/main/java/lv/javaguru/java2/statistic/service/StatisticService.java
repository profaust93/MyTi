package lv.javaguru.java2.statistic.service;

import lv.javaguru.java2.statistic.exception.StatisticException;
import lv.javaguru.java2.statistic.form.StatisticFormModel;

public interface StatisticService {
    StatisticFormModel getStatisticForUser(Long userId) throws StatisticException;
}
