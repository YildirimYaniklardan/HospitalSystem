
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author Merve DUR
 */
public class Patient implements Comparable<Patient> {

    String name, surname, history, test_results;
    long tc;
    TreeSet<Appointment> appointments = new TreeSet<>();
    ArrayList<Evaluation> evaluations = new ArrayList<>();


    public Patient(String name, String surname, int tc, String history, String test_results) {
        this.name = name;
        this.surname = surname;
        this.history = history;
        this.test_results = test_results;
        this.tc = tc;
        appointments = new TreeSet<>();
        evaluations = new ArrayList<>();
    }

    public Patient(long tc ) {
        this.tc = tc;
    }


    public boolean addAppointment(HospitalSystem h, Doctor d, LocalDateTime time) {
        Appointment a = new Appointment(time,this,d);
        if (h.addAppointment(time, a)) {
            appointments.add(a);
            return true;
        }
        return false;
    }

    public boolean removeAppointment(HospitalSystem h, Appointment a) {
        if (appointments.contains(a)) {
            boolean retVal= h.removeAppointment(a);
            if(retVal)
                appointments.remove(a);
            return retVal;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getHistory() {
        return history;
    }

    public String findHistory(int tcNo) {
        if (this.tc == tcNo) {
            return history;
        } else
            return null;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTest_results() {
        return test_results;
    }

    public void setTest_results(String test_results) {
        this.test_results = test_results;
    }

    public long getTc() {
        return tc;
    }

    public void setTc(long tc) {
        this.tc = tc;
    }

    @Override
    public int compareTo(Patient other) {
        // Compare this Person to other using last names.
        return  Long.valueOf(tc).compareTo(Long.valueOf(other.tc));
        // Compare first names if last names are the same.        
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", history='" + history + '\'' +
                ", test_results='" + test_results + '\'' +
                ", tc=" + tc +
                '}';
    }
}
