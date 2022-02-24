package Instruments_Items;

import Items.Instruments;

public class wind extends Instruments {
    boolean adjusted;
    public wind(String name1, double price1, boolean nou, int day1, int con1) {
        super(name1, price1, nou, day1, con1);
        adjusted=false;
    }
    public boolean get_adjusted(){
        return adjusted;
    }
    public void set_adjusted(boolean bool1){
        adjusted=bool1;
    }
}
