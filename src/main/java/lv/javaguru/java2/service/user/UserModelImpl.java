package lv.javaguru.java2.service.user;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.model.exceptions.LoginException;
import lv.javaguru.java2.model.exceptions.RegisterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserModelImpl implements UserModel {

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Override
    public User logInUser(String userCred, String password, Boolean rememberMe) throws LoginException {

        User user;
        Map<String,String> userInfo = new HashMap<>();
        try {
            //вренте либо юзера либо выкинет exception что юзера с таким логино нету
            user = Optional.ofNullable(userDAO.getUserByEmailOrLogin(userCred)).orElseThrow(()->new LoginException("User Does Not Exist"));
        } catch (DBException e) {
            throw new LoginException(e.getMessage());
        }

        if(!password.equals(user.getPassword())) {
            throw new LoginException("Wrong Password");
        }
        return user;
    }

    @Override
    public Boolean registerUser(User user) throws RegisterException {


        checkFields(user);

        isUserExist(user); //заимплементь метод

      /*  if(!checkFields(user)){
            checkFields(user); // просто вызови метод, а мтеод путьс генерит эксепшены
        }else{
            return true;   // после return код не пишется, здесь просто оставь экспешн
        }*/
        // чтобы вывести пользователя вызови userDAO.create(user)

        return true;
    }

    private void checkFields(User user) throws RegisterException {
        if(user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new RegisterException("Login is empty");
        } else if(user.getPassword() == null || user.getPassword().isEmpty()){
            throw new RegisterException("Password is empty");
        } else if (user.getFirstName() == null || user.getFirstName().isEmpty()){
            throw new RegisterException("First Name is empty");
        } else if (user.getLastName() == null || user.getLastName().isEmpty()){
            throw new RegisterException("Last Name is empty");
        } else if (user.getEmail() == null || user.getEmail().isEmpty()){
            throw new RegisterException("Email is empty");
        }
    }

    private void isUserExist(User user) throws RegisterException {
        //добавь проверку которая будет прореять есть ли юзер с таким логином или емайлом в базе
        // используй метод userDAO.getUserByEmailOrLogin(user.getLogin()); если он что-то вернет значит юзер уже существует
        User testUser;
        try{
            testUser = userDAO.getUserByEmailOrLogin(user.getLogin());

            if (testUser == null) {
                testUser = userDAO.getUserByEmailOrLogin(user.getEmail());
            }
            if (testUser == null){
                return;
            }
        }catch (DBException e){
            throw new RegisterException(e.getMessage());
        }

        if (user.getLogin().toLowerCase().equals(testUser.getLogin().toLowerCase())){
            throw new RegisterException("User already exists");
        }
        else if(user.getEmail().toLowerCase().equals(testUser.getEmail().toLowerCase())){
            throw new RegisterException("Email already exists");
        }

    }

}
