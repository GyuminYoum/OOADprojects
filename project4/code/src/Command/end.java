package Command;

import Staff.Clerk;
import Store.Store;
import Store.User;

public class end implements Command {
    User user;
    public end(User usr){
        user=usr;
    }
    public void execute(){
        System.out.println("Exiting...");
    }
}
