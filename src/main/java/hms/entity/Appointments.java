package hms.entity;

public class Appointments {

	int aid;
	Patients patients;
	String appointmentdate;
	String reason;
	Docters docters;

	public Docters getDocters() {
		return docters;
	}

	public void setDocters(Docters docters) {
		this.docters = docters;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	

	public Patients getPatients() {
		return patients;
	}

	public void setPatients(Patients patients) {
		this.patients = patients;
	}

	public String getAppointmentdate() {
		return appointmentdate;
	}

	public void setAppointmentdate(String appointmentdate) {
		this.appointmentdate = appointmentdate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Appointments(int aid, Patients patients, String appointmentdate, String reason, Docters docters) {
		super();
		this.aid = aid;
		this.patients = patients;
		this.appointmentdate = appointmentdate;
		this.reason = reason;
		this.docters = docters;
	}

	public Appointments() {

	}

}
