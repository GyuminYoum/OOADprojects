package Items.Instruments_Items;

import Items.Instruments;

public class Stringed extends Instruments {
    private Boolean electric;

    public Stringed(String name1, Double price1,  Boolean nou, Integer day1, Integer con1, Boolean bool1) {
        super(name1, price1, nou, day1, con1);
        electric=bool1;
    }
    public Boolean get_electric(){
        return electric;
    }
    public void set_electric(Boolean bool1){
        electric=bool1;
    }
}

