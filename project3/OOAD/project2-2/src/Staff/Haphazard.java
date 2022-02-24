package Staff;

import Instruments_Items.Stringed;
import Instruments_Items.wind;
import Items.Items;
import Items.Players;

public class Haphazard implements Strategy{
    public void Tune(Items item) {
        double roll = Math.random();
        if (item instanceof wind) {
            if (roll <0.5) {
                ((wind) item).set_adjusted(!((wind) item).get_adjusted());
                //send to logger
            }
        } else if (item instanceof Stringed) {
            if (roll <0.5) {
                ((Stringed) item).set_tuned(!((Stringed) item).get_tuned());
                //send to logger
            }
        } else if (item instanceof Players) {
            if (roll <0.5) {
                ((Players) item).set_equalized(!((Players) item).get_equalized());
            }
        }

    }
}
