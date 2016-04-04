package lv.javaguru.java2.model.user;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.model.exceptions.LoginException;
import lv.javaguru.java2.model.exceptions.RegisterException;

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
    public Boolean registerUser(User user) throws RegisterException {


        // если что-то плохо выкинуть эксепшн типо throw new RegistrationException("Login is empty")
        checkFields(user);

        isUserExist(user); //заимплементь метод

        /*if(!checkFields(user)){
            throw new RegisterException("Login is empty"); // просто вызови метод, а мтеод путьс генерит эксепшены
        }else{
            return true;   // после return код не пишется, здесь просто оставь экспешн
        }*/
        // чтобы вывести пользователя вызови userDAO.create(user)
        try {
            userDAO.create(user);
            //userDAO.getUserByEmailOrLogin(user.getLogin()); //тут это не надо у тебя уже есть юзер после создание

        } catch (DBException e){
            throw new RegisterException(e.getMessage());
        }
        return true;
    }

    // также проверять обязательный праметры типо логина пароля и т.д чтобы не были пустыми
    private void checkFields(User user) throws RegisterException {
        if(user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new RegisterException("Login is empty");
        }
        // лучше сделай в таком стиле
        // на каждое поле свой эксепшн
       /* return !(user.getLogin().isEmpty()
                || user.getPassword().isEmpty()
                || user.getEmail().isEmpty()
                || user.getFirstName().isEmpty()
                || user.getLastName().isEmpty());*/
    }

    private void isUserExist(User user) throws RegisterException {
        //добавь проверку которая будет прореять есть ли юзер с таким логином или емайлом в базе
        // используй метод userDAO.getUserByEmailOrLogin(user.getLogin()); если он что-то вернет значит юзер уже существует
        throw new RegisterException("No implemented yet"); // уберешь когда напишешь метож
    }


    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


}
