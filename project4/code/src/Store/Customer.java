
package Store;

import Accessories_Items.Cables;
import Accessories_Items.PracticeAmps;
import Accessories_Items.Strings;
import Clothing_Items.Bandanas;
import Clothing_Items.Hats;
import Clothing_Items.Shirts;
import Factory.RandomItem_Factory;
import Instruments_Items.Stringed;
import Instruments_Items.Stringed_Items.Bass;
import Instruments_Items.Stringed_Items.Guitar;
import Instruments_Items.Stringed_Items.Mandolin;
import Instruments_Items.Stringed_Items.StringDecorator;
import Instruments_Items.wind;
import Instruments_Items.wind_Items.Flute;
import Instruments_Items.wind_Items.Harmonica;
import Items.Items;
import Music_Items.PaperScore;
import Music_Items.Vinyl;
import Players_Items.MP3;
import Players_Items.RecordPlayer;
import Items.Players;


import java.util.*;

//imported from Store so that only 1 instance of Rng exists, instead of instantiating multiple instances


public class Customer {
    private int num;
    private String name;
    private Store location;
    private Random Rng = new Random();

    public void setName(int i, String s) {
        name = s;
        num = i;
    }

    public Customer(Store store1) {
        location = store1;
    }

    //private Random Rng= new Random();

    public int Buy() {
        //rand int from 0-16
        Store Store = location;
        int roll;
        int buy_count = 0;
        roll = Rng.nextInt(Store.get_ItemList().size());
        //pick item category based on RNG
        String wantedItem = Store.get_ItemList().get(roll);
        String content;

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
                //loop to find index of item being sold by store
                while (i < Store.get_InventorySize()) {
                    if (wantedItem.equals(Store.get_Item(i).get_name())) {
                        soldItem = Store.get_Item(i);
                        //saves index of chosen item
                        j = i;
                        i = Store.get_InventorySize();
                    } else {
                        i++;
                    }
                }
                //set item's daySold, salePrice
                soldItem.set_daySold(Store.get_daysPassed());
                soldItem.set_salePrice(Math.floor((soldItem.get_listPrice() * discount) * 100) / 100);
                //remove item from inventory, add to SoldItem ArrayList, add money to register
                Store.remove_Inventory(j);
                Store.add_soldItem(soldItem);
                Store.add_Register(soldItem.get_salePrice());

                //print statement depends on discount
                if (discount == 1) {
                    //System.out.printf(this.name + " " + this.num + " purchased " + soldItem.get_name() + " for $" + soldItem.get_salePrice() + ".\n");
                    content = this.name + " " + this.num + " purchased " + soldItem.get_name() + " for $" + soldItem.get_salePrice() + ".";
                    Store.notifyLoggers(content);
                    buy_count++;
                } else {
                    //System.out.printf(this.name + " " + this.num + " purchased " + soldItem.get_name() + " for $" + soldItem.get_salePrice() + " after a 10%% discount.\n");
                    content = this.name + " " + this.num + " purchased " + soldItem.get_name() + " for $" + soldItem.get_salePrice() + " after a 10%% discount.";
                    Store.notifyLoggers(content);
                    buy_count++;
                }

                //if soldItem is Stringed, call SellAccessories
                if (soldItem instanceof Stringed) {
                    StringDecorator temp = new StringDecorator((Stringed) soldItem);
                    buy_count += temp.SellAccessories();
                }

                Store.notifyTrackers("sold");

            } else {
                //if item is in stock but too expensive
                //System.out.printf(wantedItem + " was too expensive, " + this.name + " " + this.num + " left the store.\n");
                content = wantedItem + " was too expensive, " + this.name + " " + this.num + " left the store.";
                Store.notifyLoggers(content);
            }
        } else {
            //if item is out of stock
            //System.out.printf(wantedItem + " out of stock, " + this.name + " " + this.num + " left the store.\n");
            content = wantedItem + " out of stock, " + this.name + " " + this.num + " left the store.";
            Store.notifyLoggers(content);
        }
        return buy_count;
    }
    public int userBuy() {
        //rand int from 0-16
        Store Store = location;
        int roll;
        int buy_count = 0;
        roll = Rng.nextInt(Store.get_ItemList().size());
        //pick item category based on RNG
        String wantedItem = Store.get_ItemList().get(roll);
        String content;
        int item_index = 0;
        Items soldItem = null;
        Scanner reader = new Scanner(System.in);
        char response;
        double temp1 = 0.0;

        if (Store.check_stock(wantedItem) > 0) {
            for (Items i : Store.get_Inventory()) {
                if (i.get_name() == wantedItem) {
                    item_index = Store.get_Inventory().indexOf(i);
                    soldItem = i;
                }
            }
            System.out.println("Would you like to buy " + wantedItem + "for $" + soldItem.get_listPrice() + "? (Y/N)");
            response = reader.next().charAt(0);
            if (response == 'Y') {
                soldItem.set_daySold(Store.get_daysPassed());
                soldItem.set_salePrice(Math.floor((soldItem.get_listPrice()) * 100) / 100);
                //remove item from inventory, add to SoldItem ArrayList, add money to register
                Store.remove_Inventory(item_index);
                Store.add_soldItem(soldItem);
                Store.add_Register(soldItem.get_salePrice());
                System.out.println("You've successfully bought " + wantedItem + " for $" + soldItem.get_salePrice());
                buy_count++;
                if (soldItem instanceof Stringed) {
                    StringDecorator temp = new StringDecorator((Stringed) soldItem);
                    buy_count += temp.SellAccessories();
                }
            } else if (response == 'N') {
                temp1 = .9 * Math.floor((soldItem.get_listPrice()) * 100) / 100;
                System.out.println("Would you like to buy " + wantedItem + "for a 10% discounted price of $" + temp1 + "? (Y/N)");
                response = reader.next().charAt(0);
                if (response == 'Y') {
                    soldItem.set_daySold(Store.get_daysPassed());
                    soldItem.set_salePrice(temp1);
                    //remove item from inventory, add to SoldItem ArrayList, add money to register
                    Store.remove_Inventory(item_index);
                    Store.add_soldItem(soldItem);
                    Store.add_Register(soldItem.get_salePrice());
                    System.out.println("You've successfully bought " + wantedItem + " for $" + soldItem.get_salePrice());
                    buy_count++;
                    if (soldItem instanceof Stringed) {
                        StringDecorator temp = new StringDecorator((Stringed) soldItem);
                        buy_count += temp.SellAccessories();
                    }
                } else if (response == 'N') {
                    System.out.println("You've decided not to buy " + wantedItem);
                } else {
                    System.out.println("Invalid input");
                }
            }
        } else {
            System.out.println("You wanted to buy a(n) " + wantedItem + ", but it was out of stock");
        }
        return buy_count;
    }


    public int Sell() {
        Store Store = location;
        Items random_item = null;
        int roll;
        //using random.nextInt() to determine which item Customer is selling
        roll = Rng.nextInt(Store.get_ItemList().size() - 1);
        int day = Store.get_daysPassed();
        String content;
        int sell_count = 0;

        //Clerk randomly determines item's price, condition, and new/used
        double price = Rng.nextInt(49) + 1;
        int condition = Rng.nextInt(5);
        boolean used = Rng.nextBoolean();
        String name1;

        //purchase price is based on the condition
        price = price / (5 - condition);
        //System.out.printf("Value of roll: " + roll + " \n");
        RandomItem_Factory factory1 = new RandomItem_Factory(location);
        //match random int "roll" to each type of item
        switch (roll) {
            case 0: {
                //extra variables needed for constructor call
                name1 = "PaperScore";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 1: {
                //extra variables needed for constructor call
                name1 = "MusicCD";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 2: {
                name1 = "Vinyl";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 3: {
                name1 = "CDPlayer";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 4: {
                name1 = "RecordPlayer";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 5: {
                name1 = "MP3";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 6: {
                name1 = "Guitar";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 7: {
                //extra variable needed for constructor call
                name1 = "Bass";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 8: {
                //extra variables needed for constructor call
                name1 = "Mandolin";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 9: {
                //extra variables needed for constructor call
                name1 = "Flute";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 10: {
                //extra variables needed for constructor call
                name1 = "Harmonica";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 11: {
                name1 = "Hats";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 12: {
                name1 = "Shirts";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 13: {
                name1 = "Bandanas";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 14: {
                name1 = "PracticeAmps";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 15: {
                name1 = "Cables";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 16: {
                name1 = "Strings";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 17: {
                name1 = "Saxophone";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 18: {
                name1 = "Cassette";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 19: {
                name1 = "CassettePlayer";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 20: {
                name1 = "GigBag";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
        }
        //System.out.println(random_item.get_name()+" has been created for "+random_item.get_Store().get_location());

        double rand1 = Math.random();
        double rand2 = Math.random();
        boolean bought = false;
        double discount = 1;
        boolean Players_check = random_item instanceof Players;
        boolean Stringed_check = random_item instanceof Stringed;
        boolean Wind_check = random_item instanceof wind;

        //increases chance if player is equalized, stringed is tuned or wind is adjusted
        if (Players_check) {
            if (((Players) random_item).get_equalized() == true) {
                rand1 = rand1 * 1.1;
                rand2 = rand2 * 1.1;
            }
        } else if (Stringed_check) {
            if (((Stringed) random_item).get_tuned() == true) {
                rand1 = rand1 * 1.15;
                rand2 = rand2 * 1.15;
            }
        } else if (Wind_check) {
            if (((wind) random_item).get_adjusted() == true) {
                rand1 = rand1 * 1.2;
                rand2 = rand2 * 1.2;
            }
        }

        if (rand1 > 0.5) {
            bought = true;
        } else if (rand2 > 0.25) {
            bought = true;
            discount = 1.1;
        }

        //if dealing with items that are not clothing
        if (random_item.get_reorder() == true) {
            //determines discount and if Customer sells item
            //if Customer sells item
            if (bought) {
                //pay purchasePrice to customer, set dayArrived, add item to inventory
                random_item.set_purchasePrice(random_item.get_purchasePrice() * discount);
                Store.Pay(random_item.get_purchasePrice());
                random_item.set_dayArrived(Store.get_daysPassed());
                Store.add_Inventory(random_item);

                //print statement depends on discount
                if (discount == 1) {
                    //System.out.printf(Store.get_OnShift().get_name() + " bought a " + random_item.get_conditionS() + " condition " + random_item.get_name() + " from " + this.name + " " + this.num + " for $" + random_item.get_purchasePrice() + ".\n");
                    content = Store.get_OnShift().get_name() + " bought a " + random_item.get_conditionS() + " condition " + random_item.get_name() + " from " + this.name + " " + this.num + " for $" + random_item.get_purchasePrice() + ".";
                    Store.notifyLoggers(content);
                    sell_count++;

                } else {
                    //System.out.printf(Store.get_OnShift().get_name() + " bought a " + random_item.get_conditionS() + " condition " + random_item.get_name() + " from " + this.name + " " + this.num + " for $" + random_item.get_purchasePrice() + " after offering 10%% more.\n");
                    content = Store.get_OnShift().get_name() + " bought a " + random_item.get_conditionS() + " condition " + random_item.get_name() + " from " + this.name + " " + this.num + " for $" + random_item.get_purchasePrice() + " after offering 10%% more.";
                    Store.notifyLoggers(content);
                    sell_count++;
                }
                Store.notifyTrackers("purchased");
            } else {
                //if Customer doesn't accept Clerk's purchasePrice
                //System.out.printf(this.name + " " + this.num + " didn't accept the price, and left the store.\n");
                content = this.name + " " + this.num + " didn't accept the price, and left the store.";
                Store.notifyLoggers(content);
            }

        }
        //if we are dealing with clothings
        else {
            if (Store.check_stock(random_item.get_name()) != 0) {
                if (bought) {
                    //pay purchasePrice to customer, set dayArrived, add item to inventory
                    random_item.set_purchasePrice(random_item.get_purchasePrice() * discount);
                    Store.Pay(random_item.get_purchasePrice());
                    random_item.set_dayArrived(Store.get_daysPassed());
                    Store.add_Inventory(random_item);

                    //print statement depends on discount
                    if (discount == 1) {
                        //System.out.printf(Store.get_OnShift().get_name() + " bought a " + random_item.get_conditionS() + " condition " + random_item.get_name() + " from " + this.name + " " + this.num + " for $" + random_item.get_purchasePrice() + ".\n");
                        content = Store.get_OnShift().get_name() + " bought a " + random_item.get_conditionS() + " condition " + random_item.get_name() + " from " + this.name + " " + this.num + " for $" + random_item.get_purchasePrice() + ".";
                        Store.notifyLoggers(content);
                        sell_count++;
                    } else {
                        //System.out.printf(Store.get_OnShift().get_name() + " bought a " + random_item.get_conditionS() + " condition " + random_item.get_name() + " from " + this.name + " " + this.num + " for $" + random_item.get_purchasePrice() + " after offering 10%% more.\n");
                        content = Store.get_OnShift().get_name() + " bought a " + random_item.get_conditionS() + " condition " + random_item.get_name() + " from " + this.name + " " + this.num + " for $" + random_item.get_purchasePrice() + " after offering 10%% more.";
                        Store.notifyLoggers(content);
                        sell_count++;
                    }
                } else {
                    //if Customer doesn't accept Clerk's purchasePrice
                    //System.out.printf(this.name + " " + this.num + " didn't accept the price, and left the store.");
                    content = this.name + " " + this.num + " didn't accept the price, and left the store.";
                    Store.notifyLoggers(content);
                }

            }

        }
        return sell_count;
    }

    public int userSell() {
        Store Store = location;
        Items random_item = null;
        int roll;
        //using random.nextInt() to determine which item Customer is selling
        roll = Rng.nextInt(Store.get_ItemList().size() - 1);
        int day = Store.get_daysPassed();
        String content;
        int sell_count = 0;
        String name1;
        double price1;
        char response;
        Scanner reader=new Scanner(System.in);
        //Clerk randomly determines item's price, condition, and new/used
        double price = Rng.nextInt(49) + 1;
        int condition = Rng.nextInt(5);
        boolean used = Rng.nextBoolean();

        //purchase price is based on the condition
        price = price / (5 - condition);
        //System.out.printf("Value of roll: " + roll + " \n");
        RandomItem_Factory factory1 = new RandomItem_Factory(location);
        //match random int "roll" to each type of item
        switch (roll) {
            case 0: {
                //extra variables needed for constructor call
                name1 = "PaperScore";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 1: {
                //extra variables needed for constructor call
                name1 = "MusicCD";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 2: {
                name1 = "Vinyl";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 3: {
                name1 = "CDPlayer";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 4: {
                name1 = "RecordPlayer";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 5: {
                name1 = "MP3";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 6: {
                name1 = "Guitar";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 7: {
                //extra variable needed for constructor call
                name1 = "Bass";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 8: {
                //extra variables needed for constructor call
                name1 = "Mandolin";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 9: {
                //extra variables needed for constructor call
                name1 = "Flute";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 10: {
                //extra variables needed for constructor call
                name1 = "Harmonica";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 11: {
                name1 = "Hats";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 12: {
                name1 = "Shirts";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 13: {
                name1 = "Bandanas";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 14: {
                name1 = "PracticeAmps";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 15: {
                name1 = "Cables";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 16: {
                name1 = "Strings";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 17: {
                name1 = "Saxophone";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 18: {
                name1 = "Cassette";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 19: {
                name1 = "CassettePlayer";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
            case 20: {
                name1 = "GigBag";
                random_item = factory1.create_RandomItem(name1);
                break;
            }
        }
        //System.out.println(random_item.get_name()+" has been created for "+random_item.get_Store().get_location());
        name1=random_item.get_name();
        price1= random_item.get_purchasePrice();

        //if dealing with items that are not clothing
        if (random_item.get_reorder() == true) {
            //determines discount and if Customer sells item
            //if Customer sells item
            price1=Math.floor(price1)*100/100;
            System.out.println("Would you like to sell " + name1 + " at the price of $" + price1 + "? (Y/N)");
            response = reader.next().charAt(0);
            if (response == 'Y') {
                random_item.set_purchasePrice(Math.floor(random_item.get_purchasePrice())*100/100);
                Store.Pay(random_item.get_purchasePrice());
                random_item.set_dayArrived(Store.get_daysPassed());
                Store.add_Inventory(random_item);
                sell_count++;
                System.out.println("You successfully sold " + random_item.get_conditionS() + " condition " + random_item.get_name() + " at the price of $" + random_item.get_purchasePrice() + " to " + location.get_OnShift().get_name());
            } else if (response == 'N') {
                double temp2;
                temp2 = price1 * 1.1;
                temp2=Math.floor(temp2)*100/100;
                System.out.println("Would you like to sell " + name1 + " at the price of $" + temp2 + "? (Y/N)");
                response = reader.next().charAt(0);
                if (response == 'Y') {
                    random_item.set_purchasePrice(temp2);
                    Store.Pay(random_item.get_purchasePrice());
                    random_item.set_dayArrived(Store.get_daysPassed());
                    Store.add_Inventory(random_item);
                    sell_count++;
                    System.out.println("You successfully sold " + random_item.get_conditionS() + " condition " + random_item.get_name() + " at the negotiated price of $" + random_item.get_purchasePrice() + " to " + location.get_OnShift().get_name());
                } else if (response == 'N') {
                    System.out.println("You've decided not to sell the item");
                } else {
                    System.out.println("Invalid input");
                }
            } else {
                System.out.println("Invalid input");
            }
        }
        //if we are dealing with clothings
        else {
            if (Store.check_stock(random_item.get_name()) != 0) {
                System.out.println("Would you like to sell " + name1 + " at the price of $" + price1 + "? (Y/N)");
                response = reader.next().charAt(0);
                if (response == 'Y') {
                    random_item.set_purchasePrice(random_item.get_purchasePrice());
                    Store.Pay(random_item.get_purchasePrice());
                    random_item.set_dayArrived(Store.get_daysPassed());
                    Store.add_Inventory(random_item);
                    sell_count++;
                    System.out.println("You successfully sold " + random_item.get_conditionS() + " condition " + random_item.get_name() + " at the price of $" + random_item.get_purchasePrice() + " to " + location.get_OnShift().get_name());
                } else if (response == 'N') {
                    double temp2;
                    temp2 = price1 * 1.1;
                    System.out.println("Would you like to sell " + name1 + " at the price of $" + temp2 + "? (Y/N)");
                    response = reader.next().charAt(0);
                    if (response == 'Y') {
                        random_item.set_purchasePrice(temp2);
                        Store.Pay(random_item.get_purchasePrice());
                        random_item.set_dayArrived(Store.get_daysPassed());
                        Store.add_Inventory(random_item);
                        sell_count++;
                        System.out.println("You successfully sold " + random_item.get_conditionS() + " condition " + random_item.get_name() + " at the negotiated price of $" + random_item.get_purchasePrice() + " to " + location.get_OnShift().get_name());
                    } else if (response == 'N') {
                        System.out.println("You've decided not to sell the item");
                    } else {
                        System.out.println("Invalid input");
                    }
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
        return sell_count;
    }
}