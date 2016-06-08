package lv.javaguru.java2.domain;

import lv.javaguru.java2.profile.domain.UserProfile;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "UserId", columnDefinition = "INT(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "Username", unique = true,
            nullable = false, length = 45)
    private String username;

    @Column(name = "Password",
            nullable = false, length = 60)
    private String password;

    @Column(name = "Enabled", nullable = false)
    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    /*@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private UserProfile userProfile;*/

    public User() {
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String username, String password,
                boolean enabled, Set<UserRole> userRole) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }
    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public User setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }
    public User setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
        return this;
    }

    public Long getUserId() {
        return userId;
    }
    public User setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }
    public User setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

/*    public UserProfile getUserProfile() {
        return userProfile;
    }
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }*/

}
