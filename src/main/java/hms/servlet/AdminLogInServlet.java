package hms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin")
public class AdminLogInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("admin@gmail.com".equals(username) && "admin@123".equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", true);
            resp.sendRedirect("admindashboard.jsp");
        }
        else {
            req.setAttribute("error", "Invalid username or password");
            RequestDispatcher dispatcher = req.getRequestDispatcher("adminlogin.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
