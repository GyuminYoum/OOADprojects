package Store;

import Staff.Clerk;

import java.util.ArrayList;


public class Store {
    private static int Cash_Register;
    private ArrayList<listItem> Inventory;
    private float Inventory_value;
    private Clerk staff_member;
    private static int daysPassed;
    private ArrayList<listItem> Items_sold;
    private float total_salePrice;
    private float money_added;
    private ArrayList<listItem> Ordered_Items;

    public Store(){
        Cash_Register=0;
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

}



