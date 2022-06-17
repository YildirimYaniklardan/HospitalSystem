
import java.util.Queue;

/**
 * Nurse
 */
public class Nurse extends Employee {
    
    Queue<Treatment> treatmentList;
    
    public Nurse(Queue<Treatment> treatmentList) {
        this.setId("nurse");
        this.treatmentList = treatmentList;
    }

    public Queue<Treatment> getTreatmentList() {
        return this.treatmentList;
    }

    public void setTreatmentList(Queue<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }

    public Nurse treatmentList(Queue<Treatment> treatmentList) {
        setTreatmentList(treatmentList);
        return this;
    }

    public void makeTreatment(){
        if (!treatmentList.isEmpty()) {
            treatmentList.poll();
        }
    }

    
}