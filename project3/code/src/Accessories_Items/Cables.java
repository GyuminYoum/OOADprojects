package Accessories_Items;

import Items.Accessories;

public class Cables extends Accessories {
    private double length;

    public Cables(String name1, double price1,  boolean nou, int day1, int con1,  double len1) {
        super(name1, price1, nou, day1, con1);
        length = len1;
    }

    public Double get_length() {
        return length;
    }

    public void set_length(double wats1) {
        length = wats1;
    }
}

