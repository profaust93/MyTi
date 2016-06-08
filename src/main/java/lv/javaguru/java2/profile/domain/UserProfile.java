package lv.javaguru.java2.profile.domain;

import lv.javaguru.java2.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "Profiles")
public class UserProfile {

    @Id
    @Column(name="ProfileId",columnDefinition = "INT(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long profileId;

    @Transient
    private Long UserId;

    @Column(name = "Firstname",nullable = false,
            length = 45)
    private String firstName;

    @Column(name = "Lastname",nullable = false,
            length = 45)
    private String lastName;

    @Column(name = "Email",nullable = false,
            length = 45)
    private String email;

    @Column(name = "ProfilePicture")
    private String profilePicture;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    public UserProfile() {}
    //Not needed, but decided to leave it here. Mb will use of it someday.
    public UserProfile(String firstName,String lastName, String email ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public UserProfile setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
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

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


}
