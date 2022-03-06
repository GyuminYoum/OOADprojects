package Factory;

import Accessories_Items.Cables;
import Accessories_Items.GigBag;
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
import Instruments_Items.wind_Items.Saxophone;
import Items.Items;
import Music_Items.CD;
import Music_Items.Cassette;
import Music_Items.PaperScore;
import Music_Items.Vinyl;
import Players_Items.CassettePlayer;
import Players_Items.MP3;
import Players_Items.RecordPlayer;
import Store.Store;

import java.util.Random;

public class RandomItem_Factory {
    private Store location;

    public RandomItem_Factory(Store store1){
        location=store1;
    }
    public Items create_RandomItem(String name1){
        Random rng=new Random();
        String name=name1;
        double purchasePrice=1+(50)*rng.nextDouble();
        purchasePrice=Math.round(purchasePrice*100)/100.0;
        boolean newOrUsed=rng.nextBoolean();
        int condition=rng.nextInt(4);
        String[] band_list={"band1","band2","band3"};
        String band_name=band_list[rng.nextInt(3)];
        String[] album_list={"album1","album2","album3"};
        String album_name=album_list[rng.nextInt(3)];
        int day_arrived=rng.nextInt(3)+1+location.get_daysPassed();
        boolean Electric=rng.nextBoolean();;
        String[] type={"Standard", "Piccolo", "Plastic"};
        String Flute_Type=type[rng.nextInt(3)];
        String[] key={"A","Bb","C"};
        String Harmonica_Key=key[rng.nextInt(3)];
        double Hat_Size=1+(9)*rng.nextDouble();
        String[] size={"S","M","L"};
        String Shirt_Size=size[rng.nextInt(3)];
        int wattage=rng.nextInt(100)+1;
        double length=1+(99)*rng.nextDouble();;
        String[] type1={"Violin", "Cello", "Guitar"};
        String string_type=type1[rng.nextInt(3)];
        boolean equalized=rng.nextBoolean();
        boolean tuned=rng.nextBoolean();
        boolean adjusted= rng.nextBoolean();

        if (name1=="PaperScore"){
            PaperScore item1=new PaperScore(name,purchasePrice,newOrUsed, day_arrived,condition,band_name,album_name);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="MusicCD"){
            CD item1=new CD(name,purchasePrice,newOrUsed, day_arrived,condition,band_name,album_name);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Vinyl"){
            Vinyl item1=new Vinyl(name,purchasePrice,newOrUsed, day_arrived,condition,band_name,album_name);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Cassette"){
            Cassette item1=new Cassette(name,purchasePrice,newOrUsed, day_arrived,condition,band_name,album_name);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="CDPlayer"){
            Players_Items.CD item1=new Players_Items.CD(name,purchasePrice,newOrUsed, day_arrived,condition);
            item1.set_equalized(equalized);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="RecordPlayer"){
            RecordPlayer item1=new RecordPlayer(name,purchasePrice,newOrUsed, day_arrived,condition);
            item1.set_equalized(equalized);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="MP3"){
            MP3 item1=new MP3(name,purchasePrice,newOrUsed, day_arrived,condition);
            item1.set_equalized(equalized);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="CassettePlayer"){
            CassettePlayer item1=new CassettePlayer(name,purchasePrice,newOrUsed, day_arrived,condition);
            item1.set_equalized(equalized);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Guitar"){
            Guitar item1=new Guitar(name,purchasePrice,newOrUsed, day_arrived,condition,Electric);
            item1.set_tuned(tuned);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Bass"){
            Bass item1=new Bass(name,purchasePrice,newOrUsed, day_arrived,condition,Electric);
            item1.set_tuned(tuned);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Mandolin"){
            Mandolin item1=new Mandolin(name,purchasePrice,newOrUsed, day_arrived,condition, Electric);
            item1.set_tuned(tuned);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Flute"){
            Flute item1=new Flute(name,purchasePrice,newOrUsed, day_arrived,condition, Flute_Type);
            item1.set_adjusted(adjusted);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Harmonica"){
            Harmonica item1=new Harmonica(name,purchasePrice,newOrUsed, day_arrived,condition, Harmonica_Key);
            item1.set_adjusted(adjusted);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Saxophone"){
            Saxophone item1=new Saxophone(name,purchasePrice,newOrUsed, day_arrived,condition, Harmonica_Key);
            item1.set_adjusted(adjusted);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Hats"){
            Hats item1=new Hats(name,purchasePrice,newOrUsed, day_arrived,condition, Hat_Size);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Shirts"){
            Shirts item1=new Shirts(name,purchasePrice,newOrUsed, day_arrived,condition, Shirt_Size);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Bandanas"){
            Bandanas item1=new Bandanas(name,purchasePrice,newOrUsed, day_arrived,condition);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="PracticeAmps"){
            PracticeAmps item1=new PracticeAmps(name,purchasePrice,newOrUsed, day_arrived,condition,wattage);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Cables"){
            Cables item1=new Cables(name,purchasePrice,newOrUsed, day_arrived,condition,length);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="Strings"){
            Strings item1=new Strings(name,purchasePrice,newOrUsed, day_arrived,condition,string_type);
            item1.set_Store(location);
            return item1;
        }
        else if (name1=="GigBag"){
            GigBag item1=new GigBag(name,purchasePrice,newOrUsed, day_arrived,condition);
            item1.set_Store(location);
            return item1;
        }
        return null;
    }
}
