package Instruments_Items;

import Items.Instruments;

public class Stringed extends Instruments {
    private boolean electric;

    public Stringed(String name1, double price1,  boolean nou, int day1, int con1, boolean bool1) {
        super(name1, price1, nou, day1, con1);
        electric=bool1;
    }
    public boolean get_electric(){
        return electric;
    }
    public void set_electric(boolean bool1){
        electric=bool1;
    }
}

