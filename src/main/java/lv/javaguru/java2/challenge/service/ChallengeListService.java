package lv.javaguru.java2.challenge.service;

import lv.javaguru.java2.challenge.exception.ChallengeException;
import lv.javaguru.java2.challenge.form.ChallengeListModel;

import java.util.List;

public interface ChallengeListService {

    List<ChallengeListModel> getListOfChallengesForUser(Integer page, Long userId) throws ChallengeException;

    Integer getNumberOfPage(Long userId) throws ChallengeException;
}
