package GuitarKitParts;
import Items.Items;

import java.util.ArrayList;

public class GuitarKit extends Items{
    private ArrayList<Items> parts=new ArrayList<Items>();
    public GuitarKit(String name1, double price1, boolean nou, int day1, int con1) {
        super(name1, price1, nou, day1, con1);
    }
    public void addParts(Items item1){
        parts.add(item1);
        this.set_listPrice(this.get_listPrice()+item1.get_listPrice());
    }
}
