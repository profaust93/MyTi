package lv.javaguru.java2.challenge.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "SubChallengeTask")
public class SubChallengeTask {



    @Column(name = "SubChallengeName")
    private String subChallengeTaskName;

    @Column(name = "SubChallengeNote")
    private String subChallengeTaskNote;

    @Column(name = "IsComplete")
    private Boolean isComplete;


    public SubChallengeTask() {
    }

    public SubChallengeTask(String subChallengeTaskName) {
        this.subChallengeTaskName = subChallengeTaskName;
    }

    public String getSubChallengeTaskName() {
        return subChallengeTaskName;
    }

    public SubChallengeTask setSubChallengeTaskName(String subChallengeTaskName) {
        this.subChallengeTaskName = subChallengeTaskName;
        return this;
    }

    public String getSubChallengeTaskNote() {
        return subChallengeTaskNote;
    }

    public SubChallengeTask setSubChallengeTaskNote(String subChallengeTaskNote) {
        this.subChallengeTaskNote = subChallengeTaskNote;
        return this;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public SubChallengeTask setComplete(Boolean complete) {
        isComplete = complete;
        return this;
    }
}
