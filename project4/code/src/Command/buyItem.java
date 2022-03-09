package Command;
import Staff.Clerk;
import Store.Customer;
import Store.Store;
import Store.User;

//user supplies the corresponding store, and customer object is instantiated to use the method userBuy
//if store location isn't picked, prints appropriate msg.
public class buyItem implements Command{
    User user;
    public buyItem(User usr){
        user=usr;
    }
    public void execute(){
        Store store=user.get_loc();
        if(store!= null){
            Customer you=new Customer(store);
            you.userBuy();
        }
        else{
            System.out.println("Select Store location first");
        }
    }
}
