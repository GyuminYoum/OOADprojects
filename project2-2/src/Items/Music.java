package Items;

import Items.Items;

public class Music extends Items {
    private String band;
    private String album;

    public Music(String name1, Float price1, Float price2, Boolean nou, Integer day1, Integer con1, Float price3, Integer day2,String name2, String name3) {
        super(name1, price1, price2, nou, day1, con1, price3, day2);
        band=name2;
        album=name3;
    }

    public String get_band(){
        return band;
    }
    public void set_band(String name1){
        band=name1;
    }
    public String get_album(){
        return album;
    }
    public void set_album(String name2){
        album=name2;
    }
}

