package lv.javaguru.java2.challenge.util;

import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.challenge.domain.SubChallenge;
import lv.javaguru.java2.challenge.form.ChallengeFormModel;
import lv.javaguru.java2.challenge.form.ChallengeListModel;
import lv.javaguru.java2.challenge.form.SubChallengeForm;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChallengeModelConverterImpl implements ChallengeModelConverter {

    @Override
    public ChallengeListModel convertDomainToListModel(Challenge challenge) {
        ChallengeListModel challengeListModel = new ChallengeListModel();
        List<SubChallenge> challengeTasks = challenge.getSubChallenges();
        challengeListModel.setComplete(challenge.getComplete())
                .setName(challenge.getChallengeName())
                .setDeadLineTime(challenge.getDeadLineTime())
                .setId(challenge.getChallengeId())
                .setChallengesCount(challengeTasks != null ? challengeTasks.size() : 0);

        return challengeListModel;
    }

    @Override
    public Challenge convertFormModelToDomain(ChallengeFormModel challengeFormModel) {
        return new Challenge()
                .setChallengeName(challengeFormModel.getChallengeName())
                .setDeadLineTime(challengeFormModel.getDeadLineTime())
                .setChallengeId(challengeFormModel.getChallengeId())
                .setReceiverId(challengeFormModel.getReceiverId())
                .setSenderId(challengeFormModel.getSenderId())
                .setChallengeNote(challengeFormModel.getChallengeNote())
                .setSubChallenges(convertFormChallengeToDomainChallenge(challengeFormModel))
                .setComplete(calculateDone(challengeFormModel));
    }

    @Override
    public ChallengeFormModel convertDomainToFormModel(Challenge challenge) {
        return new ChallengeFormModel()
                .setChallengeNote(challenge.getChallengeNote())
                .setChallengeName(challenge.getChallengeName())
                .setDeadLineTime(challenge.getDeadLineTime())
                .setSenderId(challenge.getSenderId())
                .setReceiverId(challenge.getReceiverId())
                .setSubChallengeForms(convertToFormSubChallenges(challenge.getSubChallenges()));
    }


    private List<SubChallenge> convertFormChallengeToDomainChallenge(ChallengeFormModel challengeFormModel){
        if(challengeFormModel.getSubChallengeForms() == null){
            return Collections.singletonList(new SubChallenge()
            .setSubChallengeName(challengeFormModel.getChallengeName())
            .setSubChallengeNote(challengeFormModel.getChallengeNote()));
        }
        return challengeFormModel.getSubChallengeForms().stream()
                .map(e -> new SubChallenge()
                .setSubChallengeName(e.getName())
                .setComplete(e.getDone())
                .setSubChallengeNote(e.getNote()))
                .collect(Collectors.toList());
    }

    private Boolean calculateDone(ChallengeFormModel challengeFormModel){
        if (challengeFormModel.getSubChallengeForms() == null) return false;
        return !challengeFormModel.getSubChallengeForms()
                .stream()
                .filter(e -> !e.getDone())
                .findAny().isPresent();
    }

    private List<SubChallengeForm> convertToFormSubChallenges(List<SubChallenge> subChallenges){
        return subChallenges.stream()
                .map(e -> new SubChallengeForm()
                        .setDone(e.getComplete())
                        .setName(e.getSubChallengeName()).setNote(e.getSubChallengeNote())).collect(Collectors.toList());
    }
}
