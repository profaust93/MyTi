package lv.javaguru.java2.controller;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserProfileDAOImpl;
import lv.javaguru.java2.model.MVCModel;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Camille on 02.04.2016.
 */
public class ProfileController implements MVCController{

    @Override
    public MVCModel processGet(HttpServletRequest req){

        UserProfileDAOImpl userProfileDAO = new UserProfileDAOImpl();
        Map<String,Object> userProfileData = new HashMap<>();
        Long userId = (Long) req.getSession().getAttribute("userId");

        try {
            userProfileData.put("firstName",userProfileDAO.getById(userId).getFirstName());
            userProfileData.put("LastName",userProfileDAO.getById(userId).getLastName());
            userProfileData.put("email",userProfileDAO.getById(userId).getEmail());
        } catch (DBException e) {
            e.printStackTrace();
        }


        return new MVCModel("/viewProfile.jsp",userProfileData);
    }

    @Override
    public MVCModel processPost (HttpServletRequest req) {
        return new MVCModel("/poka HZ", "data Object");
    }

}
