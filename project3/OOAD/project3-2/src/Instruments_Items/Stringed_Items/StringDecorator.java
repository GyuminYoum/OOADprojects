package Instruments_Items.Stringed_Items;

import Instruments_Items.Stringed;
import Store.Store;

import static Store.Store.Rng;

public class StringDecorator extends Stringed {
    private Stringed item_;

    public StringDecorator(Stringed item) {
        super(item.get_name(), item.get_listPrice(), item.get_newOrUsed(), item.get_dayArrived(), item.get_condition(), item.get_electric());
        item_ = item;
    }
    public void SellAccessories() {
        double roll;
        double roll1;
        double electric = 0.0;

        //electric modifier for selling accessories
        if (!item_.get_electric()) {
            electric = 0.1;
        }
        //roll = Rng.nextInt();
        roll = Math.random();
        if (roll < 0.2 - electric) {
            if (Store.check_stock("GigBag") > 0) {
                //sell 1 gigbag
                Store.Sell("GigBag",1);
            }
        }
        roll1 = Math.random();
        if (roll1 < 0.25 - electric) {
            if (Store.check_stock("PracticeAmps") > 0) {
                //sell 1 pAmp
                Store.Sell("PracticeAmps", 1);
            }
        }
        roll = Math.random();
        if (roll < 0.3 - electric) {
            //0-0.15: sell 1 cable, 0.15-0.3: sell 2
            //if not electric, 0-0.1: sell 1, 0.1-0.2: sell 2
            if (roll < 0.15 - (electric/2)) {
                //sell 1
                Store.Sell("Cables", 1);
            } else {
                //sell 2
                Store.Sell("Cables", 2);
            }
        }
        roll1 = Math.random();
        //sell strings
        if (roll1 < 0.4 - electric) {
            if (roll1 < 0.133 - (electric/3)) {
                //sell 1
                Store.Sell("Strings", 1);
            } else if (roll1 < 0.267 - (2*electric/3)) {
                //sell 2
                Store.Sell("Strings", 2);
            } else {
                //sell 3
                Store.Sell("Strings", 3);
            }
        }
    }

}
