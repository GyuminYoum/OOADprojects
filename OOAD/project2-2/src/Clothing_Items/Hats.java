package Clothing_Items;

import Items.Clothing;

public class Hats extends Clothing {
    private Double hatSize;
    public Hats(String name1, Double price1,Boolean nou, Integer day1, Integer con1, Double size1) {
        super(name1, price1, nou, day1, con1);
        hatSize = size1;
    }
    public Double get_hatSize(){
        return hatSize;
    }
    public void set_hatSize(Double size1){
        hatSize=size1;
    }
}

