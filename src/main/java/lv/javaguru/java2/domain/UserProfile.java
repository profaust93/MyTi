package lv.javaguru.java2.domain;

/**
 * Created by Camille on 02.04.2016.
 */
public class UserProfile {

    private Long profileId;
    private Long UserId;
    private String firstName;
    private String lastName;
    private String email;
    //private byte image;

    public UserProfile() {}

    //Not needed, but decided to leave it here. Mb will use of it someday.
    public UserProfile(String firstName,String lastName, String email ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

}
