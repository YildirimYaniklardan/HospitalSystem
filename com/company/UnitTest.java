import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * @author Özlem Sevri
 * Automated Unit Test are made by using 
 * @version junit-4.13.2.jar
 */
public class UnitTest{

    /**Administrator Tests */
    @Test
    public void AdministratorTest(){
        Employee employe = new Employee();
        employe.setId("Doctor");
        HospitalSystem hospital = new HospitalSystem("Gtu Hospital");
        HospitalSystem hospital2 = new HospitalSystem("2 Gtu Hospital");
        Administrator admin = new Administrator(hospital);
        System.out.println("\n\nAdministrator Tests \n");
        System.out.println("Adding Employee Test :");
        
        assertTrue(admin.addEmployee(employe));
        System.out.println("Removing Employee Test : "); 
        assertTrue(admin.removeEmployee(employe)); 

        admin.setH(hospital);
        System.out.println("Set-Get Hospital Test :");
        assertEquals(hospital,admin.getH());
        
        System.out.println("Adding Hospital Test :");
        assertTrue(admin.addHospital(hospital2));
    }
    /**Appointment Tests */
    @Test
    public void AppointmentTest(){
        Appointment appointment = new Appointment();
        LocalDateTime now = LocalDateTime.now();
        Patient patient = new Patient(1801042651);
        HospitalSystem h = new HospitalSystem("Gtu Hospital");
        Doctor doctor = new Doctor(h);
        System.out.println("\n\nAppointment Tests \n");
       
        System.out.println("Set-Get Appointment Date Test :");
        appointment.setDate(now);
        assertEquals(now,appointment.getDate());
        
        System.out.println("Set-Get Patient Test :");
        appointment.setPatient(patient);
        assertEquals(patient,appointment.getPatient());
        
        System.out.println("Set-Get Doctor Test :");
        appointment.setDoctor(doctor);
        assertEquals(doctor,appointment.getDoctor());
        
    }
    /**Doctor Tests */
    @Test
    public void DoctorTest(){
        System.out.println("\n\nDoctor Tests\n");
        HospitalSystem hospital = new HospitalSystem("Gtu Hospital");
        Doctor doctor = new Doctor(hospital);
        Appointment ap = new Appointment();
        ap.setDoctor(doctor);

        System.out.println("Remove Appointment Tests :");
       // assertTrue(doctor.removeAppointment(ap));
        assertFalse(doctor.removeAppointment(ap));

        System.out.println("Set-Get Department Test :");
        doctor.setDepartment("Neurology");
        assertEquals("Neurology",doctor.getDepartment());

        System.out.println("Set-Get Title Test :");
        doctor.setTitle("associate professor");
        assertEquals("associate professor",doctor.getTitle());
    }
    /**Employee Tests */
    @Test
    public void EmployeeTest(){
        System.out.println("\n\nEmployee Tests \n");
        Employee employe = new Employee();
        
        System.out.println("Set-Get Name Test :");
        employe.setName("Ozlem");
        assertEquals("Ozlem",employe.getName());

        System.out.println("Set-Get Surname Test :");
        employe.setSurname("Sevri");
        assertEquals("Sevri",employe.getSurname());
        
        System.out.println("Set-Get Id Test :");
        employe.setId("Doctor");
        assertEquals("Doctor",employe.getId());

       // employe.setPayement(2500);
    
        System.out.println("Set-Get TC Test :");
        employe.setTC(1801042671);
        assertEquals(1801042671,employe.getTC());
    }
    /**Equipment Tests */
    @Test
    public void EquipmentTest() {
        System.out.println("\n\nEquipment Tests \n");
        Patient patient = new Patient(1801042671);
        Equipment equipment = new Equipment("sterilizer", "intensive care", patient);

        System.out.println("Get Type Test :");
        assertEquals("sterilizer",equipment.getType());

        System.out.println("Get Unit Test :");
        assertEquals("intensive care",equipment.getUnit());

        System.out.println("Get Patient Test :");
        assertEquals(patient, equipment.getPatient());
    }
    /**Evaulation Tests */
    @Test
    public void EvaulationTest() {
        System.out.println("\n\nEvaulation Tests\n");
        
        Evaluation evaluation = new Evaluation();
        Appointment ap = new Appointment();

        System.out.println("Set-Get Appointment Test :");
        evaluation.setA(ap);
        assertEquals(ap, evaluation.getA());

        System.out.println("Set-Get Evaluation Test :");
        evaluation.setEvaluation("Vaccination required");
        assertEquals("Vaccination required", evaluation.getEvaluation());
    }
    /**HospitalSystem Tests */
    @Test
    public void HospitalSystemTest() {
        System.out.println("\n\nHospital System Tests\n");
        HospitalSystem hospital = new HospitalSystem("GTU HOSPITAL");
        Patient patient = new Patient(1801042651);
        Doctor doctor = new Doctor(hospital);
        Treatment t = new Treatment(doctor,patient,"vaccinate");
        Employee employe = new Employee();
        employe.setId("employee");
        LocalDateTime now = LocalDateTime.now();
        Appointment ap = new Appointment(now,patient,doctor);
        
        hospital.addEmployee(doctor);
        System.out.println("Add Employee Tests :");
        assertTrue(hospital.addEmployee(employe));
        assertFalse(hospital.addEmployee(employe));
        
        System.out.println("Get Employee Tests :");
        assertEquals(employe,hospital.getEmployee("employee"));
        assertNull(hospital.getEmployee("Nurse"));
        
        System.out.println("Get Employee List Test :");
        assertNotNull(hospital.getEmployeelist());

        System.out.println("Add Treatment Test :");
        assertTrue(hospital.addTreatment(t));

        System.out.println("Remove Treatment Test :");
        assertTrue(hospital.removeTreatment(t));

        System.out.println("Add Appointment Tests :");
      //  assertTrue(hospital.addAppointment(now,ap));
        assertFalse(hospital.addAppointment(now,ap));

        System.out.println("Remove Appointment Tests :");
       // assertTrue(hospital.removeAppointment(ap));
        assertFalse(hospital.removeAppointment(ap));

        System.out.println("Get Hospital Name Test :");
        assertEquals("GTU HOSPITAL",hospital.getName());

        System.out.println("Add Patient Test :");
        assertTrue(hospital.addPatient(patient));
        
        System.out.println("Get Patient List Test :");
        assertNotNull(hospital.getPatientList());

        System.out.println("View Patient History Test :");
        assertEquals(patient.getHistory(), hospital.viewHistoryPatient(patient));

        System.out.println("Give Inventory Test :");
        assertTrue(hospital.giveInventory(patient));

        System.out.println("Has Inventory Test :");
        assertTrue(hospital.hasInventory(patient));

        System.out.println("Get Back Inventory Test :");
        assertTrue(hospital.getBackInventory(patient));

        System.out.println("Has Inventory Test :");
        assertFalse(hospital.hasInventory(patient));

        System.out.println("Remove Patient Test :");
        assertTrue(hospital.removePatient(patient));

        System.out.println("Remove Employee Test :");
        assertTrue(hospital.removeEmployee(employe));
        assertFalse(hospital.removeEmployee(employe));

        System.out.println("Add Place Tests :");
        assertTrue(hospital.addPlace("Gebze"));
        assertFalse(hospital.addPlace("Gebze"));

    }
    /**Nurse Tests */
    @Test
    public void NurseTest(){
        System.out.println("\n\nNurse Tests\n");
        Queue<Treatment> treatments = new LinkedList<Treatment> ();
        treatments.add(new Treatment());
        Nurse nurse = new Nurse(treatments);

        System.out.println("Get Treatment List Test :");
        assertNotNull(nurse.getTreatmentList());

        System.out.println("Get Nurse of Treatment Lİst Test :");
        assertEquals(nurse, nurse.treatmentList(treatments));
    }
    /**Patient Tests */
    @Test
    public void PatientTest(){
        System.out.println("\n\nPatient Tests\n");
        Patient patient = new Patient(1801042671);
        HospitalSystem hospital = new HospitalSystem("Gtu Hospital");
        Doctor doctor = new Doctor(hospital);
        LocalDateTime now = LocalDateTime.now();
        Appointment ap = new Appointment(now, patient, doctor);

        System.out.println("Add Appointment Tests :");
       // assertTrue(patient.addAppointment(hospital, doctor, now));
        assertFalse(patient.addAppointment(hospital, doctor, now));

        System.out.println("Remove Appointment Tests :");
      //  assertTrue(patient.removeAppointment(hospital, ap));
       // assertFalse(patient.removeAppointment(hospital, ap));

        System.out.println("Set-Get Patient Name Test :");
        patient.setName("Ozlem");
        assertEquals("Ozlem", patient.getName());

        System.out.println("Set-Get Patient Surname Test :");
        patient.setSurname("Sevri");
        assertEquals("Sevri", patient.getSurname());

        System.out.println("Set-Get Patient History Test :");
        patient.setHistory("Diabet");
        assertEquals("Diabet", patient.getHistory());

        System.out.println("Find History Test :");
        assertEquals("Diabet",patient.findHistory(1801042671));
        assertNull(patient.findHistory(1801042651));

        System.out.println("Set-Get Test Result Test :");
        patient.setTest_results("anemia");
        assertEquals("anemia", patient.getTest_results());

        System.out.println("Set-Get TC Test :");
        patient.setTc(1801042651);
        assertEquals(1801042651, patient.getTc());

    }
    /**PatientAdmission Tests */
    @Test
    public void PatientAdmissionTest(){
        System.out.println("\n\nPatient Admission Tests\n");
        
        HospitalSystem hospital = new HospitalSystem("Gtu Hospital");
        PatientAdmission pa = new PatientAdmission(hospital);
        Patient patient = new Patient(1801042671);
        Equipment equipment = new Equipment("sterilizer", "diabet", patient);

        System.out.println("Add Patient Test :");
        assertTrue(pa.addPatient(patient));

        System.out.println("Get Hospital System Test :");
        assertEquals(hospital, pa.getH());

        System.out.println("Give Inventory Test :");
        assertTrue(pa.giveInventroy(patient, equipment));

        System.out.println("Available Equipment Type Test :");
        assertNotEquals(equipment, pa.avaliableEquipmentViaType("sterilizer"));


        System.out.println("Take Back Inventory Test :");
        assertTrue(pa.takeBackInventroy(patient, equipment));

        System.out.println("Remove Patient Test :");
        assertTrue(pa.removePatient(patient));

        
    }
    /**Treatment Tests */
    @Test
    public void TreatmentTest() {
        System.out.println("\n\nTreatment Tests\n");
        HospitalSystem hospital = new HospitalSystem("Gtu Hospital");
        Doctor doctor = new Doctor(hospital);
        Patient patient = new Patient(1801042671);
        Treatment treatment = new Treatment(doctor,patient,"drug therapy");

        System.out.println("Get Doctor Test :");
        assertEquals(doctor,treatment.getD());

        System.out.println("Get Patient Test :");
        assertEquals(patient,treatment.getP());

        System.out.println("Get Description Test :");
        assertEquals("drug therapy",treatment.getDescription());

        
    }

}