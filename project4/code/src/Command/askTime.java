package Command;
import java.time.*;

import Staff.Clerk;
import Store.Store;
import Store.User;

//user.get_loc provides the right store
//gets clerk name and append it to string to tell time
//if the user's store hasn't been picked then print appropriate msg
public class askTime implements Command{
    User user;
    public askTime(User usr){
        user=usr;
    }
    public void execute(){
        Store store=user.get_loc();
        if(store!=null){
            LocalTime lt1= LocalTime.now();
            System.out.println(store.get_OnShift().get_name()+" working at "+store.get_location()+" store notifies you that that the Current time is "+ lt1);
        }
        else{
            System.out.println("Pick a store location first");
        }
    }
}
