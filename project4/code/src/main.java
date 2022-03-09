import Items.Players;
import Players_Items.CD;
import org.jfree.ui.RefineryUtilities;

import java.util.Random;


public class main {

    public static void main(String argv[]){

        FNMS sim1=new FNMS();
        sim1.initialize();
        sim1.Run();

        chart chart1 = new chart("Graph display","$ vs days",sim1, true);
        //uncomment and comment the top one for 2nd graph
        chart chart2 = new chart("Graph display","count vs days",sim1, false);

        chart1.pack( );
        RefineryUtilities.centerFrameOnScreen( chart1 );
        chart1.setVisible( true );
        chart2.pack( );
        RefineryUtilities.centerFrameOnScreen( chart2 );
        chart2.setVisible( true );

        /*
        for (Double i: chart1.sim.day_itemSales){
            System.out.print(i+" ");
        }
        System.out.println("");
        for (Double i: chart1.sim.day_totalRegister){
            System.out.print(i+" ");
        }

         */

        /*
        chart1.pack();
        RefineryUtilities.centerFrameOnScreen( chart1 );
        chart1.setVisible( true );

         */



        /*
        Players_Items.CD CD1=new CD("CD",1,false,1,0);
        boolean bool1= CD1 instanceof Items.Players;
        System.out.println(bool1);

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
