package Staff.Observer;
import Staff.Clerk;
import Store.Store;

import java.io.*;

import java.util.Observable;
import java.util.Observer;

public class Logger implements Observer {
    public static void main(String[] args) {

        int days = 30;


        if(Store.get_daysPassed() <= days)
        {
            Clerk log = Store.get_OnShift();
            if(log != null)
            {
                log.ArriveAtStore();
                log.CheckRegister();
                log.DoInventory();
                log.OpenTheStore();
                log.CleanTheStore();
                log.LeaveTheStore();
            }
        }
    }


    @Override
    public void update(Observable o, Object arg) {

        private





    }




}
