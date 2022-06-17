
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
/**
 * @author Merve DUR
 */
public class Equipment {

    private String type;
    private String unit;
    private Patient patient;

    public Equipment(String type, String unit, Patient patient) {
        this.type = type;
        this.unit = unit;
        this.patient = patient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                " type='" + type + '\'' +
                " , unit='" + unit + '\'' +
                '}'+'\n';
    }
}
