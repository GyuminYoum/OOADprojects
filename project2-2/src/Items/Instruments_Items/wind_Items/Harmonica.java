package Items.Instruments_Items.wind_Items;

import Items.Instruments_Items.wind;

public class Harmonica extends wind {
    private String Key;

    public Harmonica(String name1, Float price1, Float price2, Boolean nou, Integer day1, Integer con1, Float price3, Integer day2, String key1) {
        super(name1, price1, price2, nou, day1, con1, price3, day2);
        Key = key1;
    }
    public String get_Key(){
        return Key;
    }
    public void set_Key(String name1){
        Key=name1;
    }

}
