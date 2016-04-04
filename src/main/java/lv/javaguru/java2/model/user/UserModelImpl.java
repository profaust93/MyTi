package lv.javaguru.java2.model.user;

import com.sun.deploy.net.HttpRequest;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.model.exceptions.LoginException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserModelImpl implements UserModel {


    private UserDAO userDAO;

    @Override
    public Map<String, String> logInUser(String userCred, String password, Boolean rememberMe) throws LoginException {

        User user;
        Map<String,String> userInfo = new HashMap<>();
        try {
            user = Optional.ofNullable(userDAO.getUserByEmailOrLogin(userCred)).orElseThrow(()->new LoginException("User Does Not Exist"));
        } catch (DBException e) {
            throw new LoginException(e.getMessage());
        }

        if(!password.equals(user.getPassword())) {
            throw new LoginException("Wrong Password");
        }

        userInfo.put("userId", Long.toString(user.getUserId()));
        userInfo.put("userLogin",user.getLogin());
        userInfo.put("userName",user.getFirstName());
        userInfo.put("userLastName",user.getLastName());
        return userInfo;
    }

    @Override
    public Boolean registerUser(HttpRequest request) {
        // Добавить  парметры с формы в объект User пример user.setName(request.getParameters("name"))
        // также проверять обязательный праметры типо логина пароля и т.д чтобы не были пустыми
        // если что-то плохо выкинуть эксепшн типо throw new RegistrationException("Login is empty")
        //  если все ок вернуть true на всякий пожарный
        // чтобы вывести пользователя вызови userDAO.create(user)
        // обзятельно лови эксепшены смотри метод выше
        return null;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
