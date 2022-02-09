
package Store;

import Accessories_Items.Cables;
import Accessories_Items.PracticeAmps;
import Accessories_Items.Strings;
import Clothing_Items.Bandanas;
import Clothing_Items.Hats;
import Clothing_Items.Shirts;
import Instruments_Items.Stringed_Items.Bass;
import Instruments_Items.Stringed_Items.Guitar;
import Instruments_Items.Stringed_Items.Mandolin;
import Instruments_Items.wind_Items.Flute;
import Instruments_Items.wind_Items.Harmonica;
import Items.Items;
import Music_Items.PaperScore;
import Music_Items.Vinyl;
import Players_Items.MP3;
import Players_Items.RecordPlayer;

import static Store.Store.Rng;

public class Customer {
    private int num;
    private String name;
    public void setName(int i, String s) {
        name = s;
        num = i;
    }

    //private Random Rng= new Random();

    public void Buy(){
        //rand int from 0-16
        int roll;
        roll= Rng.nextInt(17);
        String wantedItem = Store.get_ItemList()[roll];

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

                //print statement depending on discount
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
    }

    public void Sell(){
        Items random_item = null;
        int roll;

        roll=Rng.nextInt(17);
        int day=Store.get_daysPassed();

        double price =Rng.nextInt(49)+1;
        int condition=Rng.nextInt(5);
        boolean used=Rng.nextBoolean();

        //purchase price is based on the condition
        price = price / (5 - condition);
        //System.out.printf("Value of roll: " + roll + " \n");

        switch(roll){
            case 0: {
                int names = Rng.nextInt(3);
                int names1 = Rng.nextInt(3);
                String[] band_names = {"band1", "band2", "band3"};
                String[] album_names = {"album1", "album2", "album3"};
                random_item = new PaperScore("PaperScore", price,used,day,condition, band_names[names], album_names[names1]);
                break;
            }
            case 1:{
                int names=Rng.nextInt(3);
                int names1=Rng.nextInt(3);
                String[] band_names={"band1","band2","band3"};
                String[] album_names={"album1","album2","album3"};
                random_item= new Music_Items.CD("MusicCD", price,used,day,condition,band_names[names],album_names[names1]);
                break;
            }
            case 2: {
                int names = Rng.nextInt(3);
                int names1 = Rng.nextInt(3);
                String[] band_names = {"band1", "band2", "band3"};
                String[] album_names = {"album1", "album2", "album3"};
                random_item = new Vinyl("Vinyl", price,used,day,condition, band_names[names], album_names[names1]);
                break;
            }
            case 3: {
                random_item = new Players_Items.CD("CDPlayer", price, used, day, condition);
                break;
            }
            case 4: {
                random_item = new RecordPlayer("RecordPlayer", price, used, day, condition);
                break;
            }
            case 5: {
                random_item = new MP3("MP3", price, used, day, condition);
                break;
            }
            case 6: {
                boolean electric=Rng.nextBoolean();
                random_item = new Guitar("Guitar", price, used, day, condition, electric);
                break;
            }
            case 7: {
                boolean electric=Rng.nextBoolean();
                random_item = new Bass("Bass", price, used, day, condition, electric);
                break;
            }
            case 8: {
                boolean electric=Rng.nextBoolean();
                random_item = new Mandolin("Mandolin", price, used, day, condition, electric);
                break;
            }
            case 9: {
                int type = Rng.nextInt(6);
                String[] typeF = {"Standard", "Piccolo", "Plastic", "Wooden", "Alto", "Bass"};
                random_item = new Flute("Flute", price, used, day, condition, typeF[type]);
                break;
            }
            case 10: {
                int key = Rng.nextInt(6);
                String[] keyH = {"A", "B", "C", "D", "E", "F", "G"};
                random_item = new Harmonica("Harmonica", price, used, day, condition, keyH[key]);
                break;
            }
            case 11: {
                double hatSize = (Rng.nextInt(4)) + 5;
                double temp = Rng.nextInt(4);
                temp /= 4;
                hatSize += temp;
                random_item = new Hats("Hats", price, used, day, condition, hatSize);
                break;
            }
            case 12: {
                int size = Rng.nextInt(5);
                String[] shirtSize = {"XS", "S", "M", "L", "XL"};
                random_item = new Shirts("Shirts", price, used, day, condition, shirtSize[size]);
                break;
            }
            case 13: {
                random_item = new Bandanas("Bandanas", price, used, day, condition);
                break;
            }
            case 14: {
                int wattage = (Rng.nextInt(100) + 1) * 5;
                random_item = new PracticeAmps("PracticeAmps", price, used, day, condition, wattage);
                break;
            }
            case 15: {
                double length = Rng.nextInt(25) + 1;
                random_item = new Cables("Cables", price, used, day, condition, length);
                break;
            }
            case 16: {
                int type = Rng.nextInt(5);
                String[] typeS = {"Violin", "Cello", "Guitar", "Bass", "Mandolin"};
                random_item = new Strings("Strings", price, used, day, condition, typeS[type]);
                break;
            }
        }

        double rand1 = Math.random();
        double rand2 = Math.random();
        boolean bought = false;
        double discount = 1;

        if (rand1 < 0.5) {
            bought = true;
        }
        else if (rand2 < 0.75) {
            bought = true;
            discount = 1.1;
        }

        if (bought) {
            if (Store.get_Register() - random_item.get_purchasePrice() < 0) {
                Store.get_OnShift().GoToBank();
            }
            random_item.set_dayArrived(Store.get_daysPassed());
            Store.set_Register(Store.get_Register() - random_item.get_purchasePrice());

            Store.add_Inventory(random_item);

            if (discount == 1) {
                System.out.printf(Store.get_OnShift().get_name() + " bought a " + random_item.get_conditionS() + " condition " + random_item.get_name() + " from " + this.name + " " + this.num + " for $" + random_item.get_purchasePrice() + ".\n");
            } else {
                System.out.printf(Store.get_OnShift().get_name() + " bought a " + random_item.get_conditionS() + " condition " + random_item.get_name() + " from " + this.name + " " + this.num + " for $" + random_item.get_purchasePrice() + " after offering 10%% more.\n");
            }

        } else {
            System.out.printf(this.name + " " + this.num + " didn't accept the price, and left the store.\n");
        }


    }


}
