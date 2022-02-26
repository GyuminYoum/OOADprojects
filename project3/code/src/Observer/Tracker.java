package Observer;

import java.util.ArrayList;
import java.util.HashMap;

import Staff.Clerk;
import Store.Store;

public class Tracker implements Observer{

    public HashMap<String, ArrayList<Integer>> data = new HashMap<String, ArrayList<Integer>>();

    //initialize HashMap at the beginning of simulation
    public void initialize() {
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
                curr = data.get(Store.get_OnShift().get_name()).get(2);
                data.get(Store.get_OnShift().get_name()).set(2, curr);
                //increment damaged value for Store.get_onShift().get_name()
                break;
        }
    }

    public void display() {
        //print out table headers
        System.out.println("Day: " + Store.get_daysPassed());
        System.out.println("Clerk | Items Sold | Items Purchased | Items Damaged");
        for (Clerk c : Store.get_ClerkMember()) {
            //print out each value in the ArrayList mapped to that clerk
            ArrayList<Integer> output = data.get(c.get_name());
            System.out.printf(c.get_name() + " | " + output.get(0) + "           | " + output.get(1) + "                | " + output.get(2) + "\n");
        }
    }
}
