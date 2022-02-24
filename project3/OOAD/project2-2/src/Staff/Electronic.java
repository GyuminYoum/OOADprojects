package Staff;

import Instruments_Items.Stringed;
import Instruments_Items.wind;
import Items.Items;
import Items.Players;
import Store.Store;

public class Electronic implements Strategy{
    public void Tune(Items item) {
        boolean temp = false;
        if (item instanceof wind) {
            ((wind) item).set_adjusted(true);
            System.out.printf(Store.get_OnShift().get_name() + " adjusted " + item.get_name() + ".\n");
        } else if (item instanceof Stringed) {
            ((Stringed) item).set_tuned(true);
            System.out.printf(Store.get_OnShift().get_name() + " tuned " + item.get_name() + ".\n");
        } else if (item instanceof Players) {
            ((Players) item).set_equalized(true);
            System.out.printf(Store.get_OnShift().get_name() + " equalized " + item.get_name() + ".\n");
        }
    }
}
