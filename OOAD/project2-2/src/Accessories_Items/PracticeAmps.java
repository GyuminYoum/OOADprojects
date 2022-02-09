package Accessories_Items;

import Items.Accessories;

public class PracticeAmps extends Accessories {
    private Integer wattage;

    public PracticeAmps(String name1, Double price1,  Boolean nou, Integer day1, Integer con1, Integer wats) {
        super(name1, price1, nou, day1, con1);
        wattage = wats;
    }

    public Integer get_wattage() {
        return wattage;
    }

    public void set_wattage(Integer wats1) {
        wattage = wats1;
    }
}

