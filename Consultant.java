public class Consultant {
    private String appointmentID;
    private String appointmentDate;
    private String patientID;
    private String doctorID;

    public Consultant() {}

    public Consultant(String appointmentID, String appointmentDate, String patientID, String doctorID) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.patientID = patientID;
        this.doctorID = doctorID;
    }

    public String getAppointmentID() { return appointmentID; }
    public void setAppointmentID(String appointmentID) { this.appointmentID = appointmentID; }

    public String getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }

    public String getDoctorID() { return doctorID; }
    public void setDoctorID(String doctorID) { this.doctorID = doctorID; }

    public void assignDoc() {
        // handled in HMS menu
    }

    public void displayDate() {
        System.out.println("Appointment ID  : " + appointmentID);
        System.out.println("Date            : " + appointmentDate);
        System.out.println("Patient ID      : " + patientID);
        System.out.println("Doctor ID       : " + doctorID);
    }
}
