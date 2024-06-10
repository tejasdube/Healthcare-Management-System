package hms.entity;

public class Patients {

	int id;
	String firstname;
	String lastname;
	String email;
	String password;
	String phonenumber;
	String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Patients(int id, String firstname, String lastname, String email, String phonenumber, String address) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;

		this.phonenumber = phonenumber;
		this.address = address;
	}

	public Patients() {

	}
}
