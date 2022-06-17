import java.time.LocalDateTime;

// compareTo method added.


public class Appointment implements Comparable<Appointment>{
    LocalDateTime date;
    Patient patient; 
    Doctor doctor;


    public Appointment() {
    }

    public Appointment(LocalDateTime date, Patient patient, Doctor doctor) {
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    public int compareTo(Appointment other){
        return date.compareTo(other.date);
    }
}
