package Items;

public class Music extends Items {
    private String band;
    private String album;

    public Music(String name1, double price1, boolean nou, int day1, int con1, String name2, String name3) {
        super(name1, price1, nou, day1, con1);
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

