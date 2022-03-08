package Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import Staff.Clerk;
import Staff.Electronic;
import Staff.Haphazard;
import Staff.Manual;
import Store.Store;
//Tracker implements the Observer to keep track of how many items each clerk has sold, purchased, and damaged
//Eager loading singleton
public class Tracker implements Observer{

    private static Tracker instance = new Tracker();

    private Tracker() {
        //System.out.println("Tracker instantiated");
    }

    public static Tracker GetInstance() {
        return instance;
    }

    public void update(String s, Store Store) {
        //Store Store=location;
        Clerk clerk = Store.get_OnShift();
        //check which store is calling update()
        if (Objects.equals(Store.get_location(), "North")) {
            switch (s) {
                case "sold" -> {
                    //increment sold value for Store.get_onShift().get_name()
                    clerk.set_SN(clerk.get_SN() + 1);
                }
                case "purchased" -> {
                    clerk.set_BN(clerk.get_BN() + 1);
                }
                //increment purchased value for Store.get_onShift().get_name()
                case "damaged" -> {
                    clerk.set_DN(clerk.get_DN() + 1);
                }
                //increment damaged value for Store.get_onShift().get_name()
            }

        } else {
            switch (s) {
                case "sold" -> {
                    //increment sold value for Store.get_onShift().get_name()
                    clerk.set_SS(clerk.get_SS() + 1);
                }
                case "purchased" -> {
                    clerk.set_BS(clerk.get_BS() + 1);
                }
                //increment purchased value for Store.get_onShift().get_name()
                case "damaged" -> {
                    clerk.set_DS(clerk.get_DS() + 1);
                }
                //increment damaged value for Store.get_onShift().get_name()
            }
        }
    }

    public void display(Store Store) {
        int days1=Store.get_daysPassed()-1;

        //check which Store is calling display()
        if (Objects.equals(Store.get_location(), "North")) {
            System.out.println("North Store");
            System.out.println("Day: " + days1);
            System.out.println("Clerk | Items Sold | Items Purchased | Items Damaged");
            for (Clerk c : Store.get_ClerkMember()) {
                //print out the Clerk's soldNorth, boughtNorth, dmgNorth values
                System.out.printf(c.get_name() + " | " + c.get_SN() + "           | " + c.get_BN() + "                | " + c.get_DN() + "\n");
            }
        } else {
            System.out.println("South Store");
            System.out.println("Day: " + days1);
            System.out.println("Clerk | Items Sold | Items Purchased | Items Damaged");
            for (Clerk c : Store.get_ClerkMember()) {
                //print out the Clerk's soldSouth, boughtSouth, dmgSouth values
                System.out.printf(c.get_name() + " | " + c.get_SS() + "           | " + c.get_BS() + "                | " + c.get_DS() + "\n");
            }
        }
        //print out table headers

    }
}
