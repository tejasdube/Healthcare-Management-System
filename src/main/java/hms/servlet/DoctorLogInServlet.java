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
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");

		try {
			Docters docters = DoctorService.doctorLogIn(email, pass);
			if (docters != null) {
				HttpSession session = req.getSession();
				session.setAttribute("did", docters.getId());
				session.setAttribute("doctors",docters);
				session.setAttribute("name", docters.getFirstname() + " " + docters.getLastname());
				//session.setAttribute("spec", docters.getSpec());			
				RequestDispatcher dispatcher = req.getRequestDispatcher("doctor.jsp");
				req.setAttribute("login", "yes");
				dispatcher.forward(req, resp);
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher("doctorlogin.jsp");
				req.setAttribute("login", "no");
				dispatcher.include(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("doctorlogin.jsp");
			req.setAttribute("login", "no");
			dispatcher.include(req, resp);
		}
	}
}
