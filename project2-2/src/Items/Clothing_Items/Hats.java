package Items.Clothing_Items;

import Items.Clothing;

public class Hats extends Clothing {
    private Float hatSize;
    public Hats(String name1, Float price1, Float price2, Boolean nou, Integer day1, Integer con1, Float price3, Integer day2, Float size1) {
        super(name1, price1, price2, nou, day1, con1, price3, day2);
        hatSize = size1;
    }
    public Float get_hatSize(){
        return hatSize;
    }
    public void set_hatSize(Float size1){
        hatSize=size1;
    }
}

