package Staff;
import Store.User;
import java.util.*;

import Command.*;
import Factory.NGuitarKitFactory;
import Factory.RandomItem_Factory;
import Factory.SGuitarKitFactory;
import Instruments_Items.Stringed;
import Instruments_Items.wind;
import Items.Items;
import Store.Customer;
import Store.Store;


import java.util.ArrayList;
import java.util.Random;

public class Clerk extends Staff{
    
    private int days_worked;
    private boolean sick;
    private Strategy strategy_;
    private Store workingAt;
    private Store userStore;
    private int sold_count;
    private int buy_count;
    private int dmg_count;

    //variables and methods for Tracker
    private int soldN = 0;
    public int get_SN(){return soldN;}
    public void set_SN(int n){soldN = n;}
    private int soldS = 0;
    public int get_SS(){return soldS;}
    public void set_SS(int n){soldS = n;}
    public int boughtN = 0;
    public int get_BN(){return boughtN;}
    public void set_BN(int n){boughtN = n;}
    public int boughtS = 0;
    public int get_BS(){return boughtS;}
    public void set_BS(int n){boughtS = n;}
    public int dmgN = 0;
    public int get_DN(){return dmgN;}
    public void set_DN(int n){dmgN = n;}
    public int dmgS = 0;
    public int get_DS(){return dmgS;}
    public void set_DS(int n){dmgS = n;}

    //default constructor for clerk
    public Clerk(String name1, Strategy strategy) {
        super(name1);
        days_worked=0;
        strategy_ = strategy;
        workingAt=null;
        userStore=null;
        sold_count=0;
        buy_count=0;
        dmg_count=0;
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
    public void set_workingAt(Store store1){
        workingAt=store1;
    }
    public Store get_workingAt(){
        return workingAt;
    }
    public void set_userStore(Store store1){
        userStore=store1;
    }
    public Store get_userStore(){
        return userStore;
    }
    public int sell_GuitarKit(){
        Store Store=workingAt;
        Items soldItem=null;
        if(workingAt.get_location()=="North"){
            NGuitarKitFactory NG1= new NGuitarKitFactory();
            soldItem=NG1.createGuitarKit();
        }
        else if(workingAt.get_location()=="South"){
            SGuitarKitFactory SG1=new SGuitarKitFactory();
            soldItem=SG1.createGuitarKit();
        }
        soldItem.set_daySold(Store.get_daysPassed());
        soldItem.set_salePrice(Math.floor((soldItem.get_listPrice() ) * 100) / 100);
        //remove item from inventory, add to SoldItem ArrayList, add money to register
        Store.add_soldItem(soldItem);
        Store.add_Register(soldItem.get_salePrice());
        System.out.println("You bought a custom Guitar kit for $"+soldItem.get_salePrice());
        Store.notifyTrackers("sold");
        sold_count++;
        return 1;
    }

    //Arrive at the store function
    //args:none
    //announces appropriate msg including name and day
    //receives order that has dayArrived value=day and announces it
    //returns N/A

    public void ArriveAtStore() {
        String name1=this.get_name();
        String content;
        Store Store=workingAt;
        int Curr_day=Store.get_daysPassed();
        int item_count=0;
        content=name1+ " arrives at the "+Store.get_location()+" store on Day "+ Store.get_daysPassed();
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
            //for extracredit:
            //resets all the variables needed for graph
            Store.resetCounts();
        }
        else{
            Store.resetCounts();
            Store.add_dayitemSales(Store.getdaySales());
            Store.add_daytotalRegister(Store.get_Register());
            Store.add_dayinventoryCount(Store.get_InventorySize());
            Store.add_daydamagedItems(Store.getdayDmg());
            Store.add_dayitemSold(Store.getdaySold());
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
        Store Store=workingAt;
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
        Store Store=workingAt;
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
        Store Store=workingAt;
        double inven_value=Store.get_InventoryValue();
        ArrayList<String> item_names=Store.get_ItemList();
        String content;
        int order_count=0;
        int day_dmgCount=0;
        //testrun
        //String[] item_names={"PaperScore","Soccer"};
        //System.out.println("All the items in the inventory are worth total of "+ inven_value);
        content="There are total of "+ Store.get_InventorySize()+" items in the inventory.";
        Store.notifyLoggers(content);
        content="All the items in the inventory are worth total of $ "+ inven_value;
        Store.notifyLoggers(content);
        for(int i=0; i<item_names.size(); i++){
            if(Store.check_stock(item_names.get(i))==0){
                order_count+=this.PlaceAnOrder(item_names.get(i));
            }
        }
        content=this.get_name()+" ordered total of "+ order_count+" items";
        Store.notifyLoggers(content);


        //tune each item
        for(int i=0; i < Store.get_InventorySize(); i++) {
            if (Store.get_Item(i) instanceof Stringed || Store.get_Item(i) instanceof wind) {
                if(strategy_.Tune(Store.get_Item(i))==false){
                    day_dmgCount++;
                }
            }
        }
        dmg_count+=day_dmgCount;
        content=this.get_name()+" has damaged "+ day_dmgCount+" items while tuning";
        Store.notifyLoggers(content);
        Store.setdayDmg(Store.getdayDmg()+day_dmgCount);
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
        Store Store=workingAt;
        int order_count=0;
        if (Store.already_ordered(name1)==false){
            //if not do 3 orders with randomized attribute each time
            RandomItem_Factory factory1=new RandomItem_Factory(workingAt);
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
    public static double countBuyerNumber(double mean){
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

    public Store find_Store(String loc){
        Store store=null;
        for(Clerk i: this.get_workingAt().get_ClerkMember()){
            if(i.get_workingAt()!=null){
                if(i.get_workingAt().get_location()==loc){
                    store=i.get_workingAt();
                }
            }
        }
        return store;
    }

    //OpenTheStore function
    //args:none
    //random amount of buyers between 4 to 10 and random amount of sellers between 1 to 4 are set
    //each buyer/seller has unique name and uses buy/sell function from customer class
    //returns N/A
    public void OpenTheStore() {
        int buy_count = 0;
        int sell_count = 0;
        int ClerkSoldItems = 0;
        int ClerkBoughtItems = 0;
        String content;
        Random rng = new Random();
        Store Store = workingAt;
        Integer temp=0;

        /*
        buy_count=rng.nextInt(6);
        buy_count+=4;

        */
        if (workingAt.get_daysPassed() != workingAt.get_duration() - 1) {
            buy_count = (int) countBuyerNumber(3.0);
            sell_count = rng.nextInt(3);
            sell_count += 1;
            for (int i = 0; i < buy_count; i++) {
                Customer person = new Customer(workingAt);
                person.setName(i + 1, "Buyer");
                ClerkSoldItems += person.Buy();
            }
            for (int j = 0; j < sell_count; j++) {
                Customer person = new Customer(workingAt);
                person.setName(j + 1, "Seller");
                ClerkBoughtItems += person.Sell();
            }
            sell_count += ClerkSoldItems;
            content = this.get_name() + " sold " + ClerkSoldItems + " items.";
            Store.notifyLoggers(content);
            Store.setdaySold(Store.getdaySold()+ClerkSoldItems);
            buy_count += ClerkBoughtItems;
            content = this.get_name() + " Bought " + ClerkBoughtItems + " items.";
            Store.notifyLoggers(content);
        }
    }
    public void user_interaction(){
        Invoker IV=new Invoker();
        Scanner reader = new Scanner(System.in);
        String input;
        String option = "";
        Store chosen_store = null;
        /*
        System.out.println(this.get_name()+" working at "+this.get_workingAt().get_location());

        for(Clerk i:this.get_workingAt().get_ClerkMember()){
            if(i.get_workingAt()!=null) {
                System.out.println(i.get_name() + " working at " + i.get_workingAt().get_location());
            }
        }

         */
        User user1=new User();
        if(workingAt.get_duration()==workingAt.get_daysPassed()){
            while(option.matches("7")==false) {
                System.out.println("Select one of the possible options: (1-7)");
                System.out.println("1. Select a store to issue commands to: (North or South)");
                System.out.println("2. Ask Clerk their name");
                System.out.println("3. Ask the Clerk what time it is");
                System.out.println("4. Sell a normal inventory item to clerk");
                System.out.println("5. Buy a normal inventory item from Clerk");
                System.out.println("6. Buy a custom guitar kit from the clerk");
                System.out.println("7. end interaction");
                option = reader.nextLine();
                if (option.matches("1") == false && option.matches("2") == false && option.matches("3") == false && option.matches("4") == false && option.matches("5") == false && option.matches("6") == false && option.matches("7") == false) {
                    System.out.println("Invalid input");
                } else if (option.matches("1") == true) {
                    selectStore ss1 = new selectStore(this, user1);
                    IV.setCommand(ss1);
                    IV.Perform();
                } else if (option.matches("2") == true) {
                    askName ss1 = new askName(user1);
                    IV.setCommand(ss1);
                    IV.Perform();
                } else if (option.matches("3") == true) {
                    askTime ss1 = new askTime(user1);
                    IV.setCommand(ss1);
                    IV.Perform();
                } else if (option.matches("4") == true) {
                    Sell ss1 = new Sell(user1);
                    IV.setCommand(ss1);
                    IV.Perform();
                } else if (option.matches("5") == true) {
                    buyItem ss1 = new buyItem(user1);
                    IV.setCommand(ss1);
                    IV.Perform();
                } else if (option.matches("6") == true) {
                    buyKit ss1 = new buyKit(user1);
                    IV.setCommand(ss1);
                    IV.Perform();
                } else if (option.matches("7") == true) {
                    end ss1 = new end(user1);
                    IV.setCommand(ss1);
                    IV.Perform();
                } else {
                    System.out.println("Invalid input");
                }
            }
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
        String content;
        int day_dmgCount=0;
        roll=Rng.nextInt(100);
        Store Store=workingAt;
        if (name=="Velma"){
            if (roll<5){
                damageItem();
                //System.out.println(this.get_name()+" damaged an item while cleaning.");
                content=this.get_name()+" damaged an item while cleaning.";
                Store.notifyLoggers(content);
                Store.notifyTrackers("damaged");
                day_dmgCount++;
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
                Store.notifyTrackers("damaged");
                day_dmgCount++;
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
                Store.notifyTrackers("damaged");
                day_dmgCount++;
            }
            else{
                //System.out.println(this.get_name()+" cleaned the store without breaking anything.");
                content=this.get_name()+" cleaned the store without breaking anything.";
                Store.notifyLoggers(content);
            }
        }
        dmg_count+=day_dmgCount;
        content=this.get_name()+" damaged "+day_dmgCount+" items while cleaning.";
        Store.notifyLoggers(content);
        Store.setdayDmg(Store.getdayDmg()+day_dmgCount);
    }
    //Leavethestore function
    //arguments: N/A
    //announces the appropriate message and increment days worked for the worker
    //increments stores day passed
    //returns: N/A
    public void LeaveTheStore() {
        String content;
        Store Store=workingAt;
        //System.out.println(this.get_name() + " went home for the day.");
        content=this.get_name() + " went home for the day.";
        Store.notifyLoggers(content);
        this.days_worked+=1;

        if (workingAt.get_daysPassed()%7!=0){
            workingAt.increment_daysPassed();
        }

        for(int i=0; i<workingAt.get_ClerkMember().size(); i++){
            if(workingAt.get_ClerkMember().get(i).get_workingAt()==null){
                workingAt.get_ClerkMember().get(i).set_daysWorked(0);
            }
        }
        //System.out.println(this.get_name()+" worked "+this.get_daysWorked()+" days");
        /*
        for (int i=0; i< Store.get_ClerkMember().size(); i++){
            System.out.println(Store.get_ClerkMember().get(i).get_name()+" "+Store.get_ClerkMember().get(i).get_sick()+" "+Store.get_ClerkMember().get(i).get_daysWorked()+" days");
        }
         */
        Store.add_dayitemSales(Math.floor(Store.getdaySales()*100)/100);
        Store.add_daytotalRegister(Math.floor(Store.get_Register()*100)/100);
        Store.add_dayinventoryCount(Store.get_InventorySize());
        Store.add_daydamagedItems(Store.getdayDmg());
        Store.add_dayitemSold(Store.getdaySold());
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
        Store Store=workingAt;
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
