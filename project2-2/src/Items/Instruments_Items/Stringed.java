package Items.Instruments_Items;

import Items.Instruments;

public class Stringed extends Instruments {
    private Boolean electric;

    public Stringed(String name1, Float price1, Float price2, Boolean nou, Integer day1, Integer con1, Float price3, Integer day2, Boolean bool1) {
        super(name1, price1, price2, nou, day1, con1, price3, day2);
        electric=bool1;
    }
    public Boolean get_electric(){
        return electric;
    }
    public void set_electric(Boolean bool1){
        electric=bool1;
    }
}

