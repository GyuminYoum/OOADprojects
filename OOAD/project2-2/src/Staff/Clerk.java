package Staff;

import Store.Store;

import java.util.*;

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
        number_PO=preorders.size();
        System.out.println(name1+ " arrives at the store on Day "+ Store.get_daysPassed());
        //System.out.println(name1);
        for(int i=0; i<number_PO; i++){
            //add from pre-orders to inventory
            preorders.get(i).get_name();

        }
    }

    public void checkRegister() {
        Integer register_cash=Store.get_register();
        if (register_cash<75){
            this.goToBank();
        }
        else{
            System.out.println("There is $"+Store.get_register()+" in the register");
        }
    }

    public void goToBank() {
        System.out.println(this.get_name()+" went to the bank and put $1000 in the register.");
        Float current_val=Store.get_moneyWithdrawn();
        Store.set_moneyWithdrawn(current_val+1000);

    }

    public void doInventory() {

    }

    public void placeAnOrder() {


    }

    public void openTheStore() {
    }

    public void cleanTheStore() {
    }

    public void leaveTheStore() {
        System.out.println(this.get_name() + " went home for the day.");
        this.days_worked+=1;
    }

    public void damageItem() {
    }

    public static void main(){

    }

}
