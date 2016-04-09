package lv.javaguru.java2.filter;

import lv.javaguru.java2.SpringConfig.SpringConfig;
import lv.javaguru.java2.controller.*;
import lv.javaguru.java2.controller.profile.VievUserProfileController;
import lv.javaguru.java2.controller.timelaps.AddTimeLapsController;
import lv.javaguru.java2.controller.timelaps.EditTimeLapsController;
import lv.javaguru.java2.controller.timelaps.ViewTimeLapsController;
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
        urlToController.put("/registration", new RegisterController());
        urlToController.put("/login", applicationContext.getBean(LoginController.class));
        urlToController.put("/viewTimeLaps",applicationContext.getBean(ViewTimeLapsController.class));
        urlToController.put("/addTimeLaps",applicationContext.getBean(AddTimeLapsController.class));
        urlToController.put("/editTimeLaps",applicationContext.getBean(EditTimeLapsController.class));
        urlToController.put("/viewUserProfile",applicationContext.getBean(VievUserProfileController.class));
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
        if(isLogIn == null || !isLogIn) {
            controller = applicationContext.getBean(LoginController.class);
        }  else {
            controller  = Optional.ofNullable(urlToController.get(contextURI)).orElse(applicationContext.getBean(ErrorController.class));
        }

        MVCModel model;
        String methodName = httpServletRequest.getMethod();
        if("GET".equalsIgnoreCase(methodName)) {
            try {
                model = controller.processGet(httpServletRequest);
            } catch (RedirectException e) {
                httpServletResponse.sendRedirect(e.getUrlToRedirect());
                return;
            }
        } else {
            try {
                model = controller.processPost(httpServletRequest);
            } catch (RedirectException e) {
                httpServletResponse.sendRedirect(e.getUrlToRedirect());
                return;
            }
        }

        httpServletRequest.setAttribute("data",model.getData());
        ServletContext context = servletRequest.getServletContext();
        RequestDispatcher requestDispatcher = context.getRequestDispatcher(model.getJspName());
        requestDispatcher.forward(httpServletRequest,httpServletResponse);

    }

    @Override
    public void destroy() {

    }
}
