package Accessories_Items;

import Items.Accessories;

public class PracticeAmps extends Accessories {
    private int wattage;

    public PracticeAmps(String name1, double price1,  boolean nou, int day1, int con1, int wats) {
        super(name1, price1, nou, day1, con1);
        wattage = wats;
    }

    public Integer get_wattage() {
        return wattage;
    }

    public void set_wattage(int wats1) {
        wattage = wats1;
    }
}

