import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HospitalManagementSystem {

    // Storage (as per project requirement)
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Consultant> consultations = new ArrayList<>();
    static ArrayList<ReceptionMedicineCounter> bills = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
                System.out.println("1. Patient Module");
                System.out.println("2. Doctor Module");
                System.out.println("3. Consultant (Appointment) Module");
                System.out.println("4. Reception / Medicine Counter (Billing) Module");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        patientMenu();
                        break;
                    case 2:
                        doctorMenu();
                        break;
                    case 3:
                        consultantMenu();
                        break;
                    case 4:
                        billingMenu();
                        break;
                    case 5:
                        System.out.println("Exiting... Thank you!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type! Please enter a number.");
                sc.nextLine(); // clear buffer
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------------- PATIENT MENU ----------------------
    static void patientMenu() {
        while (true) {
            try {
                System.out.println("\n--- PATIENT MODULE ---");
                System.out.println("1. Register Patient");
                System.out.println("2. Display All Patients");
                System.out.println("3. Search Patient by ID");
                System.out.println("4. Back");
                System.out.print("Enter choice: ");

                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {
                    case 1:
                        registerPatient();
                        break;
                    case 2:
                        displayAllPatients();
                        break;
                    case 3:
                        searchPatient();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type! Enter a number.");
                sc.nextLine();
            }
        }
    }

    static void registerPatient() {
        System.out.print("Enter Patient ID: ");
        String id = sc.nextLine().trim();

        if (findPatientById(id) != null) {
            System.out.println("Patient ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Gender: ");
        String gender = sc.nextLine().trim();

        System.out.print("Enter Disease: ");
        String disease = sc.nextLine().trim();

        Patient p = new Patient(id, name, age, gender, disease);
        patients.add(p);

        System.out.println("Patient registered successfully!");
    }

    static void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        System.out.println("\n--- ALL PATIENTS ---");
        for (Patient p : patients) {
            p.displayDetails();
            System.out.println("-------------------");
        }
    }

    static void searchPatient() {
        System.out.print("Enter Patient ID to search: ");
        String id = sc.nextLine().trim();

        Patient p = findPatientById(id);
        if (p == null) {
            System.out.println("Patient not found.");
        } else {
            p.displayDetails();
        }
    }

    static Patient findPatientById(String id) {
        for (Patient p : patients) {
            if (p.getId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    // ---------------------- DOCTOR MENU ----------------------
    static void doctorMenu() {
        while (true) {
            try {
                System.out.println("\n--- DOCTOR MODULE ---");
                System.out.println("1. Add Doctor");
                System.out.println("2. Display All Doctors");
                System.out.println("3. Search Doctor by ID");
                System.out.println("4. Back");
                System.out.print("Enter choice: ");

                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {
                    case 1:
                        addDoctor();
                        break;
                    case 2:
                        displayAllDoctors();
                        break;
                    case 3:
                        searchDoctor();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type! Enter a number.");
                sc.nextLine();
            }
        }
    }

    static void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        String id = sc.nextLine().trim();

        if (findDoctorById(id) != null) {
            System.out.println("Doctor ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Specialization: ");
        String spec = sc.nextLine().trim();

        System.out.print("Is Doctor Available? (true/false): ");
        boolean available = sc.nextBoolean();
        sc.nextLine();

        System.out.print("Enter Gender (Male/Female): ");
        String gender = sc.nextLine().trim();

        Doctor d = new Doctor(id, name, spec, available, gender);
        doctors.add(d);

        System.out.println("Doctor added successfully!");
    }

    static void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }
        System.out.println("\n--- ALL DOCTORS ---");
        for (Doctor d : doctors) {
            d.displayDetails();
            System.out.println("-------------------");
        }
    }

    static void searchDoctor() {
        System.out.print("Enter Doctor ID to search: ");
        String id = sc.nextLine().trim();

        Doctor d = findDoctorById(id);
        if (d == null) {
            System.out.println("Doctor not found.");
        } else {
            d.displayDetails();
        }
    }

    static Doctor findDoctorById(String id) {
        for (Doctor d : doctors) {
            if (d.getDoctorId().equalsIgnoreCase(id)) return d;
        }
        return null;
    }

    // ---------------------- CONSULTANT MENU ----------------------
    static void consultantMenu() {
        while (true) {
            try {
                System.out.println("\n--- CONSULTANT (APPOINTMENT) MODULE ---");
                System.out.println("1. Create Consultation (Assign Doctor)");
                System.out.println("2. Display All Consultations");
                System.out.println("3. Back");
                System.out.print("Enter choice: ");

                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {
                    case 1:
                        createConsultation();
                        break;
                    case 2:
                        displayAllConsultations();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type! Enter a number.");
                sc.nextLine();
            }
        }
    }

    static void createConsultation() {
        System.out.print("Enter Appointment ID: ");
        String appId = sc.nextLine().trim();

        if (findConsultationById(appId) != null) {
            System.out.println("Appointment ID already exists!");
            return;
        }

        System.out.print("Enter Appointment Date (DD-MM-YYYY): ");
        String date = sc.nextLine().trim();

        System.out.print("Enter Patient ID: ");
        String pid = sc.nextLine().trim();

        Patient p = findPatientById(pid);
        if (p == null) {
            System.out.println("Patient not found. Register patient first.");
            return;
        }

        System.out.print("Enter Doctor ID: ");
        String did = sc.nextLine().trim();

        Doctor d = findDoctorById(did);
        if (d == null) {
            System.out.println("Doctor not found. Add doctor first.");
            return;
        }

        if (!d.isAvailable()) {
            System.out.println("Doctor is not available right now.");
            return;
        }

        Consultant c = new Consultant(appId, date, pid, did);
        consultations.add(c);

        System.out.println("Consultation created successfully!");
    }

    static Consultant findConsultationById(String appId) {
        for (Consultant c : consultations) {
            if (c.getAppointmentID().equalsIgnoreCase(appId)) return c;
        }
        return null;
    }

    static void displayAllConsultations() {
        if (consultations.isEmpty()) {
            System.out.println("No consultations found.");
            return;
        }
        System.out.println("\n--- ALL CONSULTATIONS ---");
        for (Consultant c : consultations) {
            c.displayDate();
            System.out.println("-------------------");
        }
    }

    // ---------------------- BILLING MENU ----------------------
    static void billingMenu() {
        while (true) {
            try {
                System.out.println("\n--- RECEPTION / MEDICINE COUNTER (BILLING) MODULE ---");
                System.out.println("1. Generate Bill");
                System.out.println("2. Display All Bills");
                System.out.println("3. Back");
                System.out.print("Enter choice: ");

                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {
                    case 1:
                        generateBill();
                        break;
                    case 2:
                        displayAllBills();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type! Enter a number.");
                sc.nextLine();
            }
        }
    }

    static void generateBill() {
        System.out.print("Enter Bill ID: ");
        String billId = sc.nextLine().trim();

        if (findBillById(billId) != null) {
            System.out.println("Bill ID already exists!");
            return;
        }

        System.out.print("Enter Patient ID: ");
        String pid = sc.nextLine().trim();

        Patient p = findPatientById(pid);
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter Consultation Fee: ");
        double fee = sc.nextDouble();

        System.out.print("Enter Medicine Charges: ");
        double med = sc.nextDouble();

        System.out.print("Enter Room Charges: ");
        double room = sc.nextDouble();
        sc.nextLine();

        ReceptionMedicineCounter b = new ReceptionMedicineCounter(billId, pid, fee, med, room);
        b.generateBill();
        bills.add(b);

        System.out.println("Bill generated successfully!");
        b.displayBill();
    }

    static ReceptionMedicineCounter findBillById(String billId) {
        for (ReceptionMedicineCounter b : bills) {
            if (b.getBillID().equalsIgnoreCase(billId)) return b;
        }
        return null;
    }

    static void displayAllBills() {
        if (bills.isEmpty()) {
            System.out.println("No bills found.");
            return;
        }
        System.out.println("\n--- ALL BILLS ---");
        for (ReceptionMedicineCounter b : bills) {
            b.displayBill();
            System.out.println("-------------------");
        }
    }
}
