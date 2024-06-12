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

@WebServlet("/Appointments")
public class DoctorAppointmnetsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppointmentService appointmentService = new AppointmentService();
        Integer did = (Integer) req.getSession().getAttribute("did");

        try {
            List<Appointments> list = appointmentService.getAppointmentsByDoctorId(did);
            req.setAttribute("dlist", list);
            RequestDispatcher dispatcher = req.getRequestDispatcher("doctorApp.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
