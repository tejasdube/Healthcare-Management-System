package hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import hms.connection.DB;
import hms.entity.Docters;
import hms.entity.Patients;

public class PatientService {

	public static boolean userRegister(String email) throws Exception {
		Connection connection = DB.getConnection();
		String query = "select * from patients where email=?";
		PreparedStatement pst = connection.prepareStatement(query);
		// Patients patients = new Patients();
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		return rs.next();
	}

	public static Patients patientLogIn(String email, String password) throws Exception {
		String sql="select*from Patients where email=? and password=?";
		try (Connection connection = DB.getConnection();
				
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, email);
			statement.setString(2, password);
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					Patients patients = new Patients();
					patients.setId(rs.getInt("patient_id"));
					patients.setFirstname(rs.getString("first_name"));
					patients.setLastname(rs.getString("last_name"));
					patients.setEmail(rs.getString("email"));
					patients.setAddress(rs.getString("address"));
					patients.setPhonenumber(rs.getString("phone_number"));
					return patients;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error during doctor login");
		}
		return null;
	}
	

	public static Patients patientProfile(String email) throws Exception {
		Patients patients = null;
		Connection connection = DB.getConnection();
		PreparedStatement pst = connection.prepareStatement("select*from Patients where email=?");
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			int id = rs.getInt("patient_id");
			String firstname = rs.getString("first_name");
			String lastname = rs.getString("last_name");
			 String mail=rs.getString("email");
			String phonenumber = rs.getString("phone_number");
			String address = rs.getString("address");
			patients = new Patients(id, firstname, lastname, email, phonenumber, address);

		}
		return patients;

	}

	public static void updatePatient(String fname, String lname, String email, String pnumber, String add)
			throws Exception {

		Connection connection = DB.getConnection();
		String query = "update patients set first_name=?,last_name=?,phone_number=?,address=? where email=?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, fname);
		pst.setString(2, lname);
		pst.setString(3, pnumber);
		pst.setString(4, add);
		pst.setString(5, email);

		pst.executeUpdate();

	}
}
