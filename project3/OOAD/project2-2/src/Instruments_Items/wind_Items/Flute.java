package Instruments_Items.wind_Items;

import Instruments_Items.wind;

public class Flute extends wind {
    private String type;

    public Flute(String name1, double price1, boolean nou, int day1, int con1, String type1) {
        super(name1, price1, nou, day1, con1);
        type=type1;
    }
    public String get_type(){
        return type;
    }
    public void set_type(String name1){
        type=name1;
    }
}


