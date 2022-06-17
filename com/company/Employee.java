
/**
 * Employee
 */
public class Employee {
    String id;
    String name;
    String surname;
    long TC;
    double payement;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getTC() {
        return this.TC;
    }

    public void setTC(long TC) {
        this.TC = TC;
    }

    public double getPayement() {
        return this.payement;
    }

    public void setPayement(double payement) {
        this.payement = payement;
    }

    @Override
    public boolean equals(Object other) {

        if (other.getClass().equals(this.getClass())) {
            return TC==((Employee)other).TC;
        }
        
        return false;
    }

}