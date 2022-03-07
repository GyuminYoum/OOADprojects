package Command;
import Staff.Clerk;
import Store.Customer;
import Store.Store;
import Store.User;

public class Sell implements Command {
    User user;
    public Sell(User user1){
        user=user1;
    }
    public void execute(){
        if(user.get_loc()!= null){
            Customer you=new Customer(user.get_loc());
            you.userSell();
        }
        else{
            System.out.println("Select Store location first");
        }
    }
}
