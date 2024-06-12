package hms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hms.connection.DB;
import hms.entity.Docters;
import hms.service.DoctorService;

@WebServlet("/addAppointments")
public class AddAppointmentsServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("patientId");
		String did = req.getParameter("doctorId");
		String date = req.getParameter("appointmentDate");
		String reason = req.getParameter("reason");
		String name=req.getParameter("name");

		try {

			Connection connection = DB.getConnection();
			String query = "insert into Appointments( patient_id,doctor_id,appointment_date,reason,name)values(?,?,?,?,?) ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, pid);
			pst.setString(2, did);
			pst.setString(3, date);
			pst.setString(4, reason);
			pst.setString(5, name);
			pst.execute();
            List<Docters>list=DoctorService.getallDocters();
            req.setAttribute("doctorlist", list);
			RequestDispatcher dispatcher = req.getRequestDispatcher("bookappoinments.jsp");
			//String yes = "yes";
			req.setAttribute("appoin", "yes");
			dispatcher.include(req, resp);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
