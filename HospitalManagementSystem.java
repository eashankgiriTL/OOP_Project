import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class HospitalManagementSystem {

    // Storage (as per project requirement)
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Consultant> consultations = new ArrayList<>();
    static ArrayList<ReceptionMedicineCounter> bills = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);
    private static final int MAX_TEXT_LENGTH = 60;

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
                System.out.println("Unexpected error occurred. Please try again.");
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
        String id = readId("Enter Patient ID: ");

        if (findPatientById(id) != null) {
            System.out.println("Patient ID already exists!");
            return;
        }

        String name = readBoundedText("Enter Name: ");

        int age = readAge("Enter Age: ");

        String gender = readGender("Enter Gender (Male/Female/Other): ");

        String disease = readBoundedText("Enter Disease: ");

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
        String id = readId("Enter Doctor ID: ");

        if (findDoctorById(id) != null) {
            System.out.println("Doctor ID already exists!");
            return;
        }

        String name = readBoundedText("Enter Name: ");

        String spec = readBoundedText("Enter Specialization: ");

        boolean available = readYesNo("Is Doctor Available? (yes/no): ");

        String gender = readGender("Enter Gender (Male/Female/Other): ");

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
        String appId = readId("Enter Appointment ID: ");

        if (findConsultationById(appId) != null) {
            System.out.println("Appointment ID already exists!");
            return;
        }

        String date = readDate("Enter Appointment Date (YYYY-MM-DD): ");

        String pid = readId("Enter Patient ID: ");

        Patient p = findPatientById(pid);
        if (p == null) {
            System.out.println("Patient not found. Register patient first.");
            return;
        }

        String did = readId("Enter Doctor ID: ");

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
        String billId = readId("Enter Bill ID: ");

        if (findBillById(billId) != null) {
            System.out.println("Bill ID already exists!");
            return;
        }

        String pid = readId("Enter Patient ID: ");

        Patient p = findPatientById(pid);
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        double fee = readNonNegativeDouble("Enter Consultation Fee: ");
        double med = readNonNegativeDouble("Enter Medicine Charges: ");
        double room = readNonNegativeDouble("Enter Room Charges: ");

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

    static String readBoundedText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }
            if (value.length() > MAX_TEXT_LENGTH) {
                System.out.println("Input is too long. Maximum " + MAX_TEXT_LENGTH + " characters.");
                continue;
            }
            return value;
        }
    }

    static String readId(String prompt) {
        while (true) {
            String id = readBoundedText(prompt);
            if (!id.matches("[A-Za-z0-9_-]+")) {
                System.out.println("ID can contain only letters, numbers, underscore and hyphen.");
                continue;
            }
            return id;
        }
    }

    static int readAge(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int age = Integer.parseInt(sc.nextLine().trim());
                if (age < 0 || age > 130) {
                    System.out.println("Age must be between 0 and 130.");
                    continue;
                }
                return age;
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a whole number.");
            }
        }
    }

    static String readGender(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (value.equalsIgnoreCase("male")) return "Male";
            if (value.equalsIgnoreCase("female")) return "Female";
            if (value.equalsIgnoreCase("other")) return "Other";
            System.out.println("Please enter Male, Female, or Other.");
        }
    }

    static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("y")) return true;
            if (value.equalsIgnoreCase("no") || value.equalsIgnoreCase("n")) return false;
            System.out.println("Please enter yes or no.");
        }
    }

    static String readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(value);
                return date.toString();
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Use YYYY-MM-DD.");
            }
        }
    }

    static double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value < 0) {
                    System.out.println("Amount cannot be negative.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
    }
}
