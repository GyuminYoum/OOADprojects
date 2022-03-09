package Factory;

import GuitarKitParts.*;
import Items.Items;
import java.util.*;
import java.util.ArrayList;

//NGuitarKitFactory
//prompt the user for choice and continuously loop until the user selection is within the available options
//once valid number is picked, you get the part at that index in the pre-made arraylist
//this is done for every part
public class NGuitarKitFactory implements GuitarKitFactory{

    Scanner reader=new Scanner(System.in);

    public Bridge pickBridge(){
        int response=-1;
        ArrayList<Bridge> BridgeOpt=new ArrayList<Bridge>();
        Bridge b1=new Bridge("Bridge",3.00,true,0,5);
        Bridge b2=new Bridge("Bridge",5.00,true,0,5);
        Bridge b3=new Bridge("Bridge",7.00,true,0,5);
        BridgeOpt.add(b1);
        BridgeOpt.add(b2);
        BridgeOpt.add(b3);
        while(response!=0 && response!=1 && response!=2){
            System.out.println("pick a Bridge (0-2):");
            response=reader.nextInt();
            if(response!=0 && response!=1 && response!=2){
                System.out.println("Invalid selection");
            }
        }
        return BridgeOpt.get(response);
    }
    public Covers pickCovers(){
        int response=-1;
        ArrayList<Covers>CoversOpt=new ArrayList<Covers>();
        Covers c1=new Covers("Covers",2.00,true,0,5);
        Covers c2=new Covers("Covers",4.00,true,0,5);
        Covers c3=new Covers("Covers",6.00,true,0,5);
        CoversOpt.add(c1);
        CoversOpt.add(c2);
        CoversOpt.add(c3);
        while(response!=0 && response!=1 && response!=2){
            System.out.println("pick a Cover (0-2):");
            response=reader.nextInt();
            if(response!=0 && response!=1 && response!=2){
                System.out.println("Invalid selection");
            }
        }
        return CoversOpt.get(response);
    }
    public knobSet pickKnobSet(){
        int response=-1;
        ArrayList<knobSet>knobSetOpt=new ArrayList<knobSet>();
        knobSet k1=new knobSet("knobSet",3.00,true,0,5);
        knobSet k2=new knobSet("knobSet",2.00,true,0,5);
        knobSet k3=new knobSet("knobSet",1.00,true,0,5);
        knobSetOpt.add(k1);
        knobSetOpt.add(k2);
        knobSetOpt.add(k3);
        while(response!=0 && response!=1 && response!=2){
            System.out.println("pick a Knob Set (0-2):");
            response=reader.nextInt();
            if(response!=0 && response!=1 && response!=2){
                System.out.println("Invalid selection");
            }
        }
        return knobSetOpt.get(response);
    }
    public neck pickNeck(){
        int response=-1;
        ArrayList<neck>nckOpt=new ArrayList<neck>();

        neck n1=new neck("neck",1.00,true,0,5);
        neck n2=new neck("neck",3.00,true,0,5);
        neck n3=new neck("neck",7.00,true,0,5);
        nckOpt.add(n1);
        nckOpt.add(n2);
        nckOpt.add(n3);
        while(response!=0 && response!=1 && response!=2){
            System.out.println("pick a neck (0-2):");
            response=reader.nextInt();
            if(response!=0 && response!=1 && response!=2){
                System.out.println("Invalid selection");
            }
        }
        return nckOpt.get(response);
    }
    public pickGuard pickpG(){
        int response=-1;
        ArrayList<pickGuard>pGOpt=new ArrayList<pickGuard>();
        pickGuard pg1=new pickGuard("pickGuard",1.00,true,0,5);
        pickGuard pg2=new pickGuard("pickGuard",7.00,true,0,5);
        pickGuard pg3=new pickGuard("pickGuard",10.00,true,0,5);
        pGOpt.add(pg1);
        pGOpt.add(pg2);
        pGOpt.add(pg3);
        while(response!=0 && response!=1 && response!=2){
            System.out.println("pick a pickGuard (0-2):");
            response=reader.nextInt();
            if(response!=0 && response!=1 && response!=2){
                System.out.println("Invalid selection");
            }
        }
        return pGOpt.get(response);
    }
    public pickUps pickpU(){
        int response=-1;
        ArrayList<pickUps>pUOpt=new ArrayList<pickUps>();
        pickUps pu1=new pickUps("pickUps",5.00,true,0,5);
        pickUps pu2=new pickUps("pickUps",15.00,true,0,5);
        pickUps pu3=new pickUps("pickUps",20.00,true,0,5);
        pUOpt.add(pu1);
        pUOpt.add(pu2);
        pUOpt.add(pu3);
        while(response!=0 && response!=1 && response!=2){
            System.out.println("pick a Pickup (0-2):");
            response=reader.nextInt();
            if(response!=0 && response!=1 && response!=2){
                System.out.println("Invalid selection");
            }
        }
        return pUOpt.get(response);
    }
    //gather each part by using the function above, then combines them
    //sets listprice to half, because otherwise it will just be 2x purchase price
    //return guitarkit object
    public Items createGuitarKit(){
        GuitarKit GK1=new GuitarKit("GuitarKit",0.00,true,0,5);
        Bridge br=pickBridge();
        Covers c=pickCovers();
        knobSet ks=pickKnobSet();
        neck n=pickNeck();
        pickGuard pg=pickpG();
        pickUps pu=pickpU();
        GK1.addParts(br);
        GK1.addParts(c);
        GK1.addParts(ks);
        GK1.addParts(n);
        GK1.addParts(pg);
        GK1.addParts(pu);
        GK1.set_listPrice(GK1.get_listPrice()/2);
        return GK1;
    }
}
