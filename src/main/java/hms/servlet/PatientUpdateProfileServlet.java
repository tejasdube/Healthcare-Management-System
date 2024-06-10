package hms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hms.service.PatientService;

@WebServlet("/updatepatient")
public class PatientUpdateProfileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("name");
		String fname = req.getParameter("firstName");
		String lname = req.getParameter("lastName");
		String pnumber = req.getParameter("phoneNumber");
		String add = req.getParameter("address");

		try {
			PatientService.updatePatient(fname, lname, email, pnumber, add);
			RequestDispatcher dispatcher = req.getRequestDispatcher("update_patient.jsp");
			req.setAttribute("update", "yes");
			dispatcher.include(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
