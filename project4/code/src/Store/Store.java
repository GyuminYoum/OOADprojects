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
import Observer.Observer;
import Players_Items.CassettePlayer;
import Players_Items.MP3;
import Players_Items.RecordPlayer;
import Staff.Clerk;
import Staff.Electronic;
import Staff.Haphazard;
import Staff.Manual;

import Observer.logger;





import java.util.ArrayList;
import java.util.Random;
import Observer.Observer;
import Observer.Tracker;


//example of encapsulation
//most attributes are private so getters and setters are used to access them.
public class Store {

    public Random Rng = new Random();
    private double Cash_Register=0.0;
    //arraylist of Item objects to represent inventory
    private ArrayList<Items> Inventory= new ArrayList<Items>();
    //list of clerks
    private ArrayList<Clerk> Clerk_member;
    private double Inventory_value=0.0;
    private int daysPassed=1;
    private double total_salePrice=0.0;
    private double money_added=0.0;
    private Clerk OnShift;
    private String location;
    private ArrayList<Items> Order_list= new ArrayList<Items>();
    private ArrayList<Items> Sold_list=new ArrayList<Items>();
    private double money_withdrawn=0.0;
    private ArrayList<String>Item_list= new ArrayList<String>();
    private ArrayList<String> staff_names=new ArrayList<String>();
    private ArrayList<logger> logger_list=new ArrayList<logger>();
    private ArrayList<Tracker> tracker_list=new ArrayList<Tracker>();
    private ArrayList<Observer> Observer_list=new ArrayList<Observer>();
    private int duration;

    public Store(ArrayList<Clerk> clerks, String loc1, int dur1){
        Clerk_member=clerks;
        location=loc1;
        duration=dur1;
    }



    //setter to initialize the store
    //$0 balance in cash register, simulation starts from day 1
    //inventory is initialized with 3 of each item
    //Clerk_Member is initialized with clerk objects shaggy and velma
    public void Build(){
        //initialize all the variables
        this.Cash_Register=0.0;
        this.daysPassed=1;
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

        for (Items i:Inventory){
            i.set_Store(this);
        }
    }


    //Logger related functions
    public void registerLogger(logger O){
         logger_list.add(O);
     }
    public void removeLogger(logger O){
         logger_list.remove(O);
    }
    public void notifyLoggers(String str1){
        for (logger O:logger_list){
            O.update(str1);
        }
    }

    //Tracker related functions
    public void registerTracker(Tracker t) { tracker_list.add(t); }
    public void removeTracker(Tracker t) { tracker_list.remove(t); }
    public void notifyTrackers(String str1){
        for (Tracker t:tracker_list){
            t.update(str1);
        }
    }
    public void printTrackers() {
        for (Tracker t:tracker_list){
            t.display();
        }
    }

    public void addToItemList(String name1){
        if(Item_list.contains(name1)==false){
            Item_list.add(name1);
        }
    }

    //getter for Item_list
    public ArrayList<String> get_ItemList(){
        return Item_list;
    }

    //find index of the sick person
    public int findSickIndex(ArrayList<Clerk>list){
        for (int i=0; i<list.size(); i++){
            if(list.get(i).get_sick()==true){
                return i;
            }
        }
        return -1;
    }
    public ArrayList<Clerk> get_ClerkMember(){
        return Clerk_member;
    }
    //resets all the worker's workingAt location to null
    public void reset_ClerkStore(){
        for(Clerk i:Clerk_member){
            i.set_workingAt(null);
        }
    }

    public String get_location(){
        return location;
    }

    //picks sick person.
    //if the person was sick already then find someone else to get sick
    public void pickSick(){
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
    public Clerk pickOnShift() {
        Random rng=new Random();
        int days=0;
        //roll for whos on shift today
        int roll=0;
        int anyonesick=0;
        int sickIndex=-1;
        Clerk picked=null;
        String content;
        //days=picked.get_daysWorked();
        //make a deep copy of Clerk_member

        if(get_daysPassed()%7==0){
            this.increment_daysPassed();
            for (int i=0; i< Clerk_member.size(); i++){
                Clerk_member.get(i).set_daysWorked(0);
                Clerk_member.get(i).set_sick(false);
            }
            //System.out.println("Store is closed on Sunday.");
            content="Store is closed on Sunday.";
            this.notifyLoggers(content);
            return null;
        }
        else{
            anyonesick=rng.nextInt(100);
            if(anyonesick<10){
                pickSick();
                //System.out.println(Clerk_member.get(findSickIndex(Clerk_member)).get_name()+" is sick on day "+get_daysPassed());
                content=Clerk_member.get(findSickIndex(Clerk_member)).get_name()+" is sick on day "+get_daysPassed();
                this.notifyLoggers(content);
            }
            else{
                sickIndex=findSickIndex(Clerk_member);
                if(sickIndex!=-1){
                    Clerk_member.get(sickIndex).set_sick(false);
                }
            }

            roll=rng.nextInt(Clerk_member.size());
            picked=Clerk_member.get(roll);
            while(picked.get_sick()==true || picked.get_daysWorked()>3 || picked.get_workingAt()!=null){
                roll=rng.nextInt(Clerk_member.size());
                picked=Clerk_member.get(roll);
            }
            OnShift=Clerk_member.get(roll);
            OnShift.set_workingAt(this);
        }
        return OnShift;
    }
    //getter for ONShift
    public Clerk get_OnShift(){
        return OnShift;
    }

    //setter for daysPassed for testing
    public void set_days(int int1){
        daysPassed=int1;
    }

    //iterate through and check stock for all
    public int check_stock(String name1){
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

    public void increment_daysPassed() {
        daysPassed += 1;
    }
    //setter
    public void set_daysPassed(int days1){
        daysPassed=days1;
    }
    //getter
    public int get_daysPassed(){
        return daysPassed;
    }
    public int get_duration(){
        return duration;
    }
    public void set_duration(int day1){
        duration=day1;
    }
    public ArrayList<Items> get_Inventory(){
        return Inventory;
    }




    //////////////////////////
    //functions for register//
    //////////////////////////

    //getter
    public Double get_Register(){
        return Math.floor(Cash_Register * 100) /100;

    }
    //adds to register
    public void add_Register(double value1){
        Cash_Register+=value1;
    }
    //setter
    public void set_Register(double value1){
        Cash_Register=value1;
    }
    public boolean already_ordered(String name1){
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
    public ArrayList<Items> get_orders(){
        return Order_list;
    }
    //adds to order_list
    public void add_orders(Items item1){
        Order_list.add(item1);
    }
    //returns size of order list
    public int get_orderSize(){
        return Order_list.size();
    }


  
    public double get_moneyWithdrawn(){
        return money_withdrawn;
    }

    public void add_moneyWithdrawn(float value1){
        money_withdrawn+=value1;
    }

    //for DoInventory function for clerk
    public double get_InventoryValue(){
        double total_value=0.0;
        for (int i=0; i< Inventory.size(); i++){
            total_value+=Inventory.get(i).get_purchasePrice();
        }
        return Math.floor(total_value * 100) / 100;
    }

    //returns invnetory size
    public int get_InventorySize(){
        return Inventory.size();
    }

    ///////////////////////////////////
    //functions related to sold list//
    //////////////////////////////////

   //totaling up values in sold list
    public double get_soldValue(){
        double total_value=0.0;
        for (int i=0; i< Sold_list.size(); i++){
            total_value+=Sold_list.get(i).get_salePrice();
        }
        return Math.floor(total_value * 100) / 100;
    }
    //get size of sold list
    public int get_soldListSize(){
        return Sold_list.size();
    }
    //adding to sold list
    public void add_soldItem(Items item1) {Sold_list.add(item1);}

    /*public static ArrayList<String> get_Inventory() {
        ArrayList<String> inv = new ArrayList<String>();
        for(Items i : Inventory) {
            inv.add(i.get_name());
        }
        return inv;
    }*/


    //Item getters
    public Items get_Item(int x){
        return Inventory.get(x);
    }

    //inventory getters setters
    public void add_Inventory(Items item1){
        Inventory.add(item1);
    }
    //removing from inventory arraylist
    public void remove_Inventory(int i){
        Inventory.remove(i);
    }
    /*public static void remove_Inventory_buy(String i){
        Inventory.remove(i);
    }*/


    //moneyWithdrawn getters and setters
    public void set_moneyWithdrawn(double money){
        money_withdrawn=money;
    }

    //report function for everthing at the end.
    public void Report(){
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
        System.out.println("There is $"+ get_Register() +" in the Cash Register.");
        System.out.println("Total of $"+get_moneyWithdrawn()+" was withdrawn from the bank.");
    }
    public void Pay(double amount) {
        if (get_Register() > amount) {
            set_Register(get_Register() - amount);
        } else {
            OnShift.GoToBank();
            set_Register(get_Register() - amount);
        }
    }
    public void Sell(String item, int quantity) {
        int i = 0;
        int j = 0;
        String content;
        //for number of items that get bought
        while (i < this.get_InventorySize() && j < quantity) {
            if (item.equals(this.get_Item(i).get_name())) {
                //set daySold and salePrice, add to sold list and add money to register
                this.get_Item(i).set_daySold(this.get_daysPassed());
                this.get_Item(i).set_salePrice(this.get_Item(i).get_listPrice());
                this.add_soldItem(this.get_Item(i));
                this.add_Register(this.get_Item(i).get_salePrice());
                //System.out.printf("Buyer also purchased " + Store.get_Item(i).get_name() + " for $" + Store.get_Item(i).get_salePrice() + ".\n");
                content="Buyer also purchased " + this.get_Item(i).get_name() + " for $" + this.get_Item(i).get_salePrice() + ".";
                this.notifyLoggers(content);
                this.notifyTrackers("sold");
                //remove item from inventory
                this.remove_Inventory(i);
                j++;
            } else {
                i++;
            }
        }
    }
}




