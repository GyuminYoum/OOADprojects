package Observer;

import java.util.ArrayList;
import java.util.HashMap;

import Staff.Clerk;
import Staff.Electronic;
import Staff.Haphazard;
import Staff.Manual;
import Store.Store;
//Tracker implements the Observer to keep track of how many items each clerk has sold, purchased, and damaged
public class Tracker implements Observer{

    public HashMap<String, ArrayList<Integer>> north = new HashMap<String, ArrayList<Integer>>();
    public HashMap<String, ArrayList<Integer>> south = new HashMap<String, ArrayList<Integer>>();
    //private Store location;


    ArrayList<String> members = new ArrayList<String>();

    /*public Tracker(){
        initialize(members);
    }*/

    private static Tracker instance = new Tracker();

    private Tracker() {
    initialize(members);
    System.out.println("Tracker instantiated");
    }

    public static Tracker GetInstance() {
        return instance;
    }

    //initialize HashMap at the beginning of simulation
    public void initialize(ArrayList<String> members) {
        //Store Store=location;
        //for every clerk
        members.add("Velma");
        members.add("Shaggy");
        members.add("Daphne");
        members.add("Justin");
        members.add("Freddy");
        for (String c: members) {
            ArrayList<Integer> intList = new ArrayList<Integer>();
            for (int i = 0; i < 3; i++) {
                intList.add(0);
            }
            //Key is clerk name, {0,0,0} represents items sold/purchased/damaged
            north.put(c, intList);
            south.put(c, intList);
        }
    }

    public void update(String s, Store Store) {
        //Store Store=location;
        int curr;
        if (Store.get_location() == "North") {
            switch(s) {
                case "sold":
                    //increment sold value for Store.get_onShift().get_name()
                    curr = north.get(Store.get_OnShift().get_name()).get(0) + 1;
                    north.get(Store.get_OnShift().get_name()).set(0, curr);

                    break;
                case "purchased":
                    curr = north.get(Store.get_OnShift().get_name()).get(1) + 1;
                    north.get(Store.get_OnShift().get_name()).set(1, curr);
                    //increment purchased value for Store.get_onShift().get_name()
                    break;
                case "damaged":
                    curr = north.get(Store.get_OnShift().get_name()).get(2) + 1;
                    north.get(Store.get_OnShift().get_name()).set(2, curr);
                    //increment damaged value for Store.get_onShift().get_name()
                    break;
            }
        } else {
            switch (s) {
                case "sold" -> {
                    //increment sold value for Store.get_onShift().get_name()
                    curr = south.get(Store.get_OnShift().get_name()).get(0) + 1;
                    south.get(Store.get_OnShift().get_name()).set(0, curr);
                }
                case "purchased" -> {
                    curr = south.get(Store.get_OnShift().get_name()).get(1) + 1;
                    south.get(Store.get_OnShift().get_name()).set(1, curr);
                }
                //increment purchased value for Store.get_onShift().get_name()
                case "damaged" -> {
                    curr = south.get(Store.get_OnShift().get_name()).get(2) + 1;
                    south.get(Store.get_OnShift().get_name()).set(2, curr);
                }
                //increment damaged value for Store.get_onShift().get_name()
            }
        }

    }

    public void display(Store Store) {
        if (Store.get_location() == "North") {
            int days1=Store.get_daysPassed()-1;
            System.out.println("North Store");
            System.out.println("Day: " + days1);
            System.out.println("Clerk | Items Sold | Items Purchased | Items Damaged");
            for (Clerk c : Store.get_ClerkMember()) {
                //print out each value in the ArrayList mapped to that clerk
                ArrayList<Integer> output = north.get(c.get_name());
                System.out.printf(c.get_name() + " | " + output.get(0) + "           | " + output.get(1) + "                | " + output.get(2) + "\n");
            }
        } else {
            int days1=Store.get_daysPassed()-1;
            System.out.println("South Store");
            System.out.println("Day: " + days1);
            System.out.println("Clerk | Items Sold | Items Purchased | Items Damaged");
            for (Clerk c : Store.get_ClerkMember()) {
                //print out each value in the ArrayList mapped to that clerk
                ArrayList<Integer> output = south.get(c.get_name());
                System.out.printf(c.get_name() + " | " + output.get(0) + "           | " + output.get(1) + "                | " + output.get(2) + "\n");
            }
        }
        //Store Store=location;
        //print out table headers

    }
}
