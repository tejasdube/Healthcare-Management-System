package hms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.If;

import hms.entity.Patients;
import hms.service.PatientService;

@WebServlet("/patientlogin")
public class PatientLogInServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		// PatientService patientService = new PatientService();
		// Patients patients = new Patients();
		// patients.setEmail(email);
		try {
			Patients patients = PatientService.patientLogIn(email, pass);
			if (patients != null) {
				HttpSession session = req.getSession();
				session.setAttribute("pid", patients.getId());
				session.setAttribute("patient", patients);
				session.setAttribute("name", patients.getFirstname()+" "+patients.getLastname());
				
				// HttpSession session = req.getSession();
				// session.setAttribute("loggedIn", true);

				RequestDispatcher dispatcher = req.getRequestDispatcher("patient.jsp");
				req.setAttribute("name", email);
				dispatcher.forward(req, resp);

			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher("patientLogin.jsp");
				req.setAttribute("login", "no");
				dispatcher.include(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
