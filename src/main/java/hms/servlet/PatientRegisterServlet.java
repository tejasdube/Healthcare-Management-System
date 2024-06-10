package hms.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hms.connection.DB;
import hms.entity.Patients;
import hms.service.PatientService;


@WebServlet("/registerPatient")
public class PatientRegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname = req.getParameter("firstName");
		String lastname = req.getParameter("lastName");
		String email = req.getParameter("email");
		String phonenumber = req.getParameter("phoneNumber");
		String address = req.getParameter("address");
		String password = req.getParameter("pass");

		try {
			Patients patients = new Patients();
			patients.setFirstname(firstname);
			patients.setFirstname(lastname);
			patients.setEmail(email);
			patients.setPassword(password);
			patients.setPhonenumber(phonenumber);
			patients.setAddress(address);
			PatientService userRegisterService = new PatientService();
			if (PatientService.userRegister(email)) {
				req.setAttribute("ErrMsg", "yes");
				RequestDispatcher dispatcher = req.getRequestDispatcher("register_patient.jsp");
				dispatcher.include(req, resp);

			} else {

				Connection conn = DB.getConnection();
				String query = "insert into patients(first_name,last_name,email,password,phone_number,address)values(?,?,?,?,?,?)";

				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, firstname);
				pst.setString(2, lastname);
				pst.setString(3, email);
				pst.setString(4, password);
				pst.setString(5, phonenumber);
				pst.setString(6, address);
				pst.execute();

				RequestDispatcher dispatcher = req.getRequestDispatcher("patientLogin.jsp");
				req.setAttribute("SucMsg", "You are register successfully");
				dispatcher.forward(req, resp);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

}
