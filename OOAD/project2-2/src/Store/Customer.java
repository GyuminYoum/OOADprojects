package Store;

import Items.Items;

import java.util.Random;

public class Customer {
    public void Buy(){

    }

    public void Sell(){
        Items random_item;
        int roll;
        Random Rng= new Random();
        roll=Rng.nextInt(17);
        int day=Store.get_daysPassed();
        switch(roll){
            case 0:
                String[] band_names={"band1","band2","band3"};
                String[] album_names={"album1","album2","album3"};
                int price=Rng.nextInt(49)+1;
                //Double price1=price;
                int condition=Rng.nextInt(5);
                boolean used=Rng.nextBoolean();
                int names=Rng.nextInt(3);
                //random_item= new PaperScore("PaperScore", pricef,used,day,condition,band_names[names],album_names[names]);
        }
    }


}
