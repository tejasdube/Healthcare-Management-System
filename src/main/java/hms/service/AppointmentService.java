package hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hms.connection.DB;
import hms.entity.Appointments;
import hms.entity.Docters;
import hms.entity.Patients;

public class AppointmentService {
	// Method to get appointments for a patient
	public List<Appointments> getAppointmentsByPatientId(int patientId) throws Exception {
		List<Appointments> appointments = new ArrayList<>();
		String sql = "SELECT a.appointment_id, a.appointment_date, a.reason, d.first_name, d.last_name, d.specialization "
				+ "FROM appointments a JOIN doctors d ON a.doctor_id = d.doctor_id WHERE a.patient_id = ?";

		try (Connection connection = DB.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, patientId);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					Appointments appointment = new Appointments();
					Docters docters = new Docters();
					appointment.setAid(rs.getInt("appointment_id"));
					appointment.setAppointmentdate(rs.getString("appointment_date"));
					appointment.setReason(rs.getString("reason"));
					docters.setFirstname(rs.getString("first_name"));
					docters.setLastname(rs.getString("last_name"));
					docters.setSpec(rs.getString("specialization"));
					appointment.setDocters(docters);
					appointments.add(appointment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error retrieving appointments");
		}
		return appointments;
	}

	public List<Appointments> getAppointmentsByDoctorId(int doctorid) throws Exception {
		List<Appointments> appointments = new ArrayList<>();
		String sql = "SELECT a.appointment_id, a.patient_id, a.appointment_date, a.reason, p.first_name, p.last_name FROM appointments a JOIN patients p ON a.patient_id = p.patient_id JOIN doctors d ON a.doctor_id = d.doctor_id WHERE a.doctor_id = ?";

		try (Connection connection = DB.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, doctorid);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					Appointments appointment = new Appointments();
					Patients patients = new Patients();
					appointment.setAid(rs.getInt("appointment_id"));
					appointment.setAppointmentdate(rs.getString("appointment_date"));
					appointment.setReason(rs.getString("reason"));
					patients.setFirstname(rs.getString("first_name"));
					patients.setLastname(rs.getString("last_name"));
					appointment.setPatients(patients);
					appointments.add(appointment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error retrieving appointments");
		}
		return appointments;
	}
}
