package hms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hms.entity.Docters;
import hms.service.DoctorService;

@WebServlet("/doctorlogin")
public class DoctorLogInServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
//		Docters docters=new Docters();
//		docters.setEmail(email);
		try {

			if (DoctorService.doctorLogIn(email, pass)) {
				HttpSession session = req.getSession();
				session.setAttribute("name", email);
				RequestDispatcher dispatcher = req.getRequestDispatcher("doctor.jsp");
				req.setAttribute("login", "yes");
				dispatcher.forward(req, resp);
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher("doctorlogin.jsp");
				req.setAttribute("login", "no");
				dispatcher.include(req, resp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
