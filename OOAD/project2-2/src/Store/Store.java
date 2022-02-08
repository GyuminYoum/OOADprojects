package Store;

import Staff.Clerk;
import Items.*;

import java.util.ArrayList;
import java.util.HashMap;


public class Store {
    private static Double Cash_Register;
    //arraylist of Item objects to represent inventory
    private ArrayList<Items> Inventory;
    //hashmap to keep track of the counts
    private static HashMap<String, Integer> Inventory_stock= new HashMap<String, Integer>();
    private Double Inventory_value;
    private Clerk staff_member;
    private static int daysPassed;
    private ArrayList<> Items_sold;
    private Double total_salePrice;
    private float money_added;
    private static ArrayList<String> Order_list;
    private static float money_withdrawn;

    //default constructor
    public Store(){
        Cash_Register=0.0;
        daysPassed=0;



    }

    public void ManageStaff() {
    }

    public void Report(){
    }
    public static void set_daysPassed(Integer days1){
        daysPassed=days1;
    }
    public static Integer get_daysPassed(){
        return daysPassed;
    }
    public static Integer get_register(){
        return Cash_Register;
    }
    public static HashMap<String,Integer> get_orderedItems(){
        return Ordered_Items;
    }
    public static Float get_moneyWithdrawn(){
        return money_withdrawn;
    }
    public static void set_moneyWithdrawn(Float value1){
        money_withdrawn=value1;
    }

}



