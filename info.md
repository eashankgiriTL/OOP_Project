STORYLINE;

•The system should support searching by title, author, keywords, and ISBN. Searching the library’s collection database should be available on terminals in the library and available to potential borrowers via the Web.
 
•The borrowing activities are built around checking books out and returning books by borrowers. There are three types of borrowers: students, faculty or staff , and guests. Regardless of the type of borrower, the borrower must have a valid ID card. If the borrower is a student, having the system check with the registrar’s student database validates the ID card.
 
•The book-maintenance activities deal with adding and removing books from the library’s book collection. This requires a library manager to both logically and physically add and remove the book. Books being purchased by the library or books being returned in a damaged state typically cause these activities. If a book is determined to be damaged when it is returned and it needs to be removed from the collection, the last borrower will be assessed a fine.

FLOWCHART;


PAGE 1 — MAIN SYSTEM FLOWCHART

Draw vertically.

1️⃣ Oval

START

↓

2️⃣ Rectangle

Initialize ArrayLists
(patients, doctors,
consultations, bills)

↓

3️⃣ Rectangle

Display Main Menu
1 Patient
2 Doctor
3 Consultant
4 Billing
5 Exit

↓

4️⃣ Diamond

Enter Choice?

From this diamond draw 5 arrows:

1 → Call Patient Module

2 → Call Doctor Module

3 → Call Consultant Module

4 → Call Billing Module

5 → END


For 1–4:

After each module, draw arrow back to:

Display Main Menu

For 5:

Oval:

END


PAGE 2 — PATIENT MODULE FLOWCHART

Oval:

Start Patient Module

↓

Rectangle:

Display Patient Menu
1 Register
2 Display
3 Search
4 Back

↓

Diamond:

Enter Option?

Branches:

If 1 (Register):

Parallelogram:

Input Patient Details

Rectangle:

Add to Patient List

Arrow → Return to Main Menu


---

If 2 (Display):

Rectangle:

Display All Patients

Arrow → Return


---

If 3 (Search):

Parallelogram:

Input Patient ID

Diamond:

Patient Found?

Yes → Rectangle:

Display Details

No → Rectangle:

Show Not Found

Arrow → Return


---

If 4:

Arrow → Return to Main Menu

Done.


---

PAGE 3 — DOCTOR MODULE

Just replace with:

Add Doctor

Display Doctors

Search Doctor


Keep it symmetrical.


---

PAGE 4 — CONSULTANT MODULE

Oval:

Start Consultant Module

↓

Parallelogram:

Input Appointment ID
Input Patient ID
Input Doctor ID

↓

Diamond:

Patient Exists?

If No → Show Error → Return

If Yes →

Diamond:

Doctor Exists?

If No → Show Error → Return

If Yes →

Diamond:

Doctor Available?

If No → Show Unavailable → Return

If Yes →

Rectangle:

Create Consultation
Add to List

Arrow → Return to Main Menu

Done.


---

PAGE 5 — BILLING MODULE

Oval:

Start Billing Module

↓

Parallelogram:

Input Bill ID
Input Patient ID
Input Charges

↓

Diamond:

Patient Exists?

If No → Show Error → Return

If Yes →

Rectangle:

Calculate Total Amount

↓

Rectangle:

Generate Bill
Display Bill

↓

Return to Main Menu

