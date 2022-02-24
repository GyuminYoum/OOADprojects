package Staff;

import Instruments_Items.Stringed;
import Instruments_Items.wind;
import Items.Items;
import Items.Players;

public class Electronic implements Strategy{
    public void Tune(Items item) {
        if (item instanceof wind) {
            ((wind) item).set_adjusted(true);
        } else if (item instanceof Stringed) {
            ((Stringed) item).set_tuned(true);
        } else if (item instanceof Players) {
            ((Players) item).set_equalized(true);
        }
    }
}
