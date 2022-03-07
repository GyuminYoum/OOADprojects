package Observer;

import java.util.ArrayList;
import java.util.HashMap;

import Staff.Clerk;
import Store.Store;
//Tracker implements the Observer to keep track of how many items each clerk has sold, purchased, and damaged
public class Tracker implements Observer{

    public HashMap<String, ArrayList<Integer>> data = new HashMap<String, ArrayList<Integer>>();
    private Store location;
    public Tracker(Store store1){
        location=store1;
    }

    //initialize HashMap at the beginning of simulation
    public void initialize() {
        Store Store=location;
        //for every clerk
        for (Clerk c: Store.get_ClerkMember()) {
            ArrayList<Integer> intList = new ArrayList<Integer>();
            for (int i = 0; i < 3; i++) {
                intList.add(0);
            }
            //Key is clerk name, {0,0,0} represents items sold/purchased/damaged
            data.put(c.get_name(), intList);
        }
    }

    public void update(String s) {
        Store Store=location;
        int curr;
        switch(s) {
            case "sold":
                //increment sold value for Store.get_onShift().get_name()
                curr = data.get(Store.get_OnShift().get_name()).get(0) + 1;
                data.get(Store.get_OnShift().get_name()).set(0, curr);

                break;
            case "purchased":
                curr = data.get(Store.get_OnShift().get_name()).get(1) + 1;
                data.get(Store.get_OnShift().get_name()).set(1, curr);
                //increment purchased value for Store.get_onShift().get_name()
                break;
            case "damaged":
                curr = data.get(Store.get_OnShift().get_name()).get(2) + 1;
                data.get(Store.get_OnShift().get_name()).set(2, curr);
                //increment damaged value for Store.get_onShift().get_name()
                break;
        }
    }

    public void display() {
        Store Store=location;
        //print out table headers
        int days1=Store.get_daysPassed()-1;
        System.out.println("Day: " + days1);
        System.out.println("Clerk | Items Sold | Items Purchased | Items Damaged");
        for (Clerk c : Store.get_ClerkMember()) {
            //print out each value in the ArrayList mapped to that clerk
            ArrayList<Integer> output = data.get(c.get_name());
            System.out.printf(c.get_name() + " | " + output.get(0) + "           | " + output.get(1) + "                | " + output.get(2) + "\n");
        }
    }
}
