package lv.javaguru.java2.domain;



import javax.persistence.*;

@Entity
@Table(name = "Profiles")
public class UserProfile {

    @Id
    @Column(name = "ProfileId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long profileId;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Email")
    private String email;

    private String profilePicture;

    public String getProfilePicture() {
        return profilePicture;
    }

    public UserProfile setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public UserProfile() {}


    public UserProfile(String firstName,String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public Long getProfileId() {
        return profileId;
    }

    public UserProfile setProfileId(Long profileId) {
        this.profileId = profileId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserProfile setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfile setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfile setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfile setEmail(String email) {
        this.email = email;
        return this;
    }
    
}
