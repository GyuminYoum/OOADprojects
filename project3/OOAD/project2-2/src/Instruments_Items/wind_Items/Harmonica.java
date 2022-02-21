package Instruments_Items.wind_Items;

import Instruments_Items.wind;

public class Harmonica extends wind {
    private String Key;

    public Harmonica(String name1, double price1,  boolean nou, int day1, int con1, String key1) {
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
