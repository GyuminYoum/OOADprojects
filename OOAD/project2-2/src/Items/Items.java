package Items;

//public class item, it's attributes and getters & setters
//example of high cohesion, since the class Item only contains methods related related to Items
//and is only responsible its objects
//example of low coupling to Clerk class, since modifying attributes wouldn't affect clerk class majorly

public class Items {
    private String name;
    private double purchasePrice;
    private double listPrice;
    private boolean newOrUsed;
    private int dayArrived;
    private int condition;
    private double salePrice=0;
    private int daySold=0;

    //example of identity, Items object require its unique attributes which makes it distinguishable
    //from others.
    public Items(String name1, double price1, boolean nou, int day1, int con1){
        name=name1;
        purchasePrice=price1;
        listPrice=2*price1;
        newOrUsed=nou;
        dayArrived=day1;
        condition=con1;
    }


    //getters and setters
    //Example of Inheritance, because all its subclasses get the methods.
    public String get_name(){
        return name;
    }
    public void set_name(String name1){
        name=name1;
    }
    public double get_purchasePrice(){
        return Math.floor(purchasePrice * 100) / 100;
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

    //example of polymorphism where same named function performs differently
    // present in Items class and Music class
    public void printAtts(){
        System.out.println("name: "+name);
        System.out.println("purchasePrice: "+get_purchasePrice());
        System.out.println("listPrice: "+get_listPrice());
        System.out.println("newOrUsed: "+get_newOrUsed());
        System.out.println("dayArrived: "+get_dayArrived());
        System.out.println("condition: "+get_condition());
        System.out.println("salePrice: "+get_salePrice());
        System.out.println("daySold: "+get_daySold());
    }

}






