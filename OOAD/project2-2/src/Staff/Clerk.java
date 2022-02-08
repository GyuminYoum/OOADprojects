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

import java.util.Random;

public class Clerk extends Staff{
    
    private Integer days_worked;

    public Clerk(String name1) {
        super(name1);
        days_worked=0;
    }
    public void set_daysWorked(Integer days1){
        days_worked=days1;
    }
    public Integer get_daysWorked(){
        return days_worked;
    }
    public void increment_daysWorked(){
        days_worked+=1;
    }

    public void ArriveAtStore() {
        String name1=this.get_name();
        Integer Curr_day=Store.get_daysPassed();
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

    public void CheckRegister() {
        Double register_cash=Store.get_Register();
        if (register_cash<75){
            this.GoToBank();
        }
        else{
            System.out.println("There is $"+Store.get_Register()+" in the register");
        }
    }

    public void GoToBank() {
        System.out.println(this.get_name()+" went to the bank and put $1000 in the register.");
        Double current_val=Store.get_moneyWithdrawn();
        Store.set_moneyWithdrawn(current_val+1000);
        Store.add_Register(1000.0);
    }

    public void DoInventory() {
        Double inven_value=Store.get_InventoryValue();
        String[] item_names=Store.get_ItemList();
        //testrun
        //String[] item_names={"PaperScore","Soccer"};
        System.out.println("All the items in the inventory are worth total of "+ inven_value);
        for(int i=0; i<item_names.length; i++){
            if(Store.check_stock(item_names[i])==0){
                this.PlaceAnOrder(item_names[i]);
                this.PlaceAnOrder(item_names[i]);
                this.PlaceAnOrder(item_names[i]);
            }
        }
    }

    public void PlaceAnOrder(String name1){
        String[] names1=Store.get_ItemList();
        Random rng=new Random();
        String name;
        Double purchasePrice;
        Boolean newOrUsed;
        Integer condition;
        String[] band_list={"band1","band2","band3"};
        String band_name;
        String[] album_list={"album1","album2","album3"};
        String album_name;
        Integer day_arrived;
        Boolean Electric;
        String[] type={"Standard", "Piccolo", "Plastic"};
        String Flute_Type;
        String[] key={"A","Bb","C"};
        String Harmonica_Key;
        Double Hat_Size;
        String[] size={"S","M","L"};
        String Shirt_Size;
        Integer wattage;
        Double length;
        String[] type1={"Violin", "Cello", "Guitar"};
        String string_type;

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


        //if else statements to convert names to actual instantiated objects
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

    public void OpenTheStore() {
        int buy_count;
        int sell_count;
        Random rng=new Random();
        buy_count=rng.nextInt(6);
        buy_count+=4;
        sell_count=rng.nextInt(3);
        sell_count+=1;
        Customer person=new Customer();
        for(int i=0; i<buy_count;i++){
            person.Buy();
        }
        for(int j=0; j<sell_count;j++){
            person.Sell();
        }
    }

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
    public void LeaveTheStore() {
        System.out.println(this.get_name() + " went home for the day.");
        this.days_worked+=1;
        if (Store.get_daysPassed()%7!=0){
            Store.increment_daysPassed();
        }
    }

    public void damageItem() {
        int roll;
        int Curr_condition;
        int mod_condition;
        Double curr_listPrice;
        Double mod_listPrice;
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
        }

    }


}
