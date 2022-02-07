import Items.Items;
import Staff.Staff;
import Staff.Clerk;

import java.util.ArrayList;

public class Store {
    private int Cash_Register;
    private ArrayList<Items> Inventory;
    private float Inventory_value;
    private ArrayList<Staff> staff_member;
    private int daysPassed;
    private ArrayList<Items> Items_sold;
    private float total_salePrice;
    private float money_added;

    public void ManageStaff() {

    }

    public void Report(){
    }

    public void AddStaff(Staff worker) {
        staff_member.add(worker);
    }
    public void RemoveStaff(Staff worker) {
        staff_member.remove((staff_member.indexOf(worker)));
    }

}
