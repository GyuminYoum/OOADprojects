package Staff;

import Accessories_Items.Cables;
import Accessories_Items.PracticeAmps;
import Accessories_Items.Strings;
import Clothing_Items.Bandanas;
import Clothing_Items.Hats;
import Clothing_Items.Shirts;
import Factory.RandomItem_Factory;
import Instruments_Items.Stringed;
import Instruments_Items.Stringed_Items.Bass;
import Instruments_Items.Stringed_Items.Guitar;
import Instruments_Items.Stringed_Items.Mandolin;
import Instruments_Items.wind;
import Instruments_Items.wind_Items.Flute;
import Instruments_Items.wind_Items.Harmonica;
import Items.Items;
import Music_Items.CD;
import Music_Items.PaperScore;
import Music_Items.Vinyl;
import Observer.Observer;
import Players_Items.MP3;
import Players_Items.RecordPlayer;
import Store.Customer;
import Store.Store;


import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.Random;
import Observer.logger;

public class Clerk extends Staff{
    
    private int days_worked;
    private boolean sick;
    private Strategy strategy_;



    //default constructor for clerk
    public Clerk(String name1, Strategy strategy) {
        super(name1);
        days_worked=0;
        strategy_ = strategy;
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
    public void set_sick(boolean bool1){
        sick=bool1;
    }
    public boolean get_sick(){
        return sick;
    }

    //Arriveatthe store function
    //args:none
    //announces appropriate msg including name and day
    //receives order that has dayArrived value=day and announces it
    //returns N/A

    public void ArriveAtStore() {

        String name1=this.get_name();
        String content;
        int Curr_day=Store.get_daysPassed();
        int item_count=0;
        content=name1+ " arrives at the store on Day "+ Store.get_daysPassed();
        Store.notifyLoggers(content);
        //System.out.println(name1+ " arrives at the store on Day "+ Store.get_daysPassed());
        if(Curr_day%7!=0){
            for (int i=0; i<Store.get_orderSize(); i++){
                if(Curr_day>=Store.get_orders().get(i).get_dayArrived()){
                    //move to inventory
                    Store.add_Inventory(Store.get_orders().get(i));
                    //System.out.println(name1+" received "+Store.get_orders().get(i).get_name()+" on day "+Curr_day);
                    content=name1+" received "+Store.get_orders().get(i).get_name()+" on day "+Curr_day;
                    Store.notifyLoggers(content);
                    Store.get_orders().remove(i);
                    item_count++;
                }
            }
            content=item_count+" items has been added to the inventory";
            Store.notifyLoggers(content);
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
        String content;
        double register_cash=Store.get_Register();
        if (register_cash<75){
            this.GoToBank();
        }
        else{
            //System.out.println("There is $"+Store.get_Register()+" in the register");
            content="There is $"+Store.get_Register()+" in the register";
            Store.notifyLoggers(content);
        }
    }

    //GoToBank function
    //args:none
    //announces appropriate message
    //increases value in Cash_Register by 1000.
    //increases moneywithdrawn attribute of Store by 1000.
    //returns N/A
    public void GoToBank() {
        String content;
        //System.out.println(this.get_name()+" went to the bank and put $1000 in the register.");

        double current_val=Store.get_moneyWithdrawn();
        Store.set_moneyWithdrawn(current_val+1000);
        Store.add_Register(1000.0);
        content=this.get_name()+" went to the bank and put $1000 in the register. There is $ "+Store.get_Register()+" in the Cash Register.";
        Store.notifyLoggers(content);
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
        int dmg_count=0;
        String content;
        int order_count;
        //testrun
        //String[] item_names={"PaperScore","Soccer"};
        //System.out.println("All the items in the inventory are worth total of "+ inven_value);
        content="There are total of "+ Store.get_InventorySize()+" items in the inventory.";
        Store.notifyLoggers(content);
        content="All the items in the inventory are worth total of $ "+ inven_value;
        Store.notifyLoggers(content);
        for(int i=0; i<item_names.size(); i++){
            if(Store.check_stock(item_names.get(i))==0){
                order_count=this.PlaceAnOrder(item_names.get(i));
                content=this.get_name()+" ordered total of "+ order_count+" items";
                Store.notifyLoggers(content);
            }
        }


        //tune each item
        for(int i=0; i < Store.get_InventorySize(); i++) {
            if (Store.get_Item(i) instanceof Stringed || Store.get_Item(i) instanceof wind) {
                if(strategy_.Tune(Store.get_Item(i))==false){
                    dmg_count++;
                }
            }
        }
        content=this.get_name()+" has damaged "+ dmg_count+" items while tuning";
        Store.notifyLoggers(content);

    }

    //placeanorder function
    //args:String
    //places 3 orders of an item with the argument name if it hasn't been ordered yet.
    //utilizes RandomItem_Factory class to create a new Items object
    //item is then added to the order_list attribute and Cash_Register balance is decreased by purchasePrice
    //returns N/A
    public int PlaceAnOrder(String name1){
        //if else statements to check if its already ordered
        String content;
        int order_count=0;
        if (Store.already_ordered(name1)==false){
            //if not do 3 orders with randomized attribute each time
            RandomItem_Factory factory1=new RandomItem_Factory();
            Items temp_item=factory1.create_RandomItem(name1);
            if (temp_item.get_reorder()==true){
                for (int i=0; i<3; i++){
                    Items new_item=factory1.create_RandomItem(name1);
                    Store.add_orders(new_item);
                    Store.Pay(new_item.get_purchasePrice());
                    //System.out.println(this.get_name()+" put in order for "+ new_item.get_name() +" for $"+new_item.get_purchasePrice()+" on day "+Store.get_daysPassed());
                    content=this.get_name()+" put in order for "+ new_item.get_name() +" for $"+new_item.get_purchasePrice()+" on day "+Store.get_daysPassed();
                    Store.notifyLoggers(content);
                    order_count++;
                }
            }
        }
        return order_count;
    }
    //from stackoverflow
    //https://stackoverflow.com/questions/9832919/generate-poisson-arrival-in-java
    //just different name;
    public double countBuyerNumber(double mean){
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        //+2 at the end according to the suggested direction
        return k - 1 + 2;
    }

    //OpenTheStore function
    //args:none
    //random amount of buyers between 4 to 10 and random amount of sellers between 1 to 4 are set
    //each buyer/seller has unique name and uses buy/sell function from customer class
    //returns N/A
    public void OpenTheStore() {
        int buy_count;
        int sell_count;
        int ClerkSoldItems=0;
        int ClerkBoughtItems=0;
        String content;
        Random rng=new Random();
        /*
        buy_count=rng.nextInt(6);
        buy_count+=4;

        */
        buy_count=(int)countBuyerNumber(3.0);
        sell_count=rng.nextInt(3);
        sell_count+=1;
        for(int i=0; i<buy_count;i++){
            Customer person=new Customer();
            person.setName(i + 1, "Buyer");
            ClerkSoldItems+=person.Buy();
        }
        for(int j=0; j<sell_count;j++){
            Customer person=new Customer();
            person.setName(j + 1, "Seller");
            ClerkBoughtItems+=person.Sell();
        }
        content=this.get_name()+" sold "+ClerkSoldItems+" items.";
        Store.notifyLoggers(content);
        content=this.get_name()+" Bought "+ClerkBoughtItems+" items.";
        Store.notifyLoggers(content);
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
        String content;
        int dmg_count=0;
        roll=Rng.nextInt(100);
        if (name=="Velma"){
            if (roll<5){
                damageItem();
                //System.out.println(this.get_name()+" damaged an item while cleaning.");
                content=this.get_name()+" damaged an item while cleaning.";
                Store.notifyLoggers(content);
                dmg_count++;
            }
            else{
                //System.out.println(this.get_name()+" cleaned the store without breaking anything.");
                content=this.get_name()+" cleaned the store without breaking anything.";
                Store.notifyLoggers(content);
            }
        }
        else if (name=="Shaggy"){
            if (roll < 20){
                damageItem();
                //System.out.println(this.get_name()+" damaged an item while cleaning.");
                content=this.get_name()+" damaged an item while cleaning.";
                Store.notifyLoggers(content);
                dmg_count++;
            }
            else{
                //System.out.println(this.get_name()+" cleaned the store without breaking anything.");
                content=this.get_name()+" cleaned the store without breaking anything.";
                Store.notifyLoggers(content);
            }
        }
        //daphne has 10% chance of damaging item
        else{
            if (roll < 10){
                damageItem();
                //System.out.println(this.get_name()+" damaged an item while cleaning.");
                content=this.get_name()+" damaged an item while cleaning.";
                Store.notifyLoggers(content);
                dmg_count++;
            }
            else{
                //System.out.println(this.get_name()+" cleaned the store without breaking anything.");
                content=this.get_name()+" cleaned the store without breaking anything.";
                Store.notifyLoggers(content);
            }
        }
        content=this.get_name()+" damaged "+dmg_count+" items while cleaning.";
        Store.notifyLoggers(content);
    }
    //Leavethestore function
    //arguments: N/A
    //announces the appropriate message and increment days worked for the worker
    //increments stores day passed
    //returns: N/A
    public void LeaveTheStore() {
        String content;
        //System.out.println(this.get_name() + " went home for the day.");
        content=this.get_name() + " went home for the day.";
        Store.notifyLoggers(content);
        this.days_worked+=1;
        if (Store.get_daysPassed()%7!=0){
            Store.increment_daysPassed();
        }
        for(int i=0; i<Store.get_ClerkMember().size(); i++){
            if(Store.get_ClerkMember().get(i).get_name()!=this.get_name()){
                Store.get_ClerkMember().get(i).set_daysWorked(0);
            }
        }
        /*
        for (int i=0; i< Store.get_ClerkMember().size(); i++){
            System.out.println(Store.get_ClerkMember().get(i).get_name()+" "+Store.get_ClerkMember().get(i).get_sick()+" "+Store.get_ClerkMember().get(i).get_daysWorked()+" days");
        }
         */
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
        String content;
        Random Rng= new Random();
        roll=Rng.nextInt(Store.get_InventorySize()-1);
        Curr_condition=Store.get_Item(roll).get_condition();
        curr_listPrice=Store.get_Item(roll).get_listPrice();
        if (Curr_condition==0){
            Store.remove_Inventory(roll);
            //System.out.println(this.get_name()+" has thrown away a(n) "+ Store.get_Item(roll).get_name()+ " due to less than poor condition.");
            content=this.get_name()+" has thrown away a(n) "+ Store.get_Item(roll).get_name()+ " due to less than poor condition.";
            Store.notifyLoggers(content);
        }
        else{
            mod_condition=Curr_condition-1;
            mod_listPrice=curr_listPrice*.8;
            Store.get_Item(roll).set_condition(mod_condition);
            Store.get_Item(roll).set_listPrice(mod_listPrice);
            //System.out.println(this.get_name()+" has damaged a(n) "+ Store.get_Item(roll).get_name()+ ".");
            content=this.get_name()+" has damaged a(n) "+ Store.get_Item(roll).get_name()+ ".";
            Store.notifyLoggers(content);
        }
    }
}
