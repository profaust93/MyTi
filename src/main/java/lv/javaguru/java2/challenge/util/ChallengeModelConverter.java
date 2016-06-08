package lv.javaguru.java2.challenge.util;

import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.challenge.form.ChallengeFormModel;
import lv.javaguru.java2.challenge.form.ChallengeListModel;

/**
 * Created by Ruslan on 2016.06.08..
 */
public interface ChallengeModelConverter {

    ChallengeListModel convertDomainToListModel(Challenge challenge);

    Challenge convertFormModelToDomain(ChallengeFormModel challengeFormModel);

    ChallengeFormModel convertDomainToFormModel(Challenge challenge);
}
