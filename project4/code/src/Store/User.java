package Store;

public class User {
    private Store location;
    public User(){
        location=null;
    }
    public void set_loc(Store store1){
        location=store1;
    }
    public Store get_loc(){
        return location;
    }
}
