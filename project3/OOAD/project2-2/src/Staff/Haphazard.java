package Staff;

import Instruments_Items.Stringed;
import Instruments_Items.wind;
import Items.Items;
import Items.Players;
import Store.Store;

public class Haphazard implements Strategy{
    public void Tune(Items item) {
        double roll = Math.random();
        boolean temp = false;
        //if item is type wind
        if (item instanceof wind) {
            if (roll <0.5) {
                ((wind) item).set_adjusted(!((wind) item).get_adjusted());
                if (((wind) item).get_adjusted()) {
                    System.out.printf(Store.get_OnShift().get_name() + " adjusted " + item.get_name() + ".\n");
                } else {
                    System.out.printf(Store.get_OnShift().get_name() + " un-adjusted " + item.get_name() + ".\n");
                    temp = true;
                }
            }
        //if item is type Stringed
        } else if (item instanceof Stringed) {
            if (roll <0.5) {
                ((Stringed) item).set_tuned(!((Stringed) item).get_tuned());
                if (((Stringed) item).get_tuned()) {
                    System.out.printf(Store.get_OnShift().get_name() + " tuned " + item.get_name() + ".\n");
                } else {
                    System.out.printf(Store.get_OnShift().get_name() + " un-tuned " + item.get_name() + ".\n");
                    temp = true;
                }
            }
        //if item is type Players
        } else if (item instanceof Players) {
            if (roll <0.5) {
                ((Players) item).set_equalized(!((Players) item).get_equalized());
                if (((Players) item).get_equalized()) {
                    System.out.printf(Store.get_OnShift().get_name() + " equalized " + item.get_name() + ".\n");
                } else {
                    System.out.printf(Store.get_OnShift().get_name() + " un-equalized " + item.get_name() + ".\n");
                    temp = true;
                }
            }
        }

        if (temp) {
            roll = Math.random();
            if (roll < 0.1) {
                if (item.get_condition() == 0) {
                    //break item
                    int i = 0;
                    //remove item with matching condition from Inventory
                    while (i < Store.get_InventorySize()) {
                        if ( item.get_name().equals(Store.get_Item(i).get_name()) && Store.get_Item(i).get_condition() == 0) {
                            Store.remove_Inventory(i);
                            i = Store.get_InventorySize();
                        }
                        i++;
                    }
                    System.out.printf(Store.get_OnShift().get_name() + " has thrown away a(n) " + item.get_name() + "due to less than poor condition. ");

                } else {
                    item.set_condition(item.get_condition() - 1);
                    System.out.printf(Store.get_OnShift().get_name() + "has damaged a(n) " + item.get_name() + ".");
                }
            }
        }

    }
}
