package Staff;

import Instruments_Items.Stringed;
import Instruments_Items.wind;
import Items.Items;
import Items.Players;
import Store.Store;

public class Manual implements Strategy{
    public boolean Tune(Items item) {
        double roll = Math.random();
        boolean temp = false;
        String content;
        if (item instanceof wind) {
            //if item is of type wind
            if (((wind) item).get_adjusted()) {
                if (roll < 0.2) {
                    ((wind) item).set_adjusted(false);
                    //System.out.printf(Store.get_OnShift().get_name() + " un-adjusted " + item.get_name() + ".\n");
                    content=Store.get_OnShift().get_name() + " un-adjusted " + item.get_name() + ".";
                    Store.notifyLoggers(content);
                    temp = true;
                }
            } else {
                if (roll < 0.8) {
                    ((wind) item).set_adjusted(true);
                    //System.out.printf(Store.get_OnShift().get_name() + " adjusted " + item.get_name() + ".\n");
                    content=Store.get_OnShift().get_name() + " adjusted " + item.get_name() + ".";
                    Store.notifyLoggers(content);


                }
            }
        } else if (item instanceof Stringed) {
            //if item is type Stringed
            if (((Stringed) item).get_tuned()) {
                if (roll < 0.2) {
                    ((Stringed) item).set_tuned(false);
                    //System.out.printf(Store.get_OnShift().get_name() + " un-tuned " + item.get_name() + ".\n");
                    content=Store.get_OnShift().get_name() + " un-tuned " + item.get_name() + ".";
                    Store.notifyLoggers(content);
                    temp = true;
                }
            } else {
                if (roll < 0.8) {
                    ((Stringed) item).set_tuned(true);
                    //System.out.printf(Store.get_OnShift().get_name() + " tuned " + item.get_name() + ".\n");
                    content=Store.get_OnShift().get_name() + " tuned " + item.get_name() + ".";
                    Store.notifyLoggers(content);
                }
            }
        //if item is type Players
        } else if (item instanceof Players) {
            if (((Players) item).get_equalized()) {
                if (roll < 0.2) {
                    ((Players) item).set_equalized(false);
                    //System.out.printf(Store.get_OnShift().get_name() + " un-equalized " + item.get_name() + ".\n");
                    content=Store.get_OnShift().get_name() + " un-equalized " + item.get_name() + ".";
                    Store.notifyLoggers(content);
                    temp = true;
                }
            } else {
                if (roll < 0.8) {
                    ((Players) item).set_equalized(true);
                    //System.out.printf(Store.get_OnShift().get_name() + " equalized " + item.get_name() + ".\n");
                    content=Store.get_OnShift().get_name() + " equalized " + item.get_name() + ".";
                    Store.notifyLoggers(content);
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
                    //System.out.printf(Store.get_OnShift().get_name() + " has thrown away a(n) " + item.get_name() + "due to less than poor condition. ");
                    content=Store.get_OnShift().get_name() + " has thrown away a(n) " + item.get_name() + "due to less than poor condition.";
                    Store.notifyLoggers(content);
                    Store.notifyTrackers("damaged");
                } else {
                    item.set_condition(item.get_condition() - 1);
                    //System.out.printf(Store.get_OnShift().get_name() + "has damaged a(n) " + item.get_name() + ".");
                    content=Store.get_OnShift().get_name() + "has damaged a(n) " + item.get_name() + ".";
                    Store.notifyLoggers(content);
                    Store.notifyTrackers("damaged");
                }
                return false;
            }
        }
        return true;
    }
}
