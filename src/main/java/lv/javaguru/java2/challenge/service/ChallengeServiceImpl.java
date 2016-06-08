package lv.javaguru.java2.challenge.service;

import lv.javaguru.java2.challenge.database.ChallengeDAO;
import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.challenge.exception.ChallengeError;
import lv.javaguru.java2.challenge.exception.ChallengeException;
import lv.javaguru.java2.challenge.form.ChallengeFormModel;
import lv.javaguru.java2.challenge.util.ChallengeModelConverter;
import lv.javaguru.java2.challenge.validator.ChallengeValidator;
import lv.javaguru.java2.database.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeValidator validator;

    @Autowired
    ChallengeModelConverter challengeModelConverter;

    @Autowired
    ChallengeDAO challengeDAO;

    @Override
    public void upsertChallenge(ChallengeFormModel challengeFormModel,Long senderId, Long receiverId) throws ChallengeException {
        validator.validateChallenge(challengeFormModel, senderId, receiverId);
        try{
            Challenge challenge = challengeModelConverter.convertFormModelToDomain(challengeFormModel);
            challenge.setSenderId(senderId);
            challenge.setReceiverId(receiverId);
            challengeDAO.saveOrUpdate(challenge);
        } catch (DBException e) {
            throw new ChallengeException(ChallengeError.DB_ERROR);
        }
    }

    @Override
    public ChallengeFormModel getChallengeById(Long challengeId) throws ChallengeException {
        try{
            Challenge challenge = Optional.ofNullable(challengeDAO.getChallengeById(challengeId))
                    .orElseThrow( () -> new ChallengeException(ChallengeError.NO_CHALLENGE_FOUND));
            return challengeModelConverter.convertDomainToFormModel(challenge);
        } catch (DBException e) {
            throw new ChallengeException();
        }
    }
}
