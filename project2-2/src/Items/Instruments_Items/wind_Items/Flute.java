package Items.Instruments_Items.wind_Items;

import Items.Instruments_Items.wind;

public class Flute extends wind {
    private String type;

    public Flute(String name1, Float price1, Float price2, Boolean nou, Integer day1, Integer con1, Float price3, Integer day2, String type1) {
        super(name1, price1, price2, nou, day1, con1, price3, day2);
        type=type1;
    }
    public String get_type(){
        return type;
    }
    public void set_type(String name1){
        type=name1;
    }
}

