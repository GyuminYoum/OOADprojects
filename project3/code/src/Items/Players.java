package Items;

public class Players extends Items {
    private boolean equalized;
    public Players(String name1, double price1, boolean nou, int day1, int con1) {
        super(name1, price1, nou, day1, con1);
        equalized=false;
    }
    public void set_equalized(boolean bool1){
        equalized=bool1;
    }
    public boolean get_equalized(){
        return equalized;
    }
}

