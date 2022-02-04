package Items.Accessories_Items;

import Items.Accessories;

public class Strings extends Accessories {
    private String type;

    public Strings(String name1, Float price1, Float price2, Boolean nou, Integer day1, Integer con1, Float price3, Integer day2, String type1) {
        super(name1, price1, price2, nou, day1, con1, price3, day2);
        type = type1;
    }

    public String get_type() {
        return type;
    }

    public void set_type(String type1) {
        type = type1;
    }
}
