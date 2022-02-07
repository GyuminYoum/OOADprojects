package Staff;

import Store.Store;

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
        number_PO=Store.Order_Items.size();
        System.out.println(name1+ " arrives at the store on Day "+ Store.get_daysPassed());
        //System.out.println(name1);
        for(int i=0; i<number_PO; i++){


        }
    }

    public void checkRegister() {
        Integer register_cash=Store.get_register();
        if (register_cash==0){
            this.goToBank();
        }
        else{
            System.out.println("There is $"+Store.get_register()+" in the register");

        }

    }

    public void goToBank() {
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
