package orderprocessor;

import database.CreateItemList;
import itemfactory.Item;
import utils.BasePage;

import java.sql.SQLException;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Item i = new Item();
        CreateItemList im = new CreateItemList();
        BasePage bp = new BasePage();
        OrderProcessor op = new OrderProcessor();
        im.createDbConnection();
        im.addItemsToList();
        op.orderProcessor(im.testMap, i);

        //String format = "%-15s%-12s%-12s%-12s%-12s%-12s%-12s%-12s";




        /*if (ip.itemValidator(im.itemList, scannedItem)){
            System.out.println("Item present");
        }
        else
        System.out.println("Item not there!");*/

        // To setup the boolean exception methods, i need to find a way to use the following
        // Boolean.getBoolean(value[1].toString());
    }

    public static void printList(Map<Integer, Item> itemList){
        for(Integer key: itemList.keySet()){
            Item value = itemList.get(key);
            System.out.format("%-15s%-12s", value.getItemName(), value.getUnitPrice() );
            System.out.println();
        }
    }
}
