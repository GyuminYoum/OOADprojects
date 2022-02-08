package Store;

import Items.Items;
import Staff.Clerk;
import Staff.Staff;

import java.util.ArrayList;
import java.util.HashMap;


public class Store {
    private static Double Cash_Register=0.0;
    //arraylist of Item objects to represent inventory
    private static ArrayList<Items> Inventory= new ArrayList<Items>();
    private static ArrayList<Staff> staff_member=new ArrayList<Staff>();
    private static HashMap<String, Integer> Inventory_stock= new HashMap<String, Integer>();
    private Double Inventory_value=0.0;
    private static int daysPassed=0;
    private ArrayList<Items> Items_sold=new ArrayList<Items>();
    private Double total_salePrice=0.0;
    private Double money_added=0.0;
    private static ArrayList<String> Order_list= new ArrayList<String>();
    private static ArrayList<Items> Sold_list=new ArrayList<Items>();
    private static Double money_withdrawn=0.0;
    public static String[] Item_list={"PaperScore", "MusicCD","Vinyl","CDplayer","RecordPlayer","MP3",
            "Guitar","Bass","Mandolin","Flute","Harmonica","Hats","Shirts","Bandanas","PracticeAmps",
            "Cables","Strings"};


    public static void Build(){
        Cash_Register=0.0;
        daysPassed=0;
        PaperScore PS1=new PaperScore("PaperScore",5.0, true,0,5,"band1","album1");
        PaperScore PS2=new PaperScore("PaperScore",5.0, true,0,5,"band2","album2");
        PaperScore PS3=new PaperScore("PaperScore",5.0, true,0,5,"band3","album3");
        Inventory.add(PS1);
        Inventory.add(PS2);
        Inventory.add(PS3);
        Clerk Velma=new Clerk("Velma");
        Clerk Shaggy=new Clerk("Shaggy");
        staff_member.add(Velma);
        staff_member.add(Shaggy);
        for (int i=0; i<Item_list.length; i++){
            Inventory_stock.put(Item_list[i],3);
        }
        //System.out.println(Inventory_stock.keySet());

    }
    public static String[] get_ItemList(){
        return Item_list;
    }
    public void ManageStaff() {

    }

    public void Report(){
    }

    public static Integer check_stock(String name1){
        int count=0;
        for (int i=0; i< Inventory.size(); i++){
            if(Inventory.get(i).get_name()==name1){
                count++;
            }
        }
        return count;
    }

    public static void increment_daysPassed(){
        daysPassed+=1;

    public static void set_daysPassed(Integer days1){
        daysPassed=days1;

    }
    public static Integer get_daysPassed(){
        return daysPassed;
    }
    public static Double get_register(){
        return Cash_Register;
    }
    public static void add_register(Double value1){
        Cash_Register+=value1;
    }
    public static void subtract_register(Double value1){
        Cash_Register-=value1;
    }

    public static ArrayList<String> get_orders(){
        return Order_list;
    }
    public static Double get_moneyWithdrawn(){
        return money_withdrawn;
    }
    public static void add_moneyWithdrawn(Float value1){
        money_withdrawn+=value1;
    }
    public static void set_InventoryValue(){

    }
    //for DoInventory function for clerk
    public static Double get_InventoryValue(){
        Double total_value=0.0;
        for (int i=0; i< Inventory.size(); i++){
            total_value+=Inventory.get(i).get_purchasePrice();
        }
        return total_value;
    }
    public static Integer get_InventorySize(){
        return Inventory.size();
    }
    public static ArrayList<Items> get_Inventory(){
        return Inventory;
    }
    public static Items get_Item(Integer x){
        return Inventory.get(x);
    }
    public static void add_Inventory(Items item1){
        Inventory.add(item1);
    }
    public static void remove_Inventory(Integer i){
        Inventory.remove(i);
    }
    public static void set_moneyWithdrawn(Double money){
        money_withdrawn=money;
    }
    public void AddStaff(Staff worker) {
        staff_member.add(worker);
    }
    public void RemoveStaff(Staff worker) {
        staff_member.remove((staff_member.indexOf(worker)));
    }

    public void AddStaff(Staff worker) {
        staff_member.add(worker);
    }
    public void RemoveStaff(Staff worker) {
        staff_member.remove((staff_member.indexOf(worker)));
    }

}



