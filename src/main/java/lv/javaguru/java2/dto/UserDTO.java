package lv.javaguru.java2.dto;

/**
 * Created by germans.kuzmins on 2016.04.08..
 */
public class UserDTO {
    private String userName;
    private String userLogin;
    private Long userId;

    public UserDTO(String userName, String userLogin, Long userId) {
        this.userName = userName;
        this.userLogin = userLogin;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
