package Items.Accessories_Items;

import Items.Accessories;

public class PracticeAmps extends Accessories {
    private Integer wattage;

    public PracticeAmps(String name1, Float price1, Float price2, Boolean nou, Integer day1, Integer con1, Float price3, Integer day2, Integer wats) {
        super(name1, price1, price2, nou, day1, con1, price3, day2);
        wattage = wats;
    }

    public Integer get_wattage() {
        return wattage;
    }

    public void set_wattage(Integer wats1) {
        wattage = wats1;
    }
}

