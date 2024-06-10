package hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import hms.connection.DB;
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

	public static boolean patientLogIn(String email, String password) throws Exception {
		Connection connection = DB.getConnection();
		String query = "select * from patients where email=? and password=?";
		PreparedStatement pst = connection.prepareStatement(query);
		// Patients patients = new Patients();
		pst.setString(1, email);
		pst.setString(2, password);
		ResultSet rs = pst.executeQuery();
		return rs.next();
	}

	public static Patients patientProfile(String email) throws Exception {
		Patients patients = null;
		Connection connection = DB.getConnection();
		PreparedStatement pst = connection.prepareStatement("select*from patients where email=?");
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			int id = rs.getInt("patient_id");
			String firstname = rs.getString("first_name");
			String lastname = rs.getString("last_name");
			// String email=rs.getString("email");
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
