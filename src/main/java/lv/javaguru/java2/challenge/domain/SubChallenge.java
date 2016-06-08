package lv.javaguru.java2.challenge.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "SubChallenge")
public class SubChallenge {



    @Column(name = "SubChallengeName")
    private String subChallengeName;

    @Column(name = "SubChallengeNote")
    private String subChallengeNote;

    @Column(name = "IsComplete")
    private Boolean isComplete;


    public SubChallenge() {
    }

    public SubChallenge(String subChallengeName) {
        this.subChallengeName = subChallengeName;
    }

    public String getSubChallengeName() {
        return subChallengeName;
    }

    public SubChallenge setSubChallengeName(String subChallengeName) {
        this.subChallengeName = subChallengeName;
        return this;
    }

    public String getSubChallengeNote() {
        return subChallengeNote;
    }

    public SubChallenge setSubChallengeNote(String subChallengeNote) {
        this.subChallengeNote = subChallengeNote;
        return this;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public SubChallenge setComplete(Boolean complete) {
        isComplete = complete;
        return this;
    }
}
