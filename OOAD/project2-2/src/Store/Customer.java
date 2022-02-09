package Store;

import Items.Items;

import java.util.ArrayList;
import java.util.Random;

public class Customer {
    public void Buy(){
        ArrayList<String> items = new ArrayList<String>();

        items.add("Paper Score");
        items.add("CD");
        items.add("Vinyl");
        items.add("CD Player");
        items.add("Record Player");
        items.add("MP3");
        items.add("Guitar");
        items.add("Bass");
        items.add("Mandolin");
        items.add("Flute");
        items.add("Harmonica");
        items.add("Hat");
        items.add("Shirt");
        items.add("Bandana");
        items.add("Practice Amp");
        items.add("Cable");
        items.add("String");

        //pick random item that customer wants
        //if not in stock, leave
        //else 50% chance to buy

        //if not buy, offer 10% discount
            //75% chance to buy

        //if buy:
            //update daySold and salePrice
            //remove from store inventory
            //add to soldItems collection
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
                Boolean used=Rng.nextBoolean();
                int names=Rng.nextInt(3);
                //random_item= new PaperScore("PaperScore", pricef,used,day,condition,band_names[names],album_names[names]);
        }
    }


}
