import Staff.Clerk;
import Store.Store;

//class FNMS for simulation
//Store.Build() to initialize inventory and staff list as well as other necessary attributes.
//for 30days, the onshift staff is picked and staff performs all the functions below each day.
//afterwards, report is created and printed
public class FNMS {

    public static void initialize(){
        Store.Build();
    }
    public static void Run(){
        while(Store.get_daysPassed()<=30){
            Store.pickOnShift();
            Clerk staff1=Store.get_OnShift();
            if(staff1!=null){
                staff1.ArriveAtStore();
                staff1.CheckRegister();
                staff1.DoInventory();
                staff1.OpenTheStore();
                staff1.CleanTheStore();
                staff1.LeaveTheStore();
            }
        }
        Store.Report();
    }
}
