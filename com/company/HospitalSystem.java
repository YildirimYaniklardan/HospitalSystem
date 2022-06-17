
import java.time.LocalDateTime;
import Dynamic_Graph.*;
import java.util.*;

/**
 * @author Merve DUR
 */
public class HospitalSystem implements Comparable<HospitalSystem>{
    NavigableMap<Integer, Equipment> equipmentList;
    private BinarySearchTree<Patient> patientList;
    String name;
    private List<Employee> employeelist;
    Queue<Treatment> treatmentList;
    PriorityQueue<Appointment> appointments;
    NavigableSet<Patient> hasInventroyPatients;
    static DGraph roadMap;
    static HashMap<String, Integer> mappings;
    
    static {
        roadMap = new DGraph(false); // undirected
	mappings = new HashMap<String, Integer>();
    }

    public HospitalSystem(String name) {
        if(name == null)
            throw new IllegalArgumentException("Invalid name for the hospital (null).");

        this.patientList = new BinarySearchTree<>();
        this.employeelist = new ArrayList<>();
        this.treatmentList = new LinkedList<>();
        this.appointments = new PriorityQueue<>();
        this.hasInventroyPatients = new TreeSet<>();
        this.name = name;
        this.equipmentList=new TreeMap<Integer, Equipment>();
    }

    public boolean addEmployee(Employee e) {
        if (employeelist.contains(e)) {
            return false;
        } else {
            employeelist.add(e);
            return true;
        }
    }

    public Employee getEmployee(String id) {
        Iterator<Employee> empI = employeelist.iterator();
        while (empI.hasNext()) {
            Employee tmp = empI.next();
            if (tmp.getId().equals(id)) {
                return tmp;
            }
        }
        return null;
    }

    public boolean removeEmployee(Employee e) {
        if (employeelist.contains(e)) {
            return employeelist.remove(e);
        } else {
            return false;
        }
    }

    public boolean addTreatment(Treatment t) {
        return treatmentList.offer(t);
    }

    public boolean removeTreatment(Treatment t) {
        return treatmentList.remove(t);
    }

    public boolean addAppointment(LocalDateTime ap, Appointment a) {
        if (!employeelist.contains(a.getDoctor())) {
            return false;
        }
        LocalDateTime[] tmpFreeList = a.getDoctor().freeTimes;
        for (int i = 0; i < tmpFreeList.length; i++) {
            if (tmpFreeList[i] != null && tmpFreeList[i].compareTo(ap) == 0) {
                appointments.offer(new Appointment(ap, a.getPatient(), a.getDoctor()));
                tmpFreeList[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean removeAppointment(Appointment a) {
        if (!appointments.contains(a)) {
            return false;
        }
        LocalDateTime[] tmpFreeList = a.getDoctor().freeTimes;
        for (int i = 0; i < tmpFreeList.length; i++) {
            if (tmpFreeList[i] == null) {
                tmpFreeList[i] = a.date;
                appointments.remove(a);
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeelist() {
        return this.employeelist;
    }

    public void printTreatments() {

        for (int i = 0; i < treatmentList.size(); i++) {
            System.out.print(treatmentList.toArray()[i].toString() + "->");
        }
        System.out.println();
    }

    public void printPatients() {
        System.out.println(patientList.toString());
    }

    public void printEmployees() {

        for (int i = 0; i < employeelist.size(); i++) {
            System.out.print(employeelist.get(i).toString() + "->");
        }
        System.out.println();
    }

    public boolean addPatient(Patient e) {
        patientList.add(e);
        return patientList.addReturn;
    }

    public boolean removePatient(Patient e) {
        patientList.remove(e);
        return patientList.deleteReturn != null;
    }

    public BinarySearchTree<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(BinarySearchTree<Patient> patientList) {
        this.patientList = patientList;
    }

    public String viewHistoryPatient(Patient e) {
        return e.getHistory();
    }

    public Patient getPatient(long tcNo) {
        Patient p = new Patient(tcNo);

        return patientList.find(p);
    }

    public boolean hasInventory(Patient p) {

        return hasInventroyPatients.contains(p);

    }

    public boolean giveInventory(Patient p) {

        try {
            hasInventroyPatients.add(p);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public boolean getBackInventory(Patient p) {

        try {
            hasInventroyPatients.remove(p);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    @Override
    public int compareTo(HospitalSystem other) {
		return name.compareTo(other.name);
    }

	/**
	 * Adds the given place into the map and returns true if it is not in the map, returns false otherwise.
	 */
	public static boolean addPlace(String placeName){
		
		if(placeName==null)
			throw new IllegalArgumentException("The argument 'placeName' should not be null.");
	
		// The place is already in the roadMap.
		if(mappings.get(placeName)!=null)
			return false;
		
		// If it is not in the roadMap.
		Vertex v = roadMap.newVertex(placeName, 0);
		roadMap.addVertex(v);
		mappings.put(placeName, v.getID());
	
		return true;
	}
	
	/**
	 * Adds a road between the specified places with the specified length. If the places are not in the map, adds the places first.
	 */
	public static void addRoad(String placeName1, String placeName2, double length){
	
		if(placeName1==null || placeName2==null)
			throw new IllegalArgumentException("Names of the places should not be null.");
		
		if(length<0)
		throw new IllegalArgumentException("Length of the road can not be negative.");
		
		if(mappings.get(placeName1) == null)
			HospitalSystem.addPlace(placeName1);
		
		if(mappings.get(placeName2) == null)
			HospitalSystem.addPlace(placeName2);
		
		Integer id1 = mappings.get(placeName1);
		Integer id2 = mappings.get(placeName2);
		
		roadMap.addEdge(id1, id2, length);
	}
}
