package hms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import hms.entity.Patients;
import hms.service.PatientService;

@WebServlet("/patientprofile")
public class PatientProfileServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("name");
		PatientService patientService = new PatientService();
		try {

			patientService.patientProfile(email);

			// Adding debug statement
			//System.out.println("Patient retrieved: " + patient.getId());

			// session.setAttribute("patient", patient);
			// session.setAttribute("pid", patient.getId());
			RequestDispatcher dispatcher = req.getRequestDispatcher("patient_profile.jsp");
			dispatcher.forward(req, resp);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
