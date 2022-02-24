package Staff.Observer;

import Staff.Clerk;
import Staff.Clerk;

import java.util.List;

    interface myObserver
    {
        public void update();
        public void publish();
    }

interface Observer {
    public void update(int day,int []daysEarning,List <Record> completedOrders,List <Record> activeOrder);
}



        public void display() {


            System.out.println("Active Order Placed by Customer ");
            System.out.println();
            System.out.println("Total Active Orders: " + String.valueOf(.size()));
            System.out.println();
            for (int i = 0; i < i.size(); i++) {
                // System.out.println("order number :"+String.valueOf(i));
                System.out.println("Active Order " + (i + 1) + ": ");
                System.out.println("------------------------------------");

                System.out.println();
            }
        }
    }