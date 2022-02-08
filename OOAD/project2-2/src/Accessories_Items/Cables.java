package Accessories_Items;

import Items.Accessories;

public class Cables extends Accessories {
    private Double length;

    public Cables(String name1, Double price1,  Boolean nou, Integer day1, Integer con1,  Double len1) {
        super(name1, price1, nou, day1, con1);
        length = len1;
    }

    public Double get_length() {
        return length;
    }

    public void set_length(Double wats1) {
        length = wats1;
    }
}

