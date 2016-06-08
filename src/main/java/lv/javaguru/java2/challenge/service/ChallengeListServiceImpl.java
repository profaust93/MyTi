package lv.javaguru.java2.challenge.service;


import lv.javaguru.java2.challenge.database.ChallengeDAO;
import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.challenge.exception.ChallengeError;
import lv.javaguru.java2.challenge.exception.ChallengeException;
import lv.javaguru.java2.challenge.form.ChallengeListModel;
import lv.javaguru.java2.challenge.util.ChallengeModelConverter;
import lv.javaguru.java2.database.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChallengeListServiceImpl implements ChallengeListService {

    private final static Integer RECORDS_PER_PAGE = 20;

    @Autowired
    private ChallengeDAO challengeDAO;

    @Autowired
    private ChallengeModelConverter converter;



    @Override
    public List<ChallengeListModel> getListOfChallengesForUser(Integer page, Long userId) throws ChallengeException {
        Integer offset = (page - 1) * RECORDS_PER_PAGE;
        try{
            List<Challenge> challengeList = challengeDAO.getAllChallengesForUserView(userId,RECORDS_PER_PAGE,offset);
            return challengeList.stream()
                    .map(converter::convertDomainToListModel)
                    .collect(Collectors.toList());
        } catch (DBException e) {
            throw new ChallengeException(ChallengeError.CHALLENGE_ERROR);
        }
    }

    @Override
    public Integer getNumberOfPage(Long userId) throws ChallengeException {
        return null;
    }
}
