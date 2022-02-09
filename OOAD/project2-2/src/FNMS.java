import Staff.Clerk;
import Store.Store;

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
