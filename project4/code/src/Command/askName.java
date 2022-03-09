package Command;

import Staff.Clerk;
import Store.Store;
import Store.User;

//user.get_loc provides the right store
//prints the clerk working at the right store
//if the user's store hasn't been picked then print appropriate msg
public class askName implements Command{
    User user;
    public askName(User usr){
        user=usr;
    }
    public void execute(){
        if(user.get_loc()!=null){
            Store store1=user.get_loc();
            System.out.println("Name of the clerk that is working at "+store1.get_location()+" store is "+ store1.get_OnShift().get_name());
        }
        else{
            System.out.println("Select Store first");
        }


    }
}
