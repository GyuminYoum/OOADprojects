package Instruments_Items.Stringed_Items;

import Instruments_Items.Stringed;

public class StringDecorator extends Stringed {

    public StringDecorator(Stringed item) {
        super(item.get_name(), item.get_listPrice(), item.get_newOrUsed(), item.get_dayArrived(), item.get_condition(), item.get_electric());
    }

}
