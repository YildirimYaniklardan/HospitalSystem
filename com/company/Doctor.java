
import java.lang.reflect.Array;
import java.time.LocalDateTime;

/**
 * Doctor
 */
public class Doctor extends Employee {
    String department;
    String title;
    LocalDateTime[] freeTimes ;
    HospitalSystem h;

    public Doctor(HospitalSystem h) {
        this.setId("doctor");
        this.h = h;
        freeTimes = new LocalDateTime[15 * 8];
        for (int i = 0; i < freeTimes.length; i++) {
            freeTimes[i] = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
                    LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
                    0);
        }
    }

    public boolean removeAppointment(Appointment a) {
        if (!a.getDoctor().equals(this)) {
            return false;
        }
        for (int i = 0; i < h.appointments.size(); i++) {
            if (((Appointment) (h.appointments.toArray())[i])== (a)) {
                return h.removeAppointment(a);
            }
        }
        return false;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}