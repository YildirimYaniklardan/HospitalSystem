
/**
 * PatientAdmission
 */
public class PatientAdmission extends Employee {

    HospitalSystem h;

    public PatientAdmission() {
    }

    public boolean addPatient(Patient e) {
        return h.addPatient(e);
    }

    public boolean removePatient(Patient e) {
        return h.removePatient(e);
    }

    public PatientAdmission(HospitalSystem h) {
        this.h = h;
    }

    public HospitalSystem getH() {
        return this.h;
    }

    public void setH(HospitalSystem h) {
        this.h = h;
    }

    public boolean giveInventroy(Patient p, Equipment i) {

        if (!h.hasInventory(p)) {

            for (Integer key : h.equipmentList.keySet()) {

                Equipment e = h.equipmentList.get(key);

                if (e == i) {

                    e.setPatient(p);

                }

            }

            return h.giveInventory(p);

        }

        return false;

        // patient will add inverntory map

    }



    public boolean takeBackInventroy(Patient p, Equipment i) {

        if (h.hasInventory(p)) {

            for (Integer key : h.equipmentList.keySet()) {

                Equipment e = h.equipmentList.get(key);

                if (e == i) {

                    e.setPatient(null);

                }

            }

            return h.getBackInventory(p);

        }

        return false;

        // patient will add inverntory map

    }

    public Equipment avaliableEquipmentViaType(String type) {
        for (Integer key : h.equipmentList.keySet()) {
            Equipment e = h.equipmentList.get(key);
            if (e.getPatient() == null && e.getType().equals(type)) {
                return e;
            }
        }
        return null;

    }

}