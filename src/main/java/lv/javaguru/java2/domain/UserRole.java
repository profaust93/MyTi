package lv.javaguru.java2.domain;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "User_roles",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "Role", "UserId" }))
public class UserRole{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "User_role_id",
            unique = true, nullable = false)
    private Integer userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @Column(name = "Role", nullable = false, length = 45)
    private String role;

    public UserRole() {
    }

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }


    public Integer getUserRoleId() {
        return userRoleId;
    }

    public UserRole setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserRole setUser(User user) {
        this.user = user;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserRole setRole(String role) {
        this.role = role;
        return this;
    }
}