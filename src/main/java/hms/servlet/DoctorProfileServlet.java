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

@WebServlet("/doctorprofile")
public class DoctorProfileServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("name");

		try {
			Docters docters = DoctorService.getDocters(email);
            //session.setAttribute("doctors", docters);
			RequestDispatcher dispatcher = req.getRequestDispatcher("doctor_profile.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			// Forward to an error page or handle the exception accordingly
			// For simplicity, printing stack trace here
		}
	}
}
