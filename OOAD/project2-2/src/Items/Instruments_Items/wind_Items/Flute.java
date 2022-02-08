package Items.Instruments_Items.wind_Items;

import Items.Instruments_Items.wind;

public class Flute extends wind {
    private String type;

    public Flute(String name1, Double price1, Boolean nou, Integer day1, Integer con1, String type1) {
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

