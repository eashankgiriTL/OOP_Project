public class ReceptionMedicineCounter {
    private String billID;
    private String patientID;
    private double fee;
    private double medicineCharges;
    private double roomCharges;
    private double totalAmount;

    public ReceptionMedicineCounter() {}

    public ReceptionMedicineCounter(String billID, String patientID, double fee, double medicineCharges, double roomCharges) {
        this.billID = billID;
        this.patientID = patientID;
        this.fee = fee;
        this.medicineCharges = medicineCharges;
        this.roomCharges = roomCharges;
        this.totalAmount = 0.0;
    }

    public String getBillID() { return billID; }
    public void setBillID(String billID) { this.billID = billID; }

    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }

    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }

    public double getMedicineCharges() { return medicineCharges; }
    public void setMedicineCharges(double medicineCharges) { this.medicineCharges = medicineCharges; }

    public double getRoomCharges() { return roomCharges; }
    public void setRoomCharges(double roomCharges) { this.roomCharges = roomCharges; }

    public double getTotalAmount() { return totalAmount; }

    public void generateBill() {
        calculateTotalAmount();
    }

    public void calculateTotalAmount() {
        totalAmount = fee + medicineCharges + roomCharges;
    }

    public void displayBill() {
        System.out.println("Bill ID           : " + billID);
        System.out.println("Patient ID        : " + patientID);
        System.out.println("Consultation Fee  : " + fee);
        System.out.println("Medicine Charges  : " + medicineCharges);
        System.out.println("Room Charges      : " + roomCharges);
        System.out.println("Total Amount      : " + totalAmount);
    }
}
