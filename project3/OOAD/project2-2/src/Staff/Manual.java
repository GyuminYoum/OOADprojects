package Staff;

import Instruments_Items.Stringed;
import Instruments_Items.wind;
import Items.Items;
import Items.Players;

public class Manual implements Strategy{
    public void Tune(Items item) {
        double roll = Math.random();
        if (item instanceof wind) {
            //if item is of type wind
            if (((wind) item).get_adjusted()) {
                if (roll < 0.2) {
                    ((wind) item).set_adjusted(false);
                }
            } else {
                if (roll < 0.8) {
                    ((wind) item).set_adjusted(true);
                }
            }
        } else if (item instanceof Stringed) {
            //if item is type Stringed
            if (((Stringed) item).get_tuned()) {
                if (roll < 0.2) {
                    ((Stringed) item).set_tuned(false);
                }
            } else {
                if (roll < 0.8) {
                    ((Stringed) item).set_tuned(true);
                }
            }
        } else if (item instanceof Players) {
            if (((Players) item).get_equalized()) {
                if (roll < 0.2) {
                    ((Players) item).set_equalized(false);
                }
            } else {
                if (roll < 0.8) {
                    ((Players) item).set_equalized(true);
                }
            }
        }
    }
}
