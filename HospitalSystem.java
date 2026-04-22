import java.io.*; 
 import java.util.*; 

 interface Login{ 
 boolean authenticate(String username, String password); 
 } 

 class InvalidLoginException extends Exception{ 
 public InvalidLoginException(String msg){ 
 super(msg); 
 } 
 } 

 class Hospital{ 
 private static Hospital instance = new Hospital(); 

 private Hospital(){} 

 public static Hospital getInstance(){ 
 return instance; 
 } 

 void display(){ 
 System.out.println("\nNMIMS Hospital System"); 
 } 
 } 

 abstract class User implements Login{ 
 protected int userId; 
 protected String username; 
 protected String password; 

 public User(int id, String u, String p){ 
 userId = id; 
 username = u; 
 password = p; 
 } 

 public void login(){ 
 System.out.println("Login Successful: " + username); 
 } 

 abstract void dashboard(); 
 } 

 class Patient{ 
 private int id; 
 private String name; 
 private int age; 
 private String disease; 
 private boolean treated; 

 public Patient(int id, String name, int age, String disease){ 
this.id = id; 
 this.name = name; 
 this.age = age; 
 this.disease = disease; 
 this.treated = false; 
 } 

 public int getId(){ 
 return id; 
 } 

 public boolean isTreated(){ 
 return treated; 
 } 

 public void setTreated(boolean t){ 
 treated = t; 
 } 

 public void updateDisease(String d){ 
 disease = d; 
 } 

 public void display(){ 
 System.out.println("ID: " + id + " Name: " + name + " Age: " + age + " Disease: " + disease); 
 } 

 public String toFileString(){ 
 return id + "," + name + "," + age + "," + disease; 
 } 
 } 

 class Doctor extends User{ 
 String name, specialization, gender; 
 boolean available; 

 public Doctor(int id, String u, String p, String name, String spec, String gender, boolean avail){ 
 super(id, u, p); 
 this.name = name; 
 this.specialization = spec; 
 this.gender = gender; 
 this.available = avail; 
 } 

 public boolean authenticate(String u, String p){ 
 return username.equals(u) && password.equals(p); 
 } 

 void display(){ 
 System.out.println(name + " | " + specialization + " | " + gender + " | Available: " + available); 
 } 

 void treatPatient(Patient p){ 
 System.out.println("Treating patient..."); 
 p.display(); 
 p.setTreated(true); 
 } 

 @Override 
 void dashboard(){ 
 System.out.println("Doctor Dashboard"); 
 } 
 } 

 class Admin extends User{ 

 public Admin(){ 
 super(1, "admin", "123"); 
 } 

 public boolean authenticate(String u, String p){ 
 return username.equals(u) && password.equals(p); 
 } 

 void removeDoctor(ArrayList<Doctor> doctors, String name){ 
 for(int i = doctors.size() - 1; i >= 0; i--){ 
 if(doctors.get(i).name.equals(name)){ 
 doctors.remove(i); 
 } 
 } 
 } 

 void viewDoctors(ArrayList<Doctor> doctors){ 
 for(Doctor d : doctors) 
 d.display(); 
 } 

 @Override 
 void dashboard(){ 
 System.out.println("Admin Dashboard"); 
 } 
 } 

 class Receptionist extends User{ 

 public Receptionist(){ 
 super(2, "reception", "123"); 
 } 

 public boolean authenticate(String u, String p){ 
 return username.equals(u) && password.equals(p); 
 } 

 Patient registerPatient(int id, String name, int age, String disease){ 
 return new Patient(id, name, age, disease); 
 } 

 void updatePatient(Patient p, String disease){ 
 p.updateDisease(disease); 
 } 

 @Override 
 void dashboard(){ 
 System.out.println("Receptionist Dashboard"); 
 } 

 class Billing{ 
 class Payment{ 
 String mode; 

 Payment(String mode){ 
 this.mode = mode; 
 } 

 void processPayment(){ 
 System.out.println("Payment Successful via " + mode); 
 } 
 } 

 void generateBill(Patient p, Scanner sc){ 
 if(!p.isTreated()){ 
 System.out.println("Treatment not completed!"); 
 return; 
 } 

 System.out.print("Enter Amount: "); 
 double amount = sc.nextDouble(); 

 System.out.println("1. Cash\n2. Card"); 
 int ch = sc.nextInt(); 

 String mode = (ch == 1) ? "Cash" : "Card"; 

 System.out.println("Total Bill: " + amount); 

 Payment pay = new Payment(mode); 
 pay.processPayment(); 
 } 
 } 
 } 

 public class HospitalSystem{ 

 static Patient searchPatient(ArrayList<Patient> patients, int id){ 
 for (Patient p : patients) 
 if (p.getId() == id) 
 return p; 
 return null; 
 } 

 static Doctor searchDoctor(ArrayList<Doctor> doctors, String name){ 
 for(Doctor d : doctors) 
 if (d.name.equals(name)) 
 return d; 
 return null; 
 } 

 static void savePatients(ArrayList<Patient> patients){ 
 try{ 
 FileWriter fw = new FileWriter("patients.txt"); 
 for (Patient p : patients){ 
 fw.write(p.toFileString() + "\n"); 
 } 
 fw.close(); 
 }catch(Exception e){ 
 System.out.println("File Error!"); 
 } 
 } 

 public static void main(String[] args){ 

 Scanner sc = new Scanner(System.in); 

 Hospital.getInstance().display(); 

 ArrayList<Patient> patients = new ArrayList<>(); 
 ArrayList<Doctor> doctors = new ArrayList<>(); 

 Admin admin = new Admin(); 
 Receptionist r = new Receptionist(); 

 int choice; 

 do{ 
 System.out.println("\n1.Admin 2.Receptionist 3.Doctor 4.Exit"); 
 choice = sc.nextInt(); 
 try{ 
 switch(choice){ 
 case 1: 
 System.out.print("Username: "); 
 String au = sc.next(); 
 System.out.print("Password: "); 
 String ap = sc.next(); 

 if(!admin.authenticate(au, ap)) 
 throw new InvalidLoginException("Invalid Admin Login!"); 

 admin.login(); 

 System.out.println("1.Add Doctor 2.View Doctors"); 
 int sub = sc.nextInt(); 

 if(sub == 1){ 
 System.out.print("ID: "); 
 int id = sc.nextInt(); 
 System.out.print("Username: "); 
 String u = sc.next(); 
 System.out.print("Password: "); 
 String p = sc.next(); 
 System.out.print("Name: "); 
 String name = sc.next(); 
 System.out.print("Specialization: "); 
 String spec = sc.next(); 
 System.out.print("Gender: "); 
 String g = sc.next(); 
 System.out.print("Available: "); 
 boolean avail = sc.nextBoolean(); 

 doctors.add(new Doctor(id, u, p, name, spec, g, avail)); 
 }else{ 
 admin.viewDoctors(doctors); 
 } 
 break; 

 case 2: 
 System.out.print("Username: "); 
 String ru = sc.next(); 
 System.out.print("Password: "); 
 String rp = sc.next(); 

 if(!r.authenticate(ru, rp)) 
 throw new InvalidLoginException("Invalid Reception Login!"); 

 r.login(); 

 System.out.println("1.New Patient 2.Search Patient"); 
 int pch = sc.nextInt(); 

 if(pch == 1){ 
 System.out.print("ID: "); 
 int id = sc.nextInt(); 
 System.out.print("Name: "); 
 String name = sc.next(); 
 System.out.print("Age: "); 
 int age = sc.nextInt(); 
 System.out.print("Disease: "); 
 String d = sc.next(); 

 patients.add(r.registerPatient(id, name, age, d)); 
 }else{ 
 System.out.print("Enter ID: "); 
 int id = sc.nextInt(); 
 Patient p = searchPatient(patients, id); 
 if (p != null) p.display(); 
 else System.out.println("Not found!"); 
 } 
 break; 

 case 3: 
 System.out.print("Username: "); 
 String du = sc.next(); 
 System.out.print("Password: "); 
 String dp = sc.next(); 

 Doctor logged = null; 

 for(Doctor d : doctors){ 
 if(d.authenticate(du, dp)){ 
 logged = d; 
 break; 
 } 
 } 

 if(logged == null) 
 throw new InvalidLoginException("Invalid Doctor Login!"); 

 if(!logged.available){ 
 System.out.println("Doctor not available!"); 
 break; 
 } 

 System.out.print("Patient ID: "); 
 int id = sc.nextInt(); 

 Patient p = searchPatient(patients, id); 

 if(p != null){ 
 logged.treatPatient(p); 
 Receptionist.Billing bill = r.new Billing(); 
 bill.generateBill(p, sc); 
 } 
 break; 
 } 
 }catch(InvalidLoginException e){ 
 System.out.println(e.getMessage()); 
 }catch(Exception e){ 
 System.out.println("Invalid Input!"); 
 } 

 }while(choice != 4); 

 savePatients(patients); 

 System.out.println("System Closed"); 
 } 
 } 