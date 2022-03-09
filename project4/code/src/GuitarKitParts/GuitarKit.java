package GuitarKitParts;
import Items.Items;

import java.util.ArrayList;

//base guitar kit initializes parts
//then addParts method will add items to the list and update the price of it.
public class GuitarKit extends Items{
    private ArrayList<Items> parts;
    public GuitarKit(String name1, double price1, boolean nou, int day1, int con1) {
        super(name1, price1, nou, day1, con1);
        parts=new ArrayList<Items>();
    }
    public void addParts(Items item1){
        parts.add(item1);
        this.set_listPrice(this.get_listPrice()+item1.get_listPrice());
    }
}
