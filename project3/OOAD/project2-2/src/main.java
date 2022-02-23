import Items.Players;
import Players_Items.CD;

import java.util.Random;

public class main {

    public static void main(String argv[]){

        FNMS sim1=new FNMS();
        sim1.initialize();
        sim1.Run();




        /*
        Players_Items.CD CD1=new CD("CD",1,false,1,0);
        boolean bool1= CD1 instanceof Items.Players;
        System.out.println(bool1);
        */


        /*
         Store.set_days(5);
         System.out.println(Store.get_daysPassed());

        Store.test1();
        while(Store.get_daysPassed()<=30) {
            Store.pickOnShift();
            Store.get_OnShift().ArriveAtStore();
            Store.get_OnShift().LeaveTheStore();
        }
        */


    }
}
