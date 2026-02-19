public class Doctor {
    private String doctorId;
    private String name;
    private String specialization;
    private boolean available;
    private String gender;

    public Doctor() {}

    public Doctor(String doctorId, String name, String specialization, boolean available, String gender) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.available = available;
        this.gender = gender;
    }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public void displayDetails() {
        System.out.println("Doctor ID       : " + doctorId);
        System.out.println("Name            : " + name);
        System.out.println("Specialization  : " + specialization);
        System.out.println("Available       : " + available);
        System.out.println("Gender          : " + gender);
    }
}
