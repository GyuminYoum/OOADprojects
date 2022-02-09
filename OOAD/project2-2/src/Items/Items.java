package Items;

//public class item, it's attributes and getters & setters
public class Items {
    private String name;
    private Double purchasePrice;
    private Double listPrice;
    private Boolean newOrUsed;
    private Integer dayArrived;
    private Integer condition;
    private Double salePrice;
    private Integer daySold;

    public Items(String name1, Double price1, Boolean nou, Integer day1, Integer con1){
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
    public Double get_purchasePrice(){
        return purchasePrice;
    }
    public void set_purchasePrice(Double price1){
        purchasePrice=price1;
    }
    public Double get_listPrice(){
        return listPrice;
    }
    public void set_listPrice(Double price1){
        listPrice=price1;
    }
    public Boolean get_newOrUsed(){
        return newOrUsed;
    }
    public void set_newOrUsed(Boolean bool1){
        newOrUsed=bool1;
    }
    public Integer get_dayArrived(){
        return dayArrived;
    }
    public void set_dayArrived(Integer day1){
        dayArrived=day1;
    }
    public Integer get_condition(){
        return condition;
    }
    public String get_conditionS() {
        String[] condition = {"poor", "fair", "good", "very good", "excellent"};
        return condition[get_condition()];
    }
    public void set_condition(Integer condition1){
        condition=condition1;
    }
    public Double get_salePrice(){
        return salePrice;
    }
    public void set_salePrice(Double price1){
        salePrice=price1;
    }
    public Integer get_daySold(){
        return daySold;
    }
    public void set_daySold(Integer day1){
        daySold=day1;
    }

}






