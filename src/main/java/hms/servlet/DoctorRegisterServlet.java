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
import hms.service.DoctorService;
@WebServlet("/registerDoctor")
public class DoctorRegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String firstname = req.getParameter("firstName");
		String lastname = req.getParameter("lastName");
		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		String specialization = req.getParameter("specialization");
		String number = req.getParameter("phoneNumber");

		try {
			if (DoctorService.doctorRegister(email)) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("register_doctor.jsp");
				req.setAttribute("doctor", "yes");
				dispatcher.include(req, resp);

			} else {

				Connection connection = DB.getConnection();
				String query = "insert into Doctors(first_name,last_name,email,phone_number,specialization,password)values(?,?,?,?,?,?)";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, firstname);
				pst.setString(2, lastname);
				pst.setString(3, email);
				pst.setString(4, number);
				pst.setString(5, specialization);
				pst.setString(6, pass);
				pst.execute();
				RequestDispatcher dispatcher = req.getRequestDispatcher("doctorlogin.jsp");
				req.setAttribute("doctor", "succ");
				dispatcher.forward(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
