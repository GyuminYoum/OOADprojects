package Items.Accessories_Items;

import Items.Accessories;

public class Strings extends Accessories {
    private String type;

    public Strings(String name1, Double price1, Boolean nou, Integer day1, Integer con1, String type1) {
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
