package lv.javaguru.java2.filter;

import lv.javaguru.java2.controller.ErrorController;
import lv.javaguru.java2.controller.HelloWorldController;
import lv.javaguru.java2.controller.LoginController;
import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.model.MVCModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainFilter implements Filter {
    Map<String,MVCController> urlToController;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urlToController = new HashMap<>();
        urlToController.put("/hello",new HelloWorldController());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        MVCController controller;
        String contextURI = httpServletRequest.getServletPath();

        if(httpServletRequest.getSession(true).getAttribute("isLoggedIn").equals("true")) {
            controller = new LoginController();
        } else if(urlToController.get(contextURI) == null) {
            controller = new ErrorController();
        } else {
            controller  = urlToController.get(contextURI);
        }

        MVCModel model;
        String methodName = httpServletRequest.getMethod();
        if("GET".equalsIgnoreCase(methodName)) {
            model = controller.processGet(httpServletRequest);
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
