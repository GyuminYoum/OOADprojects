package Command;

import Staff.Clerk;
import Store.Store;
import java.util.*;
import Store.User;

public class selectStore implements Command {
    Clerk clerk1;
    User user1;
    public selectStore(Clerk staff1, User user2){
        user1=user2;
        clerk1=staff1;
    }
    public void execute(){
        Scanner reader=new Scanner(System.in);
        char response;
        System.out.println("Pick a store location:(N/S)");
        response=reader.next().charAt(0);
        if(response=='N'){
            Store ns=clerk1.find_Store("North");
            user1.set_loc(ns);
        }
        else if(response=='S'){
            Store ns=clerk1.find_Store("South");
            user1.set_loc(ns);
        }
        else{
            System.out.println("Invalid input");
        }
    }
}
