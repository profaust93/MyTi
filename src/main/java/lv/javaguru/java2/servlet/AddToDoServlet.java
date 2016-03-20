package lv.javaguru.java2.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ruslan on 16.18.3.
 */
public class AddToDoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/addToDo.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String todoId = req.getParameter("id");
        String category = req.getParameter("category");
        String priority = req.getParameter("priority");
        String description = req.getParameter("description");
        PrintWriter out = resp.getWriter();
        out.println(todoId);
        out.println(category);
        out.println(priority);
        out.println(description);

    }
}
