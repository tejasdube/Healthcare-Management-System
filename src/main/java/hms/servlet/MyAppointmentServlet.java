package hms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hms.entity.Appointments;
import hms.service.AppointmentService;

@WebServlet("/patientAppointments")
public class MyAppointmentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AppointmentService appointmentService = new AppointmentService();
		int patientId = (int) req.getSession().getAttribute("pid");

		try {
			List<Appointments> list = appointmentService.getAppointmentsByPatientId(patientId);
			req.setAttribute("Alist", list);
			RequestDispatcher dispatcher = req.getRequestDispatcher("patientAppoin.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
