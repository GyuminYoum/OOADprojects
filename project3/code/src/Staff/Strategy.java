package Staff;

import Items.Items;

//implements Tune method depending on Clerk's assigned strategy
//each tuning can be observed in electronic, haphazard, and manual all in staff package.
public interface Strategy{
    public boolean Tune(Items item);
}
