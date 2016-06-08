/*
package lv.javaguru.java2.filter;

import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.controller.*;
import lv.javaguru.java2.challenge.controller.AddChallengeController;
import lv.javaguru.java2.challenge.controller.ViewChallengeController;
import lv.javaguru.java2.controller.message.ViewMessageController;
import lv.javaguru.java2.controller.profile.EditUserProfileController;
import lv.javaguru.java2.controller.profile.ViewUserProfileController;
import lv.javaguru.java2.controller.profile.ViewUserProfileListController;
import lv.javaguru.java2.timelaps.timelaps.AddTimeLapsController;
import lv.javaguru.java2.timelaps.timelaps.EditTimeLapsController;
import lv.javaguru.java2.timelaps.timelaps.ViewTimeLapsController;
import lv.javaguru.java2.controller.todo.ToDoAddController;
import lv.javaguru.java2.controller.todo.ToDoListController;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MainFilter implements Filter {
    private Map<String,MVCController> urlToController;
    private ApplicationContext applicationContext;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        try{
            applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        } catch (BeansException e ) {
            e.printStackTrace();
        }
        urlToController = new HashMap<>();
        urlToController.put("/index", applicationContext.getBean(HelloWorldController.class));
        urlToController.put("/register", applicationContext.getBean(RegisterController.class));
        urlToController.put("/viewTimeLaps",applicationContext.getBean(ViewTimeLapsController.class));
        urlToController.put("/addTimeLaps",applicationContext.getBean(AddTimeLapsController.class));
        urlToController.put("/editTimeLaps",applicationContext.getBean(EditTimeLapsController.class));
        urlToController.put("/viewUserProfile",applicationContext.getBean(ViewUserProfileController.class));
        urlToController.put("/editUserProfile",applicationContext.getBean(EditUserProfileController.class));
        urlToController.put("/addChallenge",applicationContext.getBean(AddChallengeController.class));
        urlToController.put("/addToDo",applicationContext.getBean(ToDoAddController.class));
        urlToController.put("/viewUserProfileList",applicationContext.getBean(ViewUserProfileListController.class));
        urlToController.put("/viewMessage",applicationContext.getBean(ViewMessageController.class));
        urlToController.put("/viewChallenge",applicationContext.getBean(ViewChallengeController.class));

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        MVCController controller;
        String contextURI = httpServletRequest.getServletPath();
        if(contextURI.matches(".*(css|jpg|png|gif|js|map|woff|ttf)$")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        HttpSession session = httpServletRequest.getSession();
        Boolean isLogIn = (Boolean) session.getAttribute("IsLoggedIn");




    }

    @Override
    public void destroy() {

    }
}
*/
