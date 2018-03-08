package database;

import utils.BasePage;

import java.sql.SQLException;

public class test {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CreateItemMap im = new CreateItemMap();
        BasePage bp = new BasePage();
        ItemProcessor ip = new ItemProcessor();
        im.createDbConnection();
        im.addItemsToMap();
       // String format = "%-15s%-12s%-12s%-12s%-12s%-12s%-12s%-12s";

        String scanedItem = bp.getStringFromScanner("Scan item: ");


        if (ip.itemProcessor(im.itemMap, scanedItem)){
            System.out.println("Item present");
        }
        else
        System.out.println("Item not there!");



    }
}
