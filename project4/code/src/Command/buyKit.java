package Command;

import Staff.Clerk;
import Store.Store;
import Store.User;
public class buyKit implements Command {
    User user;
    public buyKit(User usr){
        user=usr;
    }
    public void execute(){
        if(user.get_loc()!=null){
            Store store=user.get_loc();
            Clerk person=store.get_OnShift();
            person.sell_GuitarKit();
        }
        else{
            System.out.println("Please select Store location first");
        }
    }
}
