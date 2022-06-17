
import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;
import java.util.Map.Entry;

/**
 * Administrator
 */
public class Administrator extends Employee{
    HospitalSystem h;
    SkipList<HospitalSystem> hospitals = new SkipList<>();
    
    public Administrator() {}

    public boolean addEmployee(Employee e){
        return h.addEmployee(e);
    }

    public boolean removeEmployee(Employee e){
        return h.removeEmployee(e);
    }

    public Administrator(HospitalSystem h) {
        this.h = h;
    }

    public HospitalSystem getH() {
        return this.h;
    }

    public void setH(HospitalSystem h) {
        this.h = h;
    }

    public boolean addHospital(HospitalSystem hospital){
        hospitals.add(hospital);
        return true;
    }

    public void selectionSort()
    {   

        int counter = 0;
        
        try {
            File myObj = new File("./resources/equipment.txt");
            Scanner myReader = new Scanner(myObj);
            

            while (myReader.hasNextLine()) {
                Integer num=myReader.nextInt();
                String equipmentName = myReader.next();
                String equipmentUnit = myReader.next();
                Equipment equipment=new Equipment(equipmentName,equipmentUnit,null);
                h.equipmentList.put(num,equipment);
                
                counter++;

            }
            
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        int [] arr = new int[counter];
        
        int k = 0;

        for(Entry<Integer, Equipment> entry : h.equipmentList.entrySet()){

            int eq = entry.getKey();

            
            arr[k] = eq;
            k++;
         }

        for (int i = 0; i < counter-1; i++)
        {

            int min_idx = i;
            for (int j = i+1; j < counter; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }

        System.out.println("Sorted Equipment List : ");
        System.out.println();

        for(Entry<Integer, Equipment> entry : h.equipmentList.entrySet()){

            for(int i = 0; i < counter; i++){
                if(entry.getKey() == arr[i]){
                    System.out.print(arr[i] + " " +entry.getValue().getType() + " " + entry.getValue().getUnit());
                }
            }
            System.out.println();
         }

         System.out.println();
        
    }
}