package lv.javaguru.java2.filter;


import lv.javaguru.java2.controller.ErrorController;
import lv.javaguru.java2.controller.LoginController;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by germans.kuzmins on 2016.05.10..
 */
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session =  httpServletRequest.getSession();
        String contextURI = httpServletRequest.getServletPath();

        Boolean isLogIn = (Boolean) session.getAttribute("IsLoggedIn");
        if((isLogIn == null || !isLogIn) && !contextURI.equals("/register")) {
            //httpServletResponse.sendRedirect("/java2/login");
            filterChain.doFilter(servletRequest, servletResponse);

        }  else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
