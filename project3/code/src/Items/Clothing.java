package Items;

//clothing is not ordered any more.
public class Clothing extends Items {

    public Clothing(String name1, double price1,  boolean nou, int day1, int con1) {
        super(name1, price1, nou, day1, con1);
        set_reorder(false);
    }
}

