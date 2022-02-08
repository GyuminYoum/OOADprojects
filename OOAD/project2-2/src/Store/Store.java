package Store;

import Accessories_Items.Cables;
import Accessories_Items.PracticeAmps;
import Accessories_Items.Strings;
import Clothing_Items.Bandanas;
import Clothing_Items.Hats;
import Clothing_Items.Shirts;
import Instruments_Items.Stringed_Items.Bass;
import Instruments_Items.Stringed_Items.Guitar;
import Instruments_Items.Stringed_Items.Mandolin;
import Instruments_Items.wind_Items.Flute;
import Instruments_Items.wind_Items.Harmonica;
import Items.Items;
import Music_Items.CD;
import Music_Items.PaperScore;
import Music_Items.Vinyl;
import Players_Items.MP3;
import Players_Items.RecordPlayer;
import Staff.Clerk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Store {

    private static Double Cash_Register=0.0;
    //arraylist of Item objects to represent inventory
    private static ArrayList<Items> Inventory= new ArrayList<Items>();
    private static ArrayList<Clerk> Clerk_member=new ArrayList<Clerk>();
    private static HashMap<String, Integer> Inventory_stock= new HashMap<String, Integer>();
    private static Double Inventory_value=0.0;
    private static int daysPassed=1;
    private static Double total_salePrice=0.0;
    private static Double money_added=0.0;
    private static Clerk OnShift;
    private static ArrayList<Items> Order_list= new ArrayList<Items>();
    private static ArrayList<Items> Sold_list=new ArrayList<Items>();
    private static Double money_withdrawn=0.0;
    private static String[] Item_list={"PaperScore", "MusicCD","Vinyl","CDPlayer","RecordPlayer","MP3",
            "Guitar","Bass","Mandolin","Flute","Harmonica","Hats","Shirts","Bandanas","PracticeAmps",
            "Cables","Strings"};
    private static String[] staff_names={"Velma", "Shaggy"};



    public static void Build(){
        //initialize all the variables
        Cash_Register=0.0;
        daysPassed=1;
        //create all the objects
        PaperScore PS1=new PaperScore("PaperScore",5.0, true,0,5,"band1","album1");
        PaperScore PS2=new PaperScore("PaperScore",5.5, true,0,5,"band2","album2");
        PaperScore PS3=new PaperScore("PaperScore",3.0, true,0,5,"band3","album3");
        CD CD1=new CD("MusicCD",4.0, true,0,5,"band1","album1");
        CD CD2=new CD("MusicCD",4.1, true,0,5,"band2","album2");
        CD CD3=new CD("MusicCD",4.2, true,0,5,"band3","album3");
        Vinyl Vinyl1=new Vinyl("Vinyl",5.0, true,0,5,"band1","album1");
        Vinyl Vinyl2=new Vinyl("Vinyl",6.0, true,0,5,"band2","album2");
        Vinyl Vinyl3=new Vinyl("Vinyl",7.0, true,0,5,"band3","album3");
        Players_Items.CD CDP1 =new Players_Items.CD("CDPlayer",12.0, true,0,5);
        Players_Items.CD CDP2 =new Players_Items.CD("CDPlayer",24.0, true,0,5);
        Players_Items.CD CDP3 =new Players_Items.CD("CDPlayer",36.0, true,0,5);
        RecordPlayer RP1=new RecordPlayer("RecordPlayer",33.0, true,0,5);
        RecordPlayer RP2=new RecordPlayer("RecordPlayer",22.0, true,0,5);
        RecordPlayer RP3=new RecordPlayer("RecordPlayer",11.0, true,0,5);
        MP3 MP31=new MP3("MP3",5.0, true,0,5);
        MP3 MP32=new MP3("MP3",25.0, true,0,5);
        MP3 MP33=new MP3("MP3",45.0, true,0,5);
        Guitar Guitar1=new Guitar("Guitar",27.0, true,0,5,true);
        Guitar Guitar2=new Guitar("Guitar",17.0, true,0,5,false);
        Guitar Guitar3=new Guitar("Guitar",37.0, true,0,5,true);
        Bass Bass1=new Bass("Bass",22.0, true,0,5,true);
        Bass Bass2=new Bass("Bass",23.0, true,0,5,false);
        Bass Bass3=new Bass("Bass",24.0, true,0,5,true);
        Mandolin Mandolin1=new Mandolin("Mandolin",37.0, true,0,5,true);
        Mandolin Mandolin2=new Mandolin("Mandolin",35.0, true,0,5,false);
        Mandolin Mandolin3=new Mandolin("Mandolin",33.0, true,0,5,true);
        Flute Flute1=new Flute("Flute",45.0, true,0,5,"Standard");
        Flute Flute2=new Flute("Flute",46.0, true,0,5,"Piccolo");
        Flute Flute3=new Flute("Flute",48.0, true,0,5,"Plastic");
        Harmonica Harmonica1=new Harmonica("Harmonica",18.0, true,0,5,"A");
        Harmonica Harmonica2=new Harmonica("Harmonica",19.0, true,0,5,"Bb");
        Harmonica Harmonica3=new Harmonica("Harmonica",20.0, true,0,5,"C");
        Hats Hats1=new Hats("Hats",15.0, true,0,5,5.0);
        Hats Hats2=new Hats("Hats",10.0, true,0,5,6.0);
        Hats Hats3=new Hats("Hats",5.0, true,0,5,7.0);
        Shirts Shirts1=new Shirts("Shirts",4.0, true,0,5,"S");
        Shirts Shirts2=new Shirts("Shirts",4.5, true,0,5,"M");
        Shirts Shirts3=new Shirts("Shirts",5.0, true,0,5,"L");
        Bandanas Bandanas1=new Bandanas("Bandanas",3.0, true,0,5);
        Bandanas Bandanas2=new Bandanas("Bandanas",6.0, true,0,5);
        Bandanas Bandanas3=new Bandanas("Bandanas",9.0, true,0,5);
        PracticeAmps PAMPS1=new PracticeAmps("PracticeAmps",5.0, true,0,5,30);
        PracticeAmps PAMPS2=new PracticeAmps("PracticeAmps",15.0, true,0,5,60);
        PracticeAmps PAMPS3=new PracticeAmps("PracticeAmps",25.0, true,0,5,90);
        Cables Cables1=new Cables("Cables",5.0, true,0,5,30.0);
        Cables Cables2=new Cables("Cables",10.0, true,0,5,60.0);
        Cables Cables3=new Cables("Cables",15.0, true,0,5,90.0);
        Strings Strings1=new Strings("Strings",3.5, true,0,5,"Violin");
        Strings Strings2=new Strings("Strings",7.0, true,0,5,"Cello");
        Strings Strings3=new Strings("Strings",11.0, true,0,5,"Guitar");

        //add all the created objects to inventory
        Inventory.add(PS1);
        Inventory.add(PS2);
        Inventory.add(PS3);
        Inventory.add(CD1);
        Inventory.add(CD2);
        Inventory.add(CD3);
        Inventory.add(Vinyl1);
        Inventory.add(Vinyl2);
        Inventory.add(Vinyl3);
        Inventory.add(CDP1);
        Inventory.add(CDP2);
        Inventory.add(CDP3);
        Inventory.add(RP1);
        Inventory.add(RP2);
        Inventory.add(RP3);
        Inventory.add(MP31);
        Inventory.add(MP32);
        Inventory.add(MP33);
        Inventory.add(Guitar1);
        Inventory.add(Guitar2);
        Inventory.add(Guitar3);
        Inventory.add(Bass1);
        Inventory.add(Bass2);
        Inventory.add(Bass3);
        Inventory.add(Mandolin1);
        Inventory.add(Mandolin2);
        Inventory.add(Mandolin3);
        Inventory.add(Flute1);
        Inventory.add(Flute2);
        Inventory.add(Flute3);
        Inventory.add(Harmonica1);
        Inventory.add(Harmonica2);
        Inventory.add(Harmonica3);
        Inventory.add(Hats1);
        Inventory.add(Hats2);
        Inventory.add(Hats3);
        Inventory.add(Shirts1);
        Inventory.add(Shirts2);
        Inventory.add(Shirts3);
        Inventory.add(Bandanas1);
        Inventory.add(Bandanas2);
        Inventory.add(Bandanas3);
        Inventory.add(PAMPS1);
        Inventory.add(PAMPS2);
        Inventory.add(PAMPS3);
        Inventory.add(Cables1);
        Inventory.add(Cables2);
        Inventory.add(Cables3);
        Inventory.add(Strings1);
        Inventory.add(Strings2);
        Inventory.add(Strings3);

        //
        Clerk Velma=new Clerk("Velma");
        Clerk Shaggy=new Clerk("Shaggy");
        Clerk_member.add(Velma);
        Clerk_member.add(Shaggy);
        for (int i=0; i<Item_list.length; i++){
            Inventory_stock.put(Item_list[i],3);
        }
        //System.out.println(Inventory_stock.keySet());
    }

 /*
    public static void test1(){
        Clerk Velma=new Clerk("Velma");
        Clerk Shaggy=new Clerk("Shaggy");
        Clerk_member.add(Velma);
        Clerk_member.add(Shaggy);
        for (int i=0; i<Item_list.length; i++){
            Inventory_stock.put(Item_list[i],3);
        }
        PracticeAmps PAMPS1=new PracticeAmps("PracticeAmps",5.0, true,4,5,30);
        Store.add_orders(PAMPS1);

        //test for Arrive At Store
    }

  */
    public static String[] get_ItemList(){
        return Item_list;
    }
    //makes sure no clerk works more than 3 days in a row
    public static void pickOnShift() {
        int days=0;
        Random rng=new Random();
        int roll=rng.nextInt(Clerk_member.size());
        days=Clerk_member.get(roll).get_daysWorked();
        if(get_daysPassed()%7==0){
            Store.increment_daysPassed();
            for (int i=0; i< Clerk_member.size(); i++){
                Clerk_member.get(i).set_daysWorked(0);
            }
            System.out.println("Store is closed on Sunday.");
        }
        else{
            if(days>3){
                if(roll==0){
                    OnShift=Clerk_member.get(1);
                    Clerk_member.get(1).increment_daysWorked();
                    Clerk_member.get(roll).set_daysWorked(0);
                }
                else{
                    OnShift=Clerk_member.get(0);
                    Clerk_member.get(1).increment_daysWorked();
                    Clerk_member.get(roll).set_daysWorked(0);
                }
            }
            else{
                OnShift=Clerk_member.get(roll);
                Clerk_member.get(roll).increment_daysWorked();
            }
            //return OnShift;
        }
    }
    public static Clerk get_OnShift(){
        return OnShift;
    }
    public static void set_days(Integer int1){
        daysPassed=int1;
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
    }
    public static Integer get_daysPassed(){
        return daysPassed;
    }
    public static Double get_Register(){
        return Cash_Register;
    }
    public static void add_Register(Double value1){
        Cash_Register+=value1;
    }
    public static void set_Register(Double value1){
        Cash_Register=value1;
    }

    public static ArrayList<Items> get_orders(){
        return Order_list;
    }
    public static void add_orders(Items item1){
        Order_list.add(item1);
    }
    public static Integer get_orderSize(){
        return Order_list.size();
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
    public static Double get_soldValue(){
        Double total_value=0.0;
        for (int i=0; i< Sold_list.size(); i++){
            total_value+=Sold_list.get(i).get_salePrice();
        }
        return total_value;
    }
    public static Integer get_soldListSize(){
        return Sold_list.size();
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
    public static Double get_moneyWithdrawn(){
        return money_withdrawn;
    }
    public static void add_moneyWithdrawn(Float value1){
        money_withdrawn+=value1;
    }
    /*
    //don't need it as of now
    public void AddStaff(Staff worker) {
        staf_member.add(worker);
    }
    public void RemoveStaff(Staff worker) {
        staff_member.remove((staff_member.indexOf(worker)));
    }

     */
    public static void Report(){
        System.out.println("In the inventory, there remains: ");
        String item_name;
        Double price;
        for(int i=0; i<get_InventorySize(); i++){
            item_name=Inventory.get(i).get_name();
            System.out.print(item_name+" ");
        }
        System.out.println("with total value of "+ get_InventoryValue());
        for(int i=0; i<get_soldListSize(); i++){
            item_name=Sold_list.get(i).get_name();
            System.out.println(item_name+" was sold on "+ Sold_list.get(i).get_daySold()+" at price of $"+Sold_list.get(i).get_salePrice());
        }
        System.out.println("with total Sale value of "+ get_soldValue());
        System.out.println("There is $"+ Cash_Register+" in the Cash Register.");
        System.out.println("Total of $"+get_moneyWithdrawn()+" was withdrawn from the bank.");
    }
    public static void Pay(Double amount){
        if(get_Register()>amount){
            set_Register(get_Register()-amount);
        }
        else{
            OnShift.GoToBank();
            set_Register(get_Register()-amount);
        }
    }

}



