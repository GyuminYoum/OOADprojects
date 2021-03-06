import Observer.Tracker;
import Staff.Clerk;
import Staff.Electronic;
import Staff.Haphazard;
import Staff.Manual;
import Store.Store;
import Observer.logger;
import java.util.*;


//class FNMS for simulation
//Store.Build() to initialize inventory and staff list as well as other necessary attributes.
//for 30days, the onshift staff is picked and staff performs all the functions below each day.
//afterwards, report is created and printed
//WIth current code flow, if the worker worked day 1 and didn't work day2, the worker's daysWorked
//will remain to be 1 until the leavethestore function is procced at the end of day2.
public class FNMS {
    Store North_store;
    Store South_store;
    int duration=0;
    ArrayList<Clerk> members= new ArrayList<Clerk>();
    public ArrayList <Double> day_itemSales= new ArrayList<Double>();
    public ArrayList <Double> day_totalRegister= new ArrayList<Double>();
    public ArrayList <Integer> day_inventoryCount = new ArrayList <Integer>();
    public ArrayList <Integer> day_damagedItems = new ArrayList <Integer>();
    public ArrayList <Integer> day_itemSold = new ArrayList<Integer>();

    public void initialize(){
        Random rng= new Random();
        while(duration%7==0){
            duration=rng.nextInt(20)+10;
        }
        //pool of staff object is initialized and fed to both store objects to be used.
        Clerk Velma=new Clerk("Velma", new Haphazard());
        Clerk Shaggy=new Clerk("Shaggy", new Manual());
        Clerk Daphne=new Clerk("Daphne", new Electronic());
        Clerk Justin=new Clerk("Justin", new Manual());
        Clerk Freddy=new Clerk("Freddy", new Electronic());
        members.add(Velma);
        members.add(Shaggy);
        members.add(Daphne);
        members.add(Justin);
        members.add(Freddy);
        Store store1= new Store(members,"North",duration);
        Store store2= new Store(members, "South",duration);
        North_store=store1;
        South_store=store2;
        North_store.Build();
        South_store.Build();
    }
    public void Run(){
        Clerk staff1;
        Clerk staff2;
        //Tracker tracker1 = new Tracker();
        //Tracker tracker2 = new Tracker(South_store);
        North_store.registerTracker(Tracker.GetInstance());
        South_store.registerTracker(Tracker.GetInstance());
        //tracker1.initialize();
        //tracker2.initialize();

        while(North_store.get_daysPassed()<=duration && South_store.get_daysPassed()<=duration){
            //this is done to avoid the daysWorked from resetting at the end
            //ran only once for north_store since if i were to reset it at the end with leaveTheStore function,
            //it would reset the daysWorked for the workers.
            North_store.reset_ClerkStore();
            String name1="project4//logs//North//Logger-"+North_store.get_daysPassed()+".txt";
            String name2="project4//logs//South//Logger-"+South_store.get_daysPassed()+".txt";
            //logger watcher1=new logger();
            /*logger watcher2=new logger();
            North_store.registerLogger(watcher1);
            South_store.registerLogger(watcher2);
            watcher1.makeTxt(name1, North_store);
            watcher2.makeTxt(name2, South_store);
            */

            North_store.registerLogger(logger.GetInstance());
            South_store.registerLogger(logger.GetInstance());
            logger.GetInstance().makeTxt(North_store);
            logger.GetInstance().makeTxt(South_store);


            staff1=North_store.pickOnShift();
            staff2=South_store.pickOnShift();

            /* print statements on both ends to fcheck if anyones overworking
            for (Clerk i:North_store.get_ClerkMember()){
                if(i.get_workingAt()!=null){
                    System.out.println(i.get_name()+"  "+i.get_workingAt().get_location()+"  "+i.get_daysWorked());
                }
                else{
                    System.out.println(i.get_name()+"  "+"rest"+"  "+i.get_daysWorked());

                }

            }
            for (Clerk i:South_store.get_ClerkMember()){
                if(i.get_workingAt()!=null){
                    System.out.println(i.get_name()+"  "+i.get_workingAt().get_location()+"  "+i.get_daysWorked());
                }
                else{
                    System.out.println(i.get_name()+"  "+"rest"+"  "+i.get_daysWorked());
                }
            }
             */
            if(staff1!=null && staff2!=null) {
                //System.out.println(staff1.get_name());
                //System.out.println(staff1.get_workingAt().get_location());
                staff1.ArriveAtStore();
                staff2.ArriveAtStore();
                staff1.CheckRegister();
                staff2.CheckRegister();
                staff1.DoInventory();
                staff2.DoInventory();
                staff1.OpenTheStore();
                staff2.OpenTheStore();
                staff1.user_interaction();
                staff1.CleanTheStore();
                staff2.CleanTheStore();
                staff1.LeaveTheStore();
                staff2.LeaveTheStore();
            }

            North_store.removeLogger(logger.GetInstance());
            North_store.printTrackers();
            South_store.removeLogger(logger.GetInstance());
            South_store.printTrackers();
            /*
            for (Clerk i:North_store.get_ClerkMember()){
                if(i.get_workingAt()!=null){
                    System.out.println(i.get_name()+"  "+i.get_workingAt().get_location()+"  "+i.get_daysWorked());
                }
                else{
                    System.out.println(i.get_name()+"  "+"rest"+"  "+i.get_daysWorked());

                }

            }
            for (Clerk i:South_store.get_ClerkMember()){
                if(i.get_workingAt()!=null){
                    System.out.println(i.get_name()+"  "+i.get_workingAt().get_location()+"  "+i.get_daysWorked());
                }
                else{
                    System.out.println(i.get_name()+"  "+"rest"+"  "+i.get_daysWorked());

                }
            }
            */
        }

        North_store.Report();
        North_store.printTrackers();
        North_store.removeTracker(Tracker.GetInstance());
        South_store.Report();
        South_store.printTrackers();
        South_store.removeTracker(Tracker.GetInstance());
        /*
        System.out.println("====================================================================");
        North_store.print_daydamagedItems();
        North_store.print_dayinventoryCount();
        North_store.print_daytotalRegister();
        North_store.print_dayitemSales();
        North_store.print_dayitemSold();
        System.out.println("====================================================================");
        South_store.print_daydamagedItems();
        South_store.print_dayinventoryCount();
        South_store.print_daytotalRegister();
        South_store.print_dayitemSales();
        South_store.print_dayitemSold();
         */
        //combine the two separate arraylists and set it to fnms
        for(int i=0; i< North_store.get_daydamagedItems().size(); i++){
            day_damagedItems.add(North_store.get_daydamagedItems().get(i)+South_store.get_daydamagedItems().get(i));
            day_inventoryCount.add(North_store.get_dayinventoryCount().get(i)+South_store.get_dayinventoryCount().get(i));
            day_totalRegister.add(Math.floor(North_store.get_daytotalRegister().get(i)+South_store.get_daytotalRegister().get(i)*100)/100);
            day_itemSales.add(Math.floor(North_store.get_dayitemSales().get(i)+South_store.get_dayitemSales().get(i)*100)/100);
            day_itemSold.add(North_store.get_dayitemSold().get(i)+South_store.get_dayitemSold().get(i));
        }


    }

}
