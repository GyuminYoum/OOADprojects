package Clothing_Items;

import Items.Clothing;

public class Shirts extends Clothing {
    private String shirtSize;

    public Shirts(String name1, double price1,  boolean nou, int day1, int con1,String size1) {
        super(name1, price1, nou, day1, con1);
        shirtSize = size1;
    }

    public String get_shirtSize() {
        return shirtSize;
    }

    public void set_shirtSize(String size1) {
        shirtSize = size1;
    }
}

