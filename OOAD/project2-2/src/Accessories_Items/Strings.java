package Accessories_Items;

import Items.Accessories;

public class Strings extends Accessories {
    private String type;

    public Strings(String name1, double price1, boolean nou, int day1, int con1, String type1) {
        super(name1, price1, nou, day1, con1);
        type = type1;
    }

    public String get_type() {
        return type;
    }

    public void set_type(String type1) {
        type = type1;
    }
}
