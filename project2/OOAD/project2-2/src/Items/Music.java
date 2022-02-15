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
    public void printAtts(){
        System.out.println("name: "+get_name());
        System.out.println("purchasePrice: "+get_purchasePrice());
        System.out.println("listPrice: "+get_listPrice());
        System.out.println("newOrUsed: "+get_newOrUsed());
        System.out.println("dayArrived: "+get_dayArrived());
        System.out.println("condition: "+get_condition());
        System.out.println("salePrice: "+get_salePrice());
        System.out.println("daySold: "+get_daySold());
        System.out.println("band: "+band);
        System.out.println("album: "+album);
    }
}

