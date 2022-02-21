package Clothing_Items;

import Items.Clothing;

public class Hats extends Clothing {
    private double hatSize;
    public Hats(String name1, double price1,Boolean nou, int day1, int con1, double size1) {
        super(name1, price1, nou, day1, con1);
        hatSize = size1;
    }
    public Double get_hatSize(){
        return hatSize;
    }
    public void set_hatSize(double size1){
        hatSize=size1;
    }
}

