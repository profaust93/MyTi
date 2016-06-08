package lv.javaguru.java2.challenge.form;


public class SubChallengeForm {

    private String name;

    private String note;

    private Boolean isDone;

    public String getName() {
        return name;
    }

    public SubChallengeForm setName(String name) {
        this.name = name;
        return this;
    }

    public String getNote() {
        return note;
    }

    public SubChallengeForm setNote(String note) {
        this.note = note;
        return this;
    }

    public Boolean getDone() {
        return isDone;
    }

    public SubChallengeForm setDone(Boolean done) {
        isDone = done;
        return this;
    }
}
