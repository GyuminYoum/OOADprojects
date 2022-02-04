package Items;

import java.util.*;

//public class item, it's attributes and getters & setters
public class Items {
    private String name;
    private Float purchasePrice;
    private Float listPrice;
    private Boolean newOrUsed;
    private Integer dayArrived;
    private Integer condition;
    private Float salePrice;
    private Integer daySold;

    public Items(String name1, Float price1, Float price2, Boolean nou, Integer day1, Integer con1, Float price3, Integer day2){
        name=name1;
        purchasePrice=price1;
        listPrice=price2;
        newOrUsed=nou;
        dayArrived=day1;
        condition=con1;
        salePrice=price3;
        daySold=day2;
    }


    public String get_name(){
        return name;
    }
    public void set_name(String name1){
        name=name1;
    }
    public Float get_purchasePrice(){
        return purchasePrice;
    }
    public void set_purchasePrice(Float price1){
        purchasePrice=price1;
    }
    public Float get_listPrice(){
        return listPrice;
    }
    public void set_listPrice(Float price1){
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
    public void set_condition(Integer condition1){
        condition=condition1;
    }
    public Float get_salePrice(){
        return salePrice;
    }
    public void set_salePrice(Float price1){
        salePrice=price1;
    }
    public Integer get_daySold(){
        return daySold;
    }
    public void set_daySold(Integer day1){
        daySold=day1;
    }
}






