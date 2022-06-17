// The file "driver.java".

import java.util.*;
import java.time.LocalDateTime;
import Dynamic_Graph.*;

enum Screen{ MAIN_MENU, LOGIN_SCREEN, PATIENT_MENU, NURSE_MENU, DOCTOR_MENU, PATIENT_ADMISSION_MENU, ADMINISTRATOR_MENU, EXIT, PATIENT_DISPLAY_APPOINTMENTS, PATIENT_ADD_APPOINTMENT, 
			 PATIENT_REMOVE_APPOINTMENT, PATIENT_DISPLAY_EVALUATION_REPORTS, PATIENT_DISPLAY_TEST_RESULTS, ADMINISTRATOR_DISPLAY_EMPLOYEES, ADMINISTRATOR_ADD_EMPLOYEE,
			 ADMINISTRATOR_REMOVE_EMPLOYEE, ADMINISTRATOR_ADD_PLACE, ADMINISTRATOR_ADD_ROAD, ADMINISTRATOR_DISPLAY_DISTANCES, ADMINISTRATOR_DISPLAY_PLACES, ADMINISTRATOR_SORT_AND_DISPLAY_EQUIPMENTS, REGISTER_PATIENT }

public class driver{

	public static Scanner sc;
	

	static{

		sc=new Scanner(System.in);
	}

	public static int getIntInRange(int min, int max, String prompt){

		int num=0;
		boolean errorFlag;

		do{
			errorFlag=false;

			try{
				System.out.printf(prompt);
				num=sc.nextInt();

				if(num<min || num>max){
					System.out.printf("Error: The entered value should be an integer in range [%d,%d].\n", min, max);
					errorFlag=true;
				}
			}

			catch(InputMismatchException e){
				sc.nextLine();
				System.out.println("Error: Entered value should be an integer.");
				errorFlag=true;
			}

		}while(errorFlag);

		sc.nextLine();
		System.out.println();

		return num;
	}

	public static int getIntAtLeast(int min, String prompt){

		int num=0;
		boolean errorFlag;

		do{
			errorFlag=false;

			try{
				System.out.printf(prompt);
				num=sc.nextInt();

				if(num<min){
					System.out.printf("Error: The entered value should be at least %d.\n", min);
					errorFlag=true;
				}
			}

			catch(InputMismatchException e){
				sc.nextLine();
				System.out.println("Error: Entered value should be an integer.");
				errorFlag=true;
			}

		}while(errorFlag);

		sc.nextLine();
		System.out.println();

		return num;
	}

	public static long getLongInRange(long min, long max, String prompt){

		long num=0;
		boolean errorFlag;

		do{
			errorFlag=false;

			try{
				System.out.printf(prompt);
				num=sc.nextLong();

				if(num<min || num>max){
					System.out.printf("Error: The entered value should be an integer in range [%d,%d].\n", min, max);
					errorFlag=true;
				}
			}

			catch(InputMismatchException e){
				sc.nextLine();
				System.out.println("Error: Entered value should be an integer.");
				errorFlag=true;
			}

		}while(errorFlag);

		sc.nextLine();
		System.out.println();

		return num;
	}

	public static Employee getEmployee(HospitalSystem h, long id){

		for(Employee emp : h.getEmployeelist())
			if(emp.TC==id)
				return emp;

		return null;
	}

	public static void main(String[] args){

		// Burada, ana while döngüsüne girmeden önce orda kullanılacak objeleri initialize etmek gerekebilir.

		HospitalSystem h=new HospitalSystem("Gebze Devlet Hastanesi");

		

		Administrator admin=new Administrator(h);
		admin.name="Admin";
		admin.surname="Admin";
		admin.TC=99999999999L;
		admin.payement=10000.0;
		h.addEmployee(admin);

		Doctor d1 = new Doctor(h);
		d1.setName("doctor1");
		d1.setSurname("doctor1");
		d1.setTC(55555555555L);
		d1.setDepartment("Cardiology");
		d1.setPayement(12000);
		
		Doctor d2 = new Doctor(h);
		d2.setName("doctor2");
		d2.setSurname("doctor2");
		d2.setTC(66666666666L);
		d2.setDepartment("Haematology");
		d2.setPayement(10250);

		Doctor d3 = new Doctor(h);
		d3.setName("doctor3");
		d3.setSurname("doctor3");
		d3.setTC(77777777777L);
		d3.setDepartment("Microbiology");
		d3.setPayement(8300);

		Patient p1 = new Patient(11111111111L);
		p1.name="patient1";
		p1.surname="patient1";
		p1.history=" ";
		p1.test_results=" ";

		Patient p2 = new Patient(22222222222L);
		p2.name="patient2";
		p2.surname="patient2";
		p2.history=" ";
		p2.test_results=" ";

		Patient p3 = new Patient(33333333333L);
		p3.name="patient3";
		p3.surname="patient3";
		p3.history=" ";
		p3.test_results=" ";

		Patient p4 = new Patient(44444444444L);
		p4.name="patient4";
		p4.surname="patient4";
		p4.history=" ";
		p4.test_results=" ";


		Treatment t1 = new Treatment(d1, p1, "treatment 1");
		Treatment t2 = new Treatment(d2, p2, "treatment 2");
		Treatment t3 = new Treatment(d3, p3, "treatment 3");
		Treatment t4 = new Treatment(d3, p4, "treatment 4");

		Queue <Treatment> treatments = new LinkedList<Treatment>();

		treatments.add(t1);
		treatments.add(t2);
		treatments.add(t3);
		treatments.add(t4);

		Nurse n1 = new Nurse(treatments);
		n1.setTC(88888888888L);
		n1.setName("nurse1");
		n1.setSurname("nurse1");
		n1.setPayement(6000);

		h.addEmployee(d1);
		h.addEmployee(d2);
		h.addEmployee(d3);

		h.addPatient(p1);
		h.addPatient(p2);
		h.addPatient(p3);
		h.addPatient(p4);

		h.addEmployee(n1);

		PatientAdmission pa = new PatientAdmission(h);
		pa.name="pa1";
		pa.surname="pa1";
		pa.TC=45645645645L;
		h.addEmployee(pa);



		String name, surname;
		long tc;
		double payement;
		String title, department;
		Patient patient=null;
		Employee employee=null;
		Nurse nurse=null;
		Doctor doctor=null;
		PatientAdmission patientAdmission=null;
		Administrator administrator=null;
		
		int year, month, day, hour, minute;
		LocalDateTime dateTime=null;
		Appointment appointment=null;
		int i, j;
		LinkedList<Doctor> doctors=null;

		String place1, place2;
		double length;

		Vertex[] parents=null;
		double[] distances=null;

		Integer verID;
		Vertex start;

		Screen navigate=Screen.MAIN_MENU;
		int command;

		while(true){

			// Main Menu
			if(navigate==Screen.MAIN_MENU){

				System.out.println("\n---------- HOSPITAL SYSTEM ----------\n");

				System.out.println("1 - Enter hospital system");
				System.out.println("2 - Exit\n");

				command=getIntInRange(1, 2, "Press 1 to log in to system or 2 to exit: ");

				switch(command){

					case 1:navigate=Screen.LOGIN_SCREEN; break;
					case 2:navigate=Screen.EXIT; break;
				}
			}

			// Login Screen
			else if(navigate==Screen.LOGIN_SCREEN){

				System.out.print("Enter your name: ");
				name=sc.nextLine();

				System.out.print("Enter your surname: ");
				surname=sc.nextLine();

				tc=getLongInRange(10000000000L, 99999999999L, "Enter your ID number: ");

				if((patient=h.getPatient(tc))!=null)
					navigate=Screen.PATIENT_MENU;

				else if((employee=getEmployee(h, tc))!=null){

					if(employee instanceof Nurse)
						navigate=Screen.NURSE_MENU;

					else if(employee instanceof Doctor)
						navigate=Screen.DOCTOR_MENU;

					else if(employee instanceof PatientAdmission)
						navigate=Screen.PATIENT_ADMISSION_MENU;

					else if(employee instanceof Administrator)
						navigate=Screen.ADMINISTRATOR_MENU;
				}

				else{

					System.out.println("User not found.\n");
					navigate=Screen.LOGIN_SCREEN;
				}
			}

			// User (Patient) Menu
			else if(navigate==Screen.PATIENT_MENU){

				System.out.println("1 - Display appointments");
				System.out.println("2 - Add appointment");
				System.out.println("3 - Remove appointment");
				System.out.println("4 - Display evaluation reports");
				System.out.println("5 - Display test results");
				System.out.println("6 - Log out");

				command=getIntInRange(1, 6, "Choose an option: ");

				switch(command){

					case 1:navigate=Screen.PATIENT_DISPLAY_APPOINTMENTS; break;
					case 2:navigate=Screen.PATIENT_ADD_APPOINTMENT; break;
					//case 3:navigate=;
					//case 4:navigate=;
					//case 5:navigate=;
					case 6:navigate=Screen.MAIN_MENU; break;
				}
			}

			// Nurse Menu
			else if(navigate==Screen.NURSE_MENU){

				System.out.println("1 - Display examination result");
				System.out.println("2 - Display doctor evaluation");
				System.out.println("3 - Serve medicine");
				System.out.println("4 - Vaccinate");
				System.out.println("5 - Display blood analysis");
				System.out.println("6 - Log out\n");

				command=getIntInRange(1, 6, "Choose an option: ");

				switch(command){

					case 6:navigate=Screen.LOGIN_SCREEN; break;
				}

				//----
			}

			// Doctor Menu
			else if(navigate==Screen.DOCTOR_MENU){

				System.out.println("1 - Add appointment");
				System.out.println("2 - Cancel appointment");
				System.out.println("3 - Display appointments");
				System.out.println("4 - Log out");

				command=getIntInRange(1, 4, "Choose an option: ");

				switch(command){

					case 4:navigate=Screen.LOGIN_SCREEN; break;
				}
				//----
			}

			// Patient Admission Menu
			else if(navigate==Screen.PATIENT_ADMISSION_MENU){

				System.out.println("1 - Register patient");
				System.out.println("2 - Make appointment");
				System.out.println("3 - Display appointments");
				System.out.println("4 - Log out");

				command=getIntInRange(1, 4, "Choose an option: ");

				switch(command){
					case 1:navigate=Screen.REGISTER_PATIENT; break;

					case 4:navigate=Screen.LOGIN_SCREEN; break;
				}

				// -----
			}

			// Administrator Menu
			else if(navigate==Screen.ADMINISTRATOR_MENU){
				
				System.out.println("1 - Display employees");
				System.out.println("2 - Add employee");
				System.out.println("3 - Remove employee");
				System.out.println("4 - Display places");
				System.out.println("5 - Add place");
				System.out.println("6 - Add road");
				System.out.println("7 - Display distances");
				System.out.println("8 - Sort and display equipments");
				System.out.println("9 - Log out\n");

				command=getIntInRange(1, 9, "Choose an option: ");

				switch(command){

					case 1:navigate=Screen.ADMINISTRATOR_DISPLAY_EMPLOYEES; break;
					case 2:navigate=Screen.ADMINISTRATOR_ADD_EMPLOYEE; break;
					case 3:navigate=Screen.ADMINISTRATOR_REMOVE_EMPLOYEE; break;
					case 4:navigate=Screen.ADMINISTRATOR_DISPLAY_PLACES; break;
					case 5:navigate=Screen.ADMINISTRATOR_ADD_PLACE; break;
					case 6:navigate=Screen.ADMINISTRATOR_ADD_ROAD; break;
					case 7:navigate=Screen.ADMINISTRATOR_DISPLAY_DISTANCES; break;
					case 8:navigate=Screen.ADMINISTRATOR_SORT_AND_DISPLAY_EQUIPMENTS; break;
					case 9:navigate=Screen.MAIN_MENU; break;
				}
			}

			// Exit
			else if(navigate==Screen.EXIT){

				System.out.println("Program terminated.\n");
				break;
			}

			// Patient Display Appointments
			else if(navigate==Screen.PATIENT_DISPLAY_APPOINTMENTS){

				System.out.println("--- Appointments ---\n");

				for(Appointment app : patient.appointments){

					dateTime=app.date;

					System.out.printf("Date: %d/%d/%d, Time: %d:%d, Doctor: %s - %s\n", dateTime.getDayOfMonth(), dateTime.getMonthValue(), dateTime.getYear(), dateTime.getHour(), dateTime.getMinute(), app.doctor.name, app.doctor.department);
				}
				
				if(patient.appointments.size()==0)
					System.out.println("You have no appointments.");

				System.out.println();

				navigate=Screen.PATIENT_MENU;
			}

			// Patient Add Appointment
			else if(navigate==Screen.PATIENT_ADD_APPOINTMENT){

				year=getIntInRange(2022, 2023, "Enter the appointment's year [2022, 2023]: ");
				month=getIntInRange(1, 12, "Enter the appointment's month [1, 12]: ");
				day=getIntInRange(1, 31, "Enter the appointment's day [1, 31]: ");
				hour=getIntInRange(6, 20, "Enter the appointment's hour [6, 20]: ");
				minute=0;

				dateTime=LocalDateTime.of(year, month, day, hour, minute);

				i=0; // number of doctors
				doctors=new LinkedList<Doctor>();

				for(Employee emp : h.getEmployeelist()){

					if(emp instanceof Doctor){

						doctor=(Doctor)emp;

						doctors.add(doctor);
						i++;

						System.out.printf("%d : %s - %s %s\n", i, doctor.department, doctor.name, doctor.surname);
					}					
				}

				System.out.println();

				j=getIntInRange(1, i, "Choose the department and doctor: ");

				doctor=doctors.get(j-1);

				appointment=new Appointment(dateTime, patient, doctor);

				// Check if the doctor is available on that time

				if(patient.appointments.contains(appointment)){

					System.out.println("The appointment can not be make, you already have an appointment on that time.\n");
				}

				else{

					// Aynı appointment'i doktora da eklemek lazım

					patient.appointments.add(appointment);
					System.out.println("The apointment is succesfully made.\n");
				}

				navigate=Screen.PATIENT_MENU;
			}

			// Patient Remove Appointment
			else if(navigate==Screen.PATIENT_REMOVE_APPOINTMENT){

				i=0; // appointment count

				for(Appointment app : patient.appointments){

					i++;

					dateTime=app.date;

					System.out.printf("%d) Date: %d/%d/%d, Time: %d:%d, Doctor: %s - %s\n", i, dateTime.getDayOfMonth(), dateTime.getMonthValue(), dateTime.getYear(), dateTime.getHour(), dateTime.getMinute(), app.doctor.name, app.doctor.department);
				}

				System.out.println();
				
				if(patient.appointments.size()==0)
					System.out.println("You have no appointments to remove.\n");

				else{

					command=getIntInRange(1, i, "Choose an appointment to remove: ");

					j=0;

					for(Appointment app : patient.appointments){
						j++;
						if(j==command)
							appointment=app;
					}

					patient.appointments.remove(appointment);

					System.out.println("Appointment is succesfully cancelled.\n");
				}

				navigate=Screen.PATIENT_MENU;
			}

			// Patient Display Appointments
			else if(navigate==Screen.PATIENT_DISPLAY_EVALUATION_REPORTS){

				System.out.println("--- Evaluations ---\n");

				if(patient.evaluations.size()==0)
					System.out.println("You have no previous evaluations.\n");

				for(i=0; i<patient.evaluations.size(); ++i){

					System.out.printf("%s\n\n", patient.evaluations.get(i));
				}

				navigate=Screen.PATIENT_MENU;
			}

			// Patients Display Test Results
			else if(navigate==Screen.PATIENT_DISPLAY_TEST_RESULTS){

				System.out.println("--- Test Results ---\n");

				System.out.printf("%s\n\n", patient.test_results);

				navigate=Screen.PATIENT_MENU;
			}

			// Administrator Display Employees
			else if(navigate==Screen.ADMINISTRATOR_DISPLAY_EMPLOYEES){

				System.out.println("--- Employees ---\n");

				for(i=0; i<h.getEmployeelist().size(); ++i){

					employee=h.getEmployeelist().get(i);

					System.out.printf("Name: %s, Surname: %s, ID Number: %d\n", employee.name, employee.surname, employee.TC);	
				}

				if(h.getEmployeelist().size()==1)
					System.out.println("There is no employee to remove.");

				System.out.println();

				navigate=Screen.ADMINISTRATOR_MENU;
			}

			// Adiministrator Add Employee
			else if(navigate==Screen.ADMINISTRATOR_ADD_EMPLOYEE){

				System.out.println("Enter the name of the employee: ");
				name=sc.nextLine();

				System.out.println("Enter the surname of the employee: ");
				surname=sc.nextLine();

				tc=getLongInRange(10000000000L, 99999999999L, "Enter the ID of the employee: ");

				payement=(double)getIntAtLeast(5000, "Enter the salary of the employee: ");

				System.out.println("--- Employee Types ---\n");

				System.out.println("1 - Nurse");
				System.out.println("2 - Doctor");
				System.out.println("3 - Patient Admission");

				command=getIntInRange(1, 3, "Enter the employee type: ");

				if(command==1){

					nurse=new Nurse(new LinkedList<Treatment>());
					nurse.name=name;
					nurse.surname=surname;
					nurse.payement=payement;
					nurse.TC=tc;

					h.addEmployee(nurse);

					System.out.printf("The nurse %s is succesfully added.\n\n", name);
				}

				else if(command==2){

					System.out.println("Enter the department of the doctor: ");
					department=sc.nextLine();

					System.out.println("Enter the title of the doctor: ");
					title=sc.nextLine();

					doctor=new Doctor(h);
					doctor.name=name;
					doctor.surname=surname;
					doctor.payement=payement;
					doctor.TC=tc;
					doctor.department=department;
					doctor.title=title;

					h.addEmployee(doctor);

					System.out.printf("The doctor %s is succesfully added.\n\n", name);
				}

				else{

					patientAdmission=new PatientAdmission(h);
					patientAdmission.name=name;
					patientAdmission.surname=surname;
					patientAdmission.payement=payement;
					patientAdmission.TC=tc;

					h.addEmployee(patientAdmission);

					System.out.printf("The patient admission %s is succesfully added.\n\n", name);
				}

				navigate=Screen.ADMINISTRATOR_MENU;
			}

			// Administrator Remove Employee
			else if(navigate==Screen.ADMINISTRATOR_REMOVE_EMPLOYEE){

				System.out.println("--- Employees ---\n");

				for(i=0; i<h.getEmployeelist().size(); ++i){


					employee=h.getEmployeelist().get(i);

					if(!(employee instanceof Administrator))
						System.out.printf("Name: %s, Surname: %s, ID Number: %d\n", employee.name, employee.surname, employee.TC);	
				}

				if(h.getEmployeelist().size()==1)
					System.out.println("There is no employee to remove.");

				System.out.println();

				tc=getLongInRange(10000000000L, 99999999999L, "Enter the ID number of the employee to remove: ");

				if(tc!=99999999999L){

					employee=getEmployee(h, tc);

					if(employee!=null){

						h.removeEmployee(employee);
						System.out.println("The employee is succesfully removed.\n");
					}
				}

				else
					System.out.println("The administrator can not be removed.\n");

				navigate=Screen.ADMINISTRATOR_MENU;
			}

			else if(navigate==Screen.ADMINISTRATOR_DISPLAY_PLACES){

				System.out.println("--- Places in the Map ---\n");

				for(String place : h.mappings.keySet())
					System.out.printf("%s\n", place);
				
				if(h.mappings.size()==0)
					System.out.println("There is not any registered place currently.");

				System.out.println();

				navigate=Screen.ADMINISTRATOR_MENU;
			}

			// Administrator Add Place
			else if(navigate==Screen.ADMINISTRATOR_ADD_PLACE){

				System.out.println("Enter the name of the place: ");
				place1=sc.nextLine();

				System.out.println();

				if(h.addPlace(place1))
					System.out.printf("'%s' is succesfully added.\n\n", place1);

				else
					System.out.printf("'%s' was already included in the map.\n\n", place1);

				navigate=Screen.ADMINISTRATOR_MENU;
			}

			// Administrator Add Road
			else if(navigate==Screen.ADMINISTRATOR_ADD_ROAD){

				System.out.println("Enter the name of the first place: ");
				place1=sc.nextLine();

				System.out.println("Enter the name of the second place: ");
				place2=sc.nextLine();

				length=(double)getIntAtLeast(1, "Enter the length of the road: ");

				h.addRoad(place1, place2, length);

				System.out.println("The road is succesfully added.\n");

				navigate=Screen.ADMINISTRATOR_MENU;
			}

			// Administrator Display Distances
			else if(navigate==Screen.ADMINISTRATOR_DISPLAY_DISTANCES){

				System.out.println("Enter the starting place: ");
				place1=sc.nextLine();

				System.out.println();

				verID=HospitalSystem.mappings.get(place1);

				if(verID==null)
					System.out.println("There is no such place in the map.\n");

				else{

					start=HospitalSystem.roadMap.getVertex(verID);

					System.out.println("--- Shortest distance to all places ---\n");

					DGraph.shortestPathsFrom(start, HospitalSystem.roadMap, parents, distances);
				}

				navigate=Screen.ADMINISTRATOR_MENU;
			}

			else if(navigate==Screen.ADMINISTRATOR_SORT_AND_DISPLAY_EQUIPMENTS){

				admin.selectionSort();

				navigate=Screen.ADMINISTRATOR_MENU;
			}

			else if(navigate==Screen.REGISTER_PATIENT){

				System.out.print("Enter TC: ");
				long patTC = sc.nextLong();

				System.out.print("Enter name: ");
				String patName = sc.nextLine();

				System.out.print("Enter surname: ");
				String patSurname = sc.nextLine();

				Patient p = new Patient(patTC);
				p.name=patName;
				p.surname=patSurname;
				p.history=" ";
				p.test_results=" ";

				if(h.getPatient(patTC) == null){
					h.addPatient(p);			
				}
				else{
					System.out.println("User is already exist!");
				}

				navigate=Screen.PATIENT_ADMISSION_MENU;

			}
		}
	}

	/*

	Eksik menüler:

	Nurse
	Doctor
	PatientAdmission
	Administrator

	Not: Doktor'a appointment eklenmiyo hasta randevu aldığında, dolayısıyla doktor randevuları görüntüleyemeyecek.

	*/
}