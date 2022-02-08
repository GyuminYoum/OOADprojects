package Items.Instruments_Items.wind_Items;

import Items.Instruments_Items.wind;

public class Harmonica extends wind {
    private String Key;

    public Harmonica(String name1, Double price1,  Boolean nou, Integer day1, Integer con1, String key1) {
        super(name1, price1, nou, day1, con1);
        Key = key1;
    }
    public String get_Key(){
        return Key;
    }
    public void set_Key(String name1){
        Key=name1;
    }

}
