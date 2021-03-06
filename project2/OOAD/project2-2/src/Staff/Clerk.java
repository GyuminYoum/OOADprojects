package Staff;

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
import Music_Items.CD;
import Music_Items.PaperScore;
import Music_Items.Vinyl;
import Players_Items.MP3;
import Players_Items.RecordPlayer;
import Store.Customer;
import Store.Store;

import java.util.ArrayList;
import java.util.Random;

public class Clerk extends Staff{
    
    private int days_worked;

    //default constructor for clerk
    public Clerk(String name1) {
        super(name1);
        days_worked=0;
    }

    //getters and setters for attribute days_worked
    public void set_daysWorked(int days1){
        days_worked=days1;
    }
    public int get_daysWorked(){
        return days_worked;
    }
    public void increment_daysWorked(){
        days_worked+=1;
    }

    //Arriveatthe store function
    //args:none
    //announces appropriate msg including name and day
    //receives order that has dayArrived value=day and announces it
    //returns N/A

    public void ArriveAtStore() {
        String name1=this.get_name();
        int Curr_day=Store.get_daysPassed();
        System.out.println(name1+ " arrives at the store on Day "+ Store.get_daysPassed());
        if(Curr_day%7!=0){
            for (int i=0; i<Store.get_orderSize(); i++){
                if(Curr_day>=Store.get_orders().get(i).get_dayArrived()){
                    //move to inventory
                    Store.add_Inventory(Store.get_orders().get(i));
                    System.out.println(name1+" received "+Store.get_orders().get(i).get_name()+" on day "+Curr_day);
                    Store.get_orders().remove(i);
                }
            }
            //System.out.println(name1);
        }
    }

    //CheckRegister function
    //args:none
    //checks to see how much money is in Cash_register attribute of Store
    //if less than 75, invokes gotobank function
    //else announces how much is in cash_Register
    //returns N/A
    public void CheckRegister() {
        double register_cash=Store.get_Register();
        if (register_cash<75){
            this.GoToBank();
        }
        else{
            System.out.println("There is $"+Store.get_Register()+" in the register");
        }
    }

    //GoToBank function
    //args:none
    //announces appropriate message
    //increases value in Cash_Register by 1000.
    //increases moneywithdrawn attribute of Store by 1000.
    //returns N/A
    public void GoToBank() {
        System.out.println(this.get_name()+" went to the bank and put $1000 in the register.");
        double current_val=Store.get_moneyWithdrawn();
        Store.set_moneyWithdrawn(current_val+1000);
        Store.add_Register(1000.0);
    }

    //DoInventory function
    //args:none
    //totals up the inventory worth and announces it.
    //checks to see if any of the item is at 0 stock
    //orders if its at 0  stock.
    //returns N/A
    public void DoInventory() {
        double inven_value=Store.get_InventoryValue();
        ArrayList<String> item_names=Store.get_ItemList();
        //testrun
        //String[] item_names={"PaperScore","Soccer"};
        System.out.println("All the items in the inventory are worth total of "+ inven_value);
        for(int i=0; i<item_names.size(); i++){
            if(Store.check_stock(item_names.get(i))==0){
                this.PlaceAnOrder(item_names.get(i));
            }
        }
    }

    //placeanorder function
    //args:String
    //places 3 orders of an item with the argument name if it hasn't been ordered yet.
    //all the attributes of the item are randomized and appropriate announcement message is printed
    //item is then added to the order_list attribute and Cash_Register balance is decreased by purchasePrice
    //returns N/A
    public void PlaceAnOrder(String name1){
        ArrayList<String> names1=Store.get_ItemList();
        Random rng=new Random();
        String name;
        double purchasePrice;
        boolean newOrUsed;
        int condition;
        String[] band_list={"band1","band2","band3"};
        String band_name;
        String[] album_list={"album1","album2","album3"};
        String album_name;
        int day_arrived;
        boolean Electric;
        String[] type={"Standard", "Piccolo", "Plastic"};
        String Flute_Type;
        String[] key={"A","Bb","C"};
        String Harmonica_Key;
        double Hat_Size;
        String[] size={"S","M","L"};
        String Shirt_Size;
        int wattage;
        double length;
        String[] type1={"Violin", "Cello", "Guitar"};
        String string_type;


        //if else statements to check if its already ordered
        if (Store.already_ordered(name1)==false){
            //if not do 3 orders with randomized attribute each time
            for (int i=0; i<3; i++){
                name=name1;
                condition=rng.nextInt(4);
                purchasePrice= 1+(50)*rng.nextDouble();
                purchasePrice=Math.round(purchasePrice*100)/100.0;
                newOrUsed=rng.nextBoolean();
                band_name=band_list[rng.nextInt(3)];
                album_name=album_list[rng.nextInt(3)];
                day_arrived=(rng.nextInt(3)+1+Store.get_daysPassed());
                Electric=rng.nextBoolean();
                Flute_Type=type[rng.nextInt(3)];
                Harmonica_Key=key[rng.nextInt(3)];
                Shirt_Size=size[rng.nextInt(3)];
                Hat_Size=1+(9)*rng.nextDouble();
                wattage=rng.nextInt(100)+1;
                length=1+(99)*rng.nextDouble();
                string_type=type1[rng.nextInt(3)];

                if (name1=="PaperScore"){
                    PaperScore item1=new PaperScore(name,purchasePrice,newOrUsed, day_arrived,condition,band_name,album_name);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="MusicCD"){
                    CD item1=new CD(name,purchasePrice,newOrUsed, day_arrived,condition,band_name,album_name);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="Vinyl"){
                    Vinyl item1=new Vinyl(name,purchasePrice,newOrUsed, day_arrived,condition,band_name,album_name);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="CDPlayer"){
                    Players_Items.CD item1=new Players_Items.CD(name,purchasePrice,newOrUsed, day_arrived,condition);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="RecordPlayer"){
                    RecordPlayer item1=new RecordPlayer(name,purchasePrice,newOrUsed, day_arrived,condition);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="MP3"){
                    MP3 item1=new MP3(name,purchasePrice,newOrUsed, day_arrived,condition);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="Guitar"){
                    Guitar item1=new Guitar(name,purchasePrice,newOrUsed, day_arrived,condition,Electric);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="Bass"){
                    Bass item1=new Bass(name,purchasePrice,newOrUsed, day_arrived,condition,Electric);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="Mandolin"){
                    Mandolin item1=new Mandolin(name,purchasePrice,newOrUsed, day_arrived,condition, Electric);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="Flute"){
                    Flute item1=new Flute(name,purchasePrice,newOrUsed, day_arrived,condition, Flute_Type);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="Harmonica"){
                    Harmonica item1=new Harmonica(name,purchasePrice,newOrUsed, day_arrived,condition, Harmonica_Key);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="Hats"){
                    Hats item1=new Hats(name,purchasePrice,newOrUsed, day_arrived,condition, Hat_Size);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="Shirts"){
                    Shirts item1=new Shirts(name,purchasePrice,newOrUsed, day_arrived,condition, Shirt_Size);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="Bandanas"){
                    Bandanas item1=new Bandanas(name,purchasePrice,newOrUsed, day_arrived,condition);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="PracticeAmps"){
                    PracticeAmps item1=new PracticeAmps(name,purchasePrice,newOrUsed, day_arrived,condition,wattage);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="Cables"){
                    Cables item1=new Cables(name,purchasePrice,newOrUsed, day_arrived,condition,length);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                else if (name1=="Strings"){
                    Strings item1=new Strings(name,purchasePrice,newOrUsed, day_arrived,condition,string_type);
                    Store.add_orders(item1);
                    Store.Pay(purchasePrice);
                }
                System.out.println(this.get_name()+" put in order for "+ name +" for $"+purchasePrice+" on day "+Store.get_daysPassed());
            }

        }
    }

    //OpenTheStore function
    //args:none
    //random amount of buyers between 4 to 10 and random amount of sellers between 1 to 4 are set
    //each buyer/seller has unique name and uses buy/sell function from customer class
    //returns N/A
    public void OpenTheStore() {
        int buy_count;
        int sell_count;
        Random rng=new Random();
        buy_count=rng.nextInt(6);
        buy_count+=4;
        sell_count=rng.nextInt(3);
        sell_count+=1;
        for(int i=0; i<buy_count;i++){
            Customer person=new Customer();
            person.setName(i + 1, "Buyer");
            person.Buy();
        }
        for(int j=0; j<sell_count;j++){
            Customer person=new Customer();
            person.setName(j + 1, "Seller");
            person.Sell();
        }
    }

    //clean the store function
    //args:none
    //velma invokes damageitem function 5% of the time
    //shaggy invokes damageitem function 20% of the time
    //appropriate message is announced for each action.
    public void CleanTheStore() {
        String name;
        int roll;
        Random Rng=new Random();
        name=this.get_name();
        roll=Rng.nextInt(100);
        if (name=="Velma"){
            if (roll<5){
                damageItem();
                System.out.println(this.get_name()+" damaged an item while cleaning.");
            }
            else{
                System.out.println(this.get_name()+" cleaned the store without breaking anything.");
            }
        }
        else{
            if (roll < 20){
                damageItem();
                System.out.println(this.get_name()+" damaged an item while cleaning.");
            }
            else{
                System.out.println(this.get_name()+" cleaned the store without breaking anything.");
            }

        }
    }
    //Leavethestore function
    //arguments: N/A
    //announces the appropriate message and increment days worked for the worker
    //increments stores day passed
    //returns: N/A
    public void LeaveTheStore() {
        System.out.println(this.get_name() + " went home for the day.");
        this.days_worked+=1;
        if (Store.get_daysPassed()%7!=0){
            Store.increment_daysPassed();
        }
    }

    //damageItem function
    //arguments: N/A
    //if conditions at the lowest throw the item away and announce it
    //else downgrade condition by 1 and decrease price by 20%
    //returns: N/A
    public void damageItem() {
        int roll;
        int Curr_condition;
        int mod_condition;
        double curr_listPrice;
        double mod_listPrice;
        Random Rng= new Random();
        roll=Rng.nextInt(Store.get_InventorySize());
        Curr_condition=Store.get_Item(roll).get_condition();
        curr_listPrice=Store.get_Item(roll).get_listPrice();
        if (Curr_condition==0){
            Store.remove_Inventory(roll);
            System.out.println(this.get_name()+" has thrown away a(n) "+ Store.get_Item(roll).get_name()+ " due to less than poor condition.");
        }
        else{
            mod_condition=Curr_condition-1;
            mod_listPrice=curr_listPrice*.8;
            Store.get_Item(roll).set_condition(mod_condition);
            Store.get_Item(roll).set_listPrice(mod_listPrice);
            System.out.println(this.get_name()+" has damaged a(n) "+ Store.get_Item(roll).get_name()+ ".");

        }
    }
}
