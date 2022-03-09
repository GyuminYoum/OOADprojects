package Command;
import Staff.Clerk;
import Store.Customer;
import Store.Store;
import Store.User;

//user supplies the location via get_loc
//customer object is instantiated to use userSell
//if the user's store hasn't been picked then print appropriate msg
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
