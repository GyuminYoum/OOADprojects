package Items;

//public class item, it's attributes and getters & setters
public class Items {
    private String name;
    private double purchasePrice;
    private double listPrice;
    private boolean newOrUsed;
    private int dayArrived;
    private int condition;
    private double salePrice;
    private int daySold;

    public Items(String name1, double price1, boolean nou, int day1, int con1){
        name=name1;
        purchasePrice=price1;
        listPrice=2*price1;
        newOrUsed=nou;
        dayArrived=day1;
        condition=con1;
    }


    public String get_name(){
        return name;
    }
    public void set_name(String name1){
        name=name1;
    }
    public double get_purchasePrice(){
        return purchasePrice;
    }
    public void set_purchasePrice(double price1){
        purchasePrice=price1;
    }
    public double get_listPrice(){
        return listPrice;
    }
    public void set_listPrice(double price1){
        listPrice=price1;
    }
    public boolean get_newOrUsed(){
        return newOrUsed;
    }
    public void set_newOrUsed(boolean bool1){
        newOrUsed=bool1;
    }
    public int get_dayArrived(){
        return dayArrived;
    }
    public void set_dayArrived(int day1){
        dayArrived=day1;
    }
    public int get_condition(){
        return condition;
    }
    public String get_conditionS() {
        String[] condition = {"poor", "fair", "good", "very good", "excellent"};
        return condition[get_condition()];
    }
    public void set_condition(Integer condition1){
        condition=condition1;
    }
    public double get_salePrice(){
        return salePrice;
    }
    public void set_salePrice(double price1){
        salePrice=price1;
    }
    public int get_daySold(){
        return daySold;
    }
    public void set_daySold(int day1){
        daySold=day1;
    }

}






