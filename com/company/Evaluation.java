
public class Evaluation {
    Appointment a;
    String evaluation;

    public Evaluation() {
    }

    public Evaluation(Appointment a, String evaluation) {
        this.a = a;
        this.evaluation = evaluation;
    }

    public Appointment getA() {
        return this.a;
    }

    public void setA(Appointment a) {
        this.a = a;
    }

    public String getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }


}
