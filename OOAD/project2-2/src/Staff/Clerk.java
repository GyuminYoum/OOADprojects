package Staff;

import Store.Store;

import java.util.ArrayList;
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

    public void arriveAtStore() {
        String name1=this.get_name();
        Integer number_PO;
        //go through order list and add them to inventory
        ArrayList<String> temp_orders=Store.get_orders();

        for (int i=0; i<temp_orders.size(); i++){
            temp_orders.get(i);

            temp_orders.remove(i);

        }

        System.out.println(name1+ " arrives at the store on Day "+ Store.get_daysPassed());
        //System.out.println(name1);


    }

    public void checkRegister() {
        Double register_cash=Store.get_register();
        if (register_cash<75){
            this.goToBank();
        }
        else{
            System.out.println("There is $"+Store.get_register()+" in the register");
        }
    }

    public void goToBank() {
        System.out.println(this.get_name()+" went to the bank and put $1000 in the register.");
        Double current_val=Store.get_moneyWithdrawn();
        Store.set_moneyWithdrawn(current_val+1000);
        Store.add_register(1000.0);
    }

    public void doInventory() {
        Double inven_value=Store.get_InventoryValue();
        String[] item_names=Store.get_ItemList();
        //testrun
        //String[] item_names={"PaperScore","Soccer"};
        System.out.println("All the items in the inventory are worth total of "+ inven_value);
        for(int i=0; i<item_names.length; i++){
            if(Store.check_stock(item_names[i])==0){
                this.placeAnOrder(item_names[i]);
            }
        }


    }

    public void placeAnOrder(String name1){
        for (int i=0; i<3; i++){

        }
        System.out.println(this.get_name()+" ordered 3 "+ name1);

    }

    public void openTheStore() {
    }

    public void cleanTheStore() {
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

    public void leaveTheStore() {
        System.out.println(this.get_name() + " went home for the day.");
        this.days_worked+=1;
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
