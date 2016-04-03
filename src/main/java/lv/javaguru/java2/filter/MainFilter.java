package lv.javaguru.java2.filter;

import lv.javaguru.java2.controller.*;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.user.UserModelImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MainFilter implements Filter {
    Map<String,MVCController> urlToController;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urlToController = new HashMap<>();
        urlToController.put("/hello",new HelloWorldController());
        urlToController.put("/registration", new RegistrController());
        urlToController.put("/login", new LoginController(new UserModelImpl()));
        urlToController.put("/viewTimeLaps",new ViewTimeLapsController());
        urlToController.put("/addTimeLaps",new AddTimeLapsController());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        MVCController controller;
        String contextURI = httpServletRequest.getServletPath();
        if(contextURI.matches(".*(css|jpg|png|gif|js)$")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        HttpSession session = httpServletRequest.getSession();
        Boolean isLogIn = (Boolean) session.getAttribute("IsLoggedIn");
        if(isLogIn == null || !isLogIn) {
            controller = new LoginController(new UserModelImpl());
        }  else {
            controller  = Optional.ofNullable(urlToController.get(contextURI)).orElse(new ErrorController());
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
            model = controller.processPost(httpServletRequest);
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
