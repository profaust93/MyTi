package lv.javaguru.java2.domain;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {
/*
    CREATE TABLE IF NOT EXISTS `my_ti`.`Users` (
            `UserId` INT(11) NOT NULL AUTO_INCREMENT,
    `Login` CHAR(32) NOT NULL,
    `Password` CHAR(32) NOT NULL,
    `FirstName` CHAR(32) NOT NULL,
    `LastName` CHAR(32) NOT NULL,
    `Email` CHAR(32) NOT NULL,
    PRIMARY KEY (`UserId`)
    )*/


    @Id
    @Column(name = "UserId", columnDefinition = "INT(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "Login", columnDefinition = "CHAR(32)")
    private String login;

    @Column(name = "FirstName", columnDefinition = "CHAR(32)")
    private String firstName;

    @Column(name = "LastName", columnDefinition = "CHAR(32)")
    private String lastName;

    @Column(name = "Password", columnDefinition = "CHAR(32)")
    private String password;

    @Column(name = "Email", columnDefinition = "CHAR(32)")
    private String email;

    public User(){

    }

    public User(String login, String password,
                String firstName, String lastName, String email) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
