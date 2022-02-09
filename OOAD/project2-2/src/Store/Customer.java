package Store;

import Items.Items;

import java.util.Random;

public class Customer {
    private int num;
    private String name;
    public void setName(int i, String s) {
        name = s;
        num = i;
    }

    public void Buy(){
        //rand int from 0-16
        int customerChoice = new Random().nextInt(17);
        String wantedItem = Store.get_ItemList()[customerChoice];

        if (Store.check_stock(wantedItem) > 0) {
            double rand1 = Math.random();
            double rand2 = Math.random();
            double discount = 1;
            boolean sold = false;

            //determine salePrice and if item sells
            if (rand1 < 0.5) {
                sold = true;
            } else if (rand2 < 0.75) {
                sold = true;
                discount = 0.9;
            }

            if (sold) {
                int i = 0;
                int j = 0;
                Items soldItem = null;
                while (i < Store.get_InventorySize()) {
                    if (wantedItem.equals(Store.get_Item(i).get_name())) {
                        soldItem = Store.get_Item(i);
                        j = i;
                        i = Store.get_InventorySize();
                    } else {
                        i++;
                    }
                }
                soldItem.set_daySold(Store.get_daysPassed());
                soldItem.set_salePrice(Math.floor((soldItem.get_listPrice() * discount)*100)/100);
                Store.remove_Inventory(j);
                Store.add_soldItem(soldItem);
                Store.add_Register(soldItem.get_salePrice());

                if (discount == 1) {
                    System.out.printf(this.name + " " + this.num + " purchased " + soldItem.get_name() + " for $" + soldItem.get_salePrice() + ".\n");
                } else {
                    System.out.printf(this.name + " " + this.num + " purchased " + soldItem.get_name() + " for $" + soldItem.get_salePrice() + " after a 10%% discount.\n");
                }

            } else {
                System.out.printf(wantedItem + " was too expensive, " + this.name + " " + this.num + " left the store.\n");
            }



        } else {
            System.out.printf(wantedItem + " out of stock, " + this.name + " " + this.num + " left the store.\n");
        }



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
        /*Items random_item;
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
        }*/
    }


}
