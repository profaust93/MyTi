package lv.javaguru.java2.profile.domain;

public class UserProfileList {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePicture;

    public UserProfileList(Long userId, String firstName, String lastName, String email,String profilePicture) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public Long getUserId() {
        return userId;
    }

    public UserProfileList setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileList setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileList setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileList setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public UserProfileList setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }
}
