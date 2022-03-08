import Items.Items;
import Staff.Clerk;
import Staff.Electronic;
import Staff.Manual;
import Store.Store;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
//used these links as sources to learn about JUnit testing
//https://www.youtube.com/watch?v=o5pE7L2tVV8
//https://www.youtube.com/watch?v=tTbBXf9Uh0s&list=PLqq-6Pq4lTTa4ad5JISViSb2FVG8Vwa4o&index=27
//https://docs.oracle.com/javase/7/docs/api/java/io/PrintStream.html

class Tests {

    //instantiating variables used across multiple tests
    Items item;
    ArrayList<Clerk> members= new ArrayList<Clerk>();
    Clerk Justin=new Clerk("Justin", new Manual());
    Clerk Freddy=new Clerk("Freddy", new Electronic());
    //static private File file_;

    //TestReporter is used to publish text to the console
    TestInfo testInfo_;
    TestReporter testReporter_;
    //static FileWriter fileWriter;
    //static BufferedWriter bufferedWriter;

    /*@BeforeAll
    static void makeTestOutputFile(){
        File file = new File("project4//code//src//TestLogs.txt");
        file_ = file;
        try {
            Files.deleteIfExists(file.toPath());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    */
    //Before the start of all tests
    @BeforeAll
    static void init() throws FileNotFoundException {
        //PrintStream and setOut are used to redirect System.out output to the text file
        PrintStream fileOut = new PrintStream("project4//code//src//TestLogs.txt");
        System.setOut(fileOut);
    }

    //Before every test, reset item/testInfo/testReporter
    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        testInfo_ = testInfo;
        testReporter_ = testReporter;
        item = new Items("testItem1", 12.34, true, 3, 4);
        /*try {
            fileWriter = new FileWriter(file_, true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (Exception e) {
            System.err.println(e);
        }*/
    }

    @Test
    void get_name(){
        assertEquals("testItem1", item.get_name());
        //if any of these tests fail, publishEntry will not go through
        //applicable to all test cases
        testReporter_.publishEntry("Items.get_name() is working correctly. ");
        System.out.println("Items.get_name() is working correctly. ");
    }

    @Test
    void get_purchasePrice() {
        assertEquals(12.34, item.get_purchasePrice(), 0.001);
        testReporter_.publishEntry("Items.get_purchasePrice() is working correctly. ");
        System.out.println("Items.get_purchasePrice() is working correctly. ");
    }

    @Test
    void get_listPrice() {
        assertEquals(12.34 * 2, item.get_listPrice(), 0.001);
        testReporter_.publishEntry("Items.get_listPrice() is working correctly. ");
        System.out.println("Items.get_listPrice() is working correctly. ");
    }

    @Test
    void get_newOrUsed() {
        assertTrue(item.get_newOrUsed());
        testReporter_.publishEntry("Items.get_newOrUsed() is working correctly. ");
        System.out.println("Items.get_newOrUsed() is working correctly. ");
    }

    @Test
    void get_dayArrived() {
        assertEquals(3, item.get_dayArrived());
        testReporter_.publishEntry("Items.get_dayArrived() is working correctly. ");
        System.out.println("Items.get_dayArrived() is working correctly. ");
    }

    @Test
    void get_condition() {
        assertEquals(4, item.get_condition());
        testReporter_.publishEntry("Items.get_condition() is working correctly. ");
        System.out.println("Items.get_condition() is working correctly. ");
    }

    @Test
    void set_name() {
        item.set_name("woohoo");
        assertEquals("woohoo", item.get_name());
        testReporter_.publishEntry("Items.set_name() is working correctly. ");
        System.out.println("Items.set_name() is working correctly. ");
    }

    @Test
    void set_purchasePrice() {
        item.set_purchasePrice(54.32);
        assertEquals(54.32, item.get_purchasePrice(), 0.001);
        testReporter_.publishEntry("Items.set_purchasePrice() is working correctly. ");
        System.out.println("Items.set_purchasePrice() is working correctly. ");
    }

    @Test
    void set_listPrice() {
        item.set_listPrice(567.34);
        assertEquals(567.34, item.get_listPrice(), 0.001);
        testReporter_.publishEntry("Items.set_listPrice() is working correctly. ");
        System.out.println("Items.set_listPrice() is working correctly. ");
    }

    @Test
    void set_newOrUsed() {
        item.set_newOrUsed(false);
        assertFalse(item.get_newOrUsed());
        testReporter_.publishEntry("Items.set_newOrUsed() is working correctly. ");
        System.out.println("Items.set_newOrUsed() is working correctly. ");
    }

    @Test
    void set_dayArrived() {
        item.set_dayArrived(20);
        assertEquals(20, item.get_dayArrived());
        testReporter_.publishEntry("Items.set_dayArrived() is working correctly. ");
        System.out.println("Items.set_dayArrived() is working correctly. ");
    }

    @Test
    void get_conditionS() {
        assertEquals("excellent", item.get_conditionS());
        testReporter_.publishEntry("Items.get_conditionS() is working correctly. ");
        System.out.println("Items.get_conditionS() is working correctly. ");
    }

    @Test
    void set_condition() {
        item.set_condition(3);
        assertEquals(3, item.get_condition());
        testReporter_.publishEntry("Items.set_condition() is working correctly. ");
        System.out.println("Items.set_condition() is working correctly. ");
    }

    @Test
    void CheckRegister() {
        members.add(Justin);
        Store store1= new Store(members,"North",1);
        store1.Build();
        assertEquals(0, store1.get_Register());
        testReporter_.publishEntry("Clerk.CheckRegister() is working correctly. ");
        System.out.println("Clerk.CheckRegister() is working correctly. ");
    }

    @Test
    void get_workingAt() {
        members.add(Justin);
        Store store1= new Store(members,"North",1);
        Justin.set_workingAt(store1);
        assertEquals(store1,Justin.get_workingAt());
        testReporter_.publishEntry("Store.get_workingAt() is working correctly. ");
        System.out.println("Store.get_workingAt() is working correctly. ");
    }

    @Test
    void goToBank() {
        members.add(Freddy);
        Store store1= new Store(members,"North",1);
        Freddy.set_workingAt(store1);
        Freddy.GoToBank();
        assertEquals(1000, store1.get_Register());
        testReporter_.publishEntry("Clerk.goToBank() is working correctly. ");
        System.out.println("Clerk.goToBank() is working correctly. ");
    }

    @Test
    void addToItemList() {
        members.add(Freddy);
        Store store1= new Store(members,"North",1);
        store1.addToItemList("pog");
        assertEquals("pog", store1.get_ItemList().get(0));
        testReporter_.publishEntry("Store.addToItemList is working correctly. ");
        System.out.println("Store.addToItemList is working correctly. ");
    }

    @Test
    void Build() {
        members.add(Justin);
        members.add(Freddy);
        Store store1= new Store(members,"North",1);
        store1.Build();
        assertNotEquals(0, store1.get_InventoryValue());
        assertEquals(1, store1.get_daysPassed());
        testReporter_.publishEntry("Store.Build() is working correctly. ");
        System.out.println("Store.Build() is working correctly. ");
    }
}