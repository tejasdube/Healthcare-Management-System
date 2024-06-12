package hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hms.connection.DB;

import hms.entity.Appointments;
import hms.entity.Docters;

public class DoctorService {

	public static boolean doctorRegister(String email) throws Exception {

		Connection conn = DB.getConnection();
		PreparedStatement pst = conn.prepareStatement("select*from Doctors where email=?");
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		return rs.next();

	}

	public static Docters doctorLogIn(String email, String pass) throws Exception {
		String sql = "SELECT *FROM doctors WHERE email = ? AND password = ?";

		try (Connection connection = DB.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, email);
			statement.setString(2, pass);
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					Docters docters = new Docters();
					docters.setId(rs.getInt("doctor_id"));
					docters.setFirstname(rs.getString("first_name"));
					docters.setLastname(rs.getString("last_name"));
					docters.setEmail(rs.getString("email"));
					docters.setPhonenumber(rs.getString("phone_number"));
					docters.setSpec(rs.getString("specialization"));
					return docters;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error during doctor login");
		}
		return null;
	}

	public static Docters getDocters(String email) throws Exception {
		Docters docters = null;
		Connection connection = DB.getConnection();
		PreparedStatement pst = connection.prepareStatement("select*from Doctors where email=?");
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			int id = rs.getInt("doctor_id");
			String fname = rs.getString("first_name");
			String lname = rs.getString("last_name");
			String mail = rs.getString("email");
			String number = rs.getString("phone_number");
			String spec = rs.getString("specialization");
			docters = new Docters(id, fname, lname, email, spec, number);
		}
		return docters;

	}

	public static List<Docters> getallDocters() throws Exception {
		List<Docters> list = new ArrayList<Docters>();
		Connection connection = DB.getConnection();
		String query = "select*from Doctors";
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			Docters doctor = new Docters();
			doctor.setId(rs.getInt("doctor_id"));
			doctor.setFirstname(rs.getString("first_name"));
			doctor.setLastname(rs.getString("last_name"));
			doctor.setSpec(rs.getString("specialization"));
			list.add(doctor);
		}
		return list;
	}

}
