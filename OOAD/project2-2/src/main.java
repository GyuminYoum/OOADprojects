public class main {
    public static void main(String argv[]){

        FNMS sim1=new FNMS();
        sim1.initialize();
        sim1.Run();

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
