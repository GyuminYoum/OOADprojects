package Items.Accessories_Items;

import Items.Accessories;

public class Cables extends Accessories {
    private Float length;

    public Cables(String name1, Float price1, Float price2, Boolean nou, Integer day1, Integer con1, Float price3, Integer day2, Float len1) {
        super(name1, price1, price2, nou, day1, con1, price3, day2);
        length = len1;
    }

    public Float get_length() {
        return length;
    }

    public void set_length(Float wats1) {
        length = wats1;
    }
}

