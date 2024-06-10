package hms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hms.entity.Docters;
import hms.entity.Patients;
import hms.service.DoctorService;
import hms.service.PatientService;
@WebServlet("/bookappoinmnent")
public class BookAppoinmentsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session=req.getSession();
		session.getAttribute("name");
		PatientService patientService = new PatientService();
		DoctorService doctorService=new DoctorService();
	try {
		
		List<Docters>docters=doctorService.getallDocters();
		RequestDispatcher dispatcher=req.getRequestDispatcher("bookappoinments.jsp");
		req.setAttribute("doctorlist", docters);
		dispatcher.forward(req, resp);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}

}
