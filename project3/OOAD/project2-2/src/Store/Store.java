package Store;

import Accessories_Items.Cables;
import Accessories_Items.GigBag;
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
import Instruments_Items.wind_Items.Saxophone;
import Items.Items;
import Music_Items.CD;
import Music_Items.Cassette;
import Music_Items.PaperScore;
import Music_Items.Vinyl;
import Players_Items.CassettePlayer;
import Players_Items.MP3;
import Players_Items.RecordPlayer;
import Staff.Clerk;


import java.util.ArrayList;
import java.util.Random;

//example of encapsulation
//most attributes are private so getters and setters are used to access them.
public class Store {

    public static Random Rng = new Random();

    private static double Cash_Register=0.0;
    //arraylist of Item objects to represent inventory
    private static ArrayList<Items> Inventory= new ArrayList<Items>();
    //list of clerks
    private static ArrayList<Clerk> Clerk_member=new ArrayList<Clerk>();
    //private static HashMap<String, int> Inventory_stock= new HashMap<String, int>();
    private static double Inventory_value=0.0;
    private static int daysPassed=1;
    private static double total_salePrice=0.0;
    private static double money_added=0.0;
    private static Clerk OnShift;
    private static ArrayList<Items> Order_list= new ArrayList<Items>();
    private static ArrayList<Items> Sold_list=new ArrayList<Items>();
    private static double money_withdrawn=0.0;
    private static ArrayList<String>Item_list= new ArrayList<String>();
    private static ArrayList<String> staff_names=new ArrayList<String>();


    //setter to initialize the store
    //$0 balance in cash register, simulation starts from day 1
    //inventory is initialized with 3 of each item
    //Clerk_Member is initialized with clerk objects shaggy and velma
    public static void Build(){
        //initialize all the variables
        Cash_Register=0.0;
        daysPassed=1;
        //create all the objects
        PaperScore PS1=new PaperScore("PaperScore",5.0, true,0,5,"band1","album1");
        PaperScore PS2=new PaperScore("PaperScore",5.5, true,0,5,"band2","album2");
        PaperScore PS3=new PaperScore("PaperScore",3.0, true,0,5,"band3","album3");
        addToItemList(PS1.get_name());
        CD CD1=new CD("MusicCD",4.0, true,0,5,"band1","album1");
        CD CD2=new CD("MusicCD",4.1, true,0,5,"band2","album2");
        CD CD3=new CD("MusicCD",4.2, true,0,5,"band3","album3");
        addToItemList(CD1.get_name());
        Vinyl Vinyl1=new Vinyl("Vinyl",5.0, true,0,5,"band1","album1");
        Vinyl Vinyl2=new Vinyl("Vinyl",6.0, true,0,5,"band2","album2");
        Vinyl Vinyl3=new Vinyl("Vinyl",7.0, true,0,5,"band3","album3");
        addToItemList(Vinyl1.get_name());
        Players_Items.CD CDP1 =new Players_Items.CD("CDPlayer",12.0, true,0,5);
        Players_Items.CD CDP2 =new Players_Items.CD("CDPlayer",24.0, true,0,5);
        Players_Items.CD CDP3 =new Players_Items.CD("CDPlayer",36.0, true,0,5);
        addToItemList(CDP1.get_name());
        RecordPlayer RP1=new RecordPlayer("RecordPlayer",33.0, true,0,5);
        RecordPlayer RP2=new RecordPlayer("RecordPlayer",22.0, true,0,5);
        RecordPlayer RP3=new RecordPlayer("RecordPlayer",11.0, true,0,5);
        addToItemList(RP1.get_name());
        MP3 MP31=new MP3("MP3",5.0, true,0,5);
        MP3 MP32=new MP3("MP3",25.0, true,0,5);
        MP3 MP33=new MP3("MP3",45.0, true,0,5);
        addToItemList(MP31.get_name());
        Guitar Guitar1=new Guitar("Guitar",27.0, true,0,5,true);
        Guitar Guitar2=new Guitar("Guitar",17.0, true,0,5,false);
        Guitar Guitar3=new Guitar("Guitar",37.0, true,0,5,true);
        addToItemList(Guitar1.get_name());
        Bass Bass1=new Bass("Bass",22.0, true,0,5,true);
        Bass Bass2=new Bass("Bass",23.0, true,0,5,false);
        Bass Bass3=new Bass("Bass",24.0, true,0,5,true);
        addToItemList(Bass1.get_name());
        Mandolin Mandolin1=new Mandolin("Mandolin",37.0, true,0,5,true);
        Mandolin Mandolin2=new Mandolin("Mandolin",35.0, true,0,5,false);
        Mandolin Mandolin3=new Mandolin("Mandolin",33.0, true,0,5,true);
        addToItemList(Mandolin1.get_name());
        Flute Flute1=new Flute("Flute",45.0, true,0,5,"Standard");
        Flute Flute2=new Flute("Flute",46.0, true,0,5,"Piccolo");
        Flute Flute3=new Flute("Flute",48.0, true,0,5,"Plastic");
        addToItemList(Flute1.get_name());
        Harmonica Harmonica1=new Harmonica("Harmonica",18.0, true,0,5,"A");
        Harmonica Harmonica2=new Harmonica("Harmonica",19.0, true,0,5,"Bb");
        Harmonica Harmonica3=new Harmonica("Harmonica",20.0, true,0,5,"C");
        addToItemList(Harmonica1.get_name());
        Hats Hats1=new Hats("Hats",15.0, true,0,5,5.0);
        Hats Hats2=new Hats("Hats",10.0, true,0,5,6.0);
        Hats Hats3=new Hats("Hats",5.0, true,0,5,7.0);
        addToItemList(Hats1.get_name());
        Shirts Shirts1=new Shirts("Shirts",4.0, true,0,5,"S");
        Shirts Shirts2=new Shirts("Shirts",4.5, true,0,5,"M");
        Shirts Shirts3=new Shirts("Shirts",5.0, true,0,5,"L");
        addToItemList(Shirts1.get_name());
        Bandanas Bandanas1=new Bandanas("Bandanas",3.0, true,0,5);
        Bandanas Bandanas2=new Bandanas("Bandanas",6.0, true,0,5);
        Bandanas Bandanas3=new Bandanas("Bandanas",9.0, true,0,5);
        addToItemList(Bandanas1.get_name());
        PracticeAmps PAMPS1=new PracticeAmps("PracticeAmps",5.0, true,0,5,30);
        PracticeAmps PAMPS2=new PracticeAmps("PracticeAmps",15.0, true,0,5,60);
        PracticeAmps PAMPS3=new PracticeAmps("PracticeAmps",25.0, true,0,5,90);
        addToItemList(PAMPS1.get_name());
        Cables Cables1=new Cables("Cables",5.0, true,0,5,30.0);
        Cables Cables2=new Cables("Cables",10.0, true,0,5,60.0);
        Cables Cables3=new Cables("Cables",15.0, true,0,5,90.0);
        addToItemList(Cables1.get_name());
        Strings Strings1=new Strings("Strings",3.5, true,0,5,"Violin");
        Strings Strings2=new Strings("Strings",7.0, true,0,5,"Cello");
        Strings Strings3=new Strings("Strings",11.0, true,0,5,"Guitar");
        addToItemList(Strings1.get_name());
        Saxophone Saxophone1=new Saxophone("Saxophone",45.0, true,0,5,"alto");
        Saxophone Saxophone2=new Saxophone("Saxophone",42.0, true,0,5,"tenor");
        Saxophone Saxophone3=new Saxophone("Saxophone",48.0, true,0,5,"bass");
        addToItemList(Saxophone1.get_name());
        Cassette Cassette1 =new Cassette("Cassette",3.0, true,0,5,"band1","album1");
        Cassette Cassette2 =new Cassette("Cassette",4.1, true,0,5,"band2","album2");
        Cassette Cassette3 =new Cassette("Cassette",2.2, true,0,5,"band3","album3");
        addToItemList(Cassette1.get_name());
        CassettePlayer CassettePlayer1=new CassettePlayer("CassettePlayer",13.0, true,0,5);
        CassettePlayer CassettePlayer2=new CassettePlayer("CassettePlayer",12.0, true,0,5);
        CassettePlayer CassettePlayer3=new CassettePlayer("CassettePlayer",21.0, true,0,5);
        addToItemList(CassettePlayer1.get_name());
        GigBag GigBag1=new GigBag("GigBag",5.5, true,0,5);
        GigBag GigBag2=new GigBag("GigBag",6.0, true,0,5);
        GigBag GigBag3=new GigBag("GigBag",6.5, true,0,5);
        addToItemList(GigBag1.get_name());

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
        Inventory.add(Saxophone1);
        Inventory.add(Saxophone2);
        Inventory.add(Saxophone3);
        Inventory.add(Cassette1);
        Inventory.add(Cassette2);
        Inventory.add(Cassette3);
        Inventory.add(CassettePlayer1);
        Inventory.add(CassettePlayer2);
        Inventory.add(CassettePlayer3);
        Inventory.add(GigBag1);
        Inventory.add(GigBag2);
        Inventory.add(GigBag3);

        //
        Clerk Velma=new Clerk("Velma");
        Clerk Shaggy=new Clerk("Shaggy");
        Clerk Daphne=new Clerk("Daphne");
        Clerk_member.add(Velma);
        Clerk_member.add(Shaggy);
        Clerk_member.add(Daphne);
        addToStaffNames(Velma.get_name());
        addToStaffNames(Shaggy.get_name());
        addToStaffNames(Daphne.get_name());
        /*
        for (int i=0; i<Item_list.length; i++){
            Inventory_stock.put(Item_list[i],3);
        }

         */
        //System.out.println(Inventory_stock.keySet());
    }

 /*
 //testing builds
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
    public static void addToItemList(String name1){
        if(Item_list.contains(name1)==false){
            Item_list.add(name1);
        }
    }
    public static void addToStaffNames(String name1){
        if (staff_names.contains(name1)==false){
            staff_names.add(name1);
        }
    }
    //getter for Item_list
    public static ArrayList<String> get_ItemList(){
        return Item_list;
    }

    //find index of the sick person
    public static int findSickIndex(ArrayList<Clerk>list){
        for (int i=0; i<list.size(); i++){
            if(list.get(i).get_sick()==true){
                return i;
            }
        }
        return -1;
    }
    public static ArrayList<Clerk> get_ClerkMember(){
        return Clerk_member;
    }

    //picks sick person.
    //if the person was sick already then find someone else to get sick
    public static void pickSick(){
        Random rng=new Random();
        int sickIndex;
        int roll=-1;

        sickIndex=findSickIndex(Clerk_member);
        //no ones sick
        if (sickIndex==-1){
            roll=rng.nextInt(Clerk_member.size());
            Clerk_member.get(roll).set_sick(true);
        }
        else{
            Clerk_member.get(sickIndex).set_sick(false);
            roll=sickIndex;
            //make sure the same person can't get sick twice
            while(roll==sickIndex){
                roll=rng.nextInt(Clerk_member.size());
            }
            Clerk_member.get(roll).set_sick(true);
        }
    }
    //makes sure no clerk works more than 3 days in a row
    //randomly rolls between clerk member and picks whos working
    //if a person is sick, it finds substitute
    //every 7th day, no one works, days passed is incremented.
    public static void pickOnShift() {
        Random rng=new Random();
        int days=0;
        //roll for whos on shift today
        int roll=0;
        int anyonesick=0;
        int sickIndex=-1;
        Clerk picked=null;
        //days=picked.get_daysWorked();
        //make a deep copy of Clerk_member


        if(get_daysPassed()%7==0){
            Store.increment_daysPassed();
            for (int i=0; i< Clerk_member.size(); i++){
                Clerk_member.get(i).set_daysWorked(0);
                Clerk_member.get(i).set_sick(false);
            }
            System.out.println("Store is closed on Sunday.");
            /*
            for (int i=0; i< Clerk_member.size(); i++){
                System.out.println(Clerk_member.get(i).get_name()+" "+Clerk_member.get(i).get_sick()+" "+Clerk_member.get(i).get_daysWorked()+" days");
            }

             */
        }
        else{
            anyonesick=rng.nextInt(100);
            if(anyonesick<10){
                pickSick();
                System.out.println(Clerk_member.get(findSickIndex(Clerk_member)).get_name()+" is sick on day "+get_daysPassed());
            }
            else{
                sickIndex=findSickIndex(Clerk_member);
                if(sickIndex!=-1){
                    Clerk_member.get(sickIndex).set_sick(false);
                }
            }
            roll=rng.nextInt(Clerk_member.size());
            picked=Clerk_member.get(roll);
            while(picked.get_sick()==true && picked.get_daysWorked()>3){
                roll=rng.nextInt(Clerk_member.size());
                picked=Clerk_member.get(roll);
            }
            OnShift=Clerk_member.get(roll);
            //Clerk_member.get(roll).increment_daysWorked();
            /*
            for (int i=0; i< Clerk_member.size();i++){
                if(i!=roll){
                    Clerk_member.get(i).set_daysWorked(0);
                }
            }

             */
        }

    }
    //getter for ONShift
    public static Clerk get_OnShift(){
        return OnShift;
    }

    //setter for daysPassed for testing
    public static void set_days(int int1){
        daysPassed=int1;
    }

    //iterate through and check stock for all
    public static int check_stock(String name1){
        int count=0;
        for (int i=0; i< Inventory.size(); i++){
            if(Inventory.get(i).get_name()==name1){
                count++;
            }
        }
        return count;
    }

    ////////////////////////////
    //functions for daysPassed//
    ///////////////////////////

    public static void increment_daysPassed() {
        daysPassed += 1;
    }
    //setter
    public static void set_daysPassed(int days1){
        daysPassed=days1;
    }
    //getter
    public static int get_daysPassed(){
        return daysPassed;
    }




    //////////////////////////
    //functions for register//
    //////////////////////////

    //getter
    public static Double get_Register(){
        return Math.floor(Cash_Register * 100) /100;

    }
    //adds to register
    public static void add_Register(double value1){
        Cash_Register+=value1;
    }
    //setter
    public static void set_Register(double value1){
        Cash_Register=value1;
    }
    public static boolean already_ordered(String name1){
        boolean status=false;
        for (int i=0; i<Order_list.size(); i++){
            if(Order_list.get(i).get_name()==name1){
                status=true;
            }
        }
        return status;
    }

    ////////////////////////////
    //functions for order_list//
    ///////////////////////////
    //getter for order-list
    public static ArrayList<Items> get_orders(){
        return Order_list;
    }
    //adds to order_list
    public static void add_orders(Items item1){
        Order_list.add(item1);
    }
    //returns size of order list
    public static int get_orderSize(){
        return Order_list.size();
    }


  
    public static double get_moneyWithdrawn(){
        return money_withdrawn;
    }

    public static void add_moneyWithdrawn(float value1){
        money_withdrawn+=value1;
    }

    //for DoInventory function for clerk
    public static double get_InventoryValue(){
        double total_value=0.0;
        for (int i=0; i< Inventory.size(); i++){
            total_value+=Inventory.get(i).get_purchasePrice();
        }
        return Math.floor(total_value * 100) / 100;
    }

    //returns invnetory size
    public static int get_InventorySize(){
        return Inventory.size();
    }

    ///////////////////////////////////
    //functions related to sold list//
    //////////////////////////////////

   //totaling up values in sold list
    public static double get_soldValue(){
        double total_value=0.0;
        for (int i=0; i< Sold_list.size(); i++){
            total_value+=Sold_list.get(i).get_salePrice();
        }
        return Math.floor(total_value * 100) / 100;
    }
    //get size of sold list
    public static int get_soldListSize(){
        return Sold_list.size();
    }
    //adding to sold list
    public static void add_soldItem(Items item1) {Sold_list.add(item1);}

    /*public static ArrayList<String> get_Inventory() {
        ArrayList<String> inv = new ArrayList<String>();
        for(Items i : Inventory) {
            inv.add(i.get_name());
        }
        return inv;
    }*/


    //Item getters
    public static Items get_Item(int x){
        return Inventory.get(x);
    }

    //inventory getters setters
    public static void add_Inventory(Items item1){
        Inventory.add(item1);
    }
    //removing from inventory arraylist
    public static void remove_Inventory(int i){
        Inventory.remove(i);
    }
    /*public static void remove_Inventory_buy(String i){
        Inventory.remove(i);
    }*/


    //moneyWithdrawn getters and setters
    public static void set_moneyWithdrawn(double money){
        money_withdrawn=money;
    }
    /*public static void add_moneyWithdrawn(Float value1){
        money_withdrawn+=value1;
    }*/
    /*
    //don't need it as of now
    public void AddStaff(Staff worker) {
        staf_member.add(worker);
    }
    public void RemoveStaff(Staff worker) {
        staff_member.remove((staff_member.indexOf(worker)));
    }

     */
    //report function for everthing at the end.
    public static void Report(){
        System.out.println("In the inventory, there remains: ");
        String item_name;
        double price;
        for(int i=0; i<get_InventorySize(); i++){
            item_name=Inventory.get(i).get_name();
            System.out.print(item_name+" ");

        }
        System.out.println(" ");
        System.out.println("with total value of $"+ get_InventoryValue());
        for(int i=0; i<get_soldListSize(); i++){
            item_name=Sold_list.get(i).get_name();
            System.out.println(item_name+" was sold on Day "+ Sold_list.get(i).get_daySold()+" at a price of $"+Sold_list.get(i).get_salePrice());
        }
        System.out.println("with total Sale value of $"+ get_soldValue());
        System.out.println("There is $"+ Store.get_Register() +" in the Cash Register.");
        System.out.println("Total of $"+get_moneyWithdrawn()+" was withdrawn from the bank.");
    }
    public static void Pay(double amount){
        if(get_Register()>amount){
            set_Register(get_Register()-amount);
        }
        else{
            OnShift.GoToBank();
            set_Register(get_Register()-amount);
        }
        /*
    public void AddStaff(Staff worker) {
        staff_member.add(worker);
    }
    public void RemoveStaff(Staff worker) {
        staff_member.remove((staff_member.indexOf(worker)));
    }
         */
    }
}




