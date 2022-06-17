
/**
 * Treatment
 */
public class Treatment {
    Doctor d;
    Patient p;
    String description;


    public Treatment() {
    }

    public Treatment(Doctor d, Patient p, String description) {
        this.d = d;
        this.p = p;
        this.description = description;
    }

    public Doctor getD() {
        return this.d;
    }

    public void setD(Doctor d) {
        this.d = d;
    }

    public Patient getP() {
        return this.p;
    }

    public void setP(Patient p) {
        this.p = p;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
