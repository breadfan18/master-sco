package orderprocessor;

import database.CreateItemList;
import utils.BasePage;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CreateItemList im = new CreateItemList();
        BasePage bp = new BasePage();
        OrderProcessor op = new OrderProcessor();
        im.createDbConnection();
        im.addItemsToMap();
        op.orderProcessor(im.itemList);

        //String format = "%-15s%-12s%-12s%-12s%-12s%-12s%-12s%-12s";




        /*if (ip.itemValidator(im.itemList, scannedItem)){
            System.out.println("Item present");
        }
        else
        System.out.println("Item not there!");*/

        // To setup the boolean exception methods, i need to find a way to use the following
        // Boolean.getBoolean(value[1].toString());
    }

   /* public void printList(List<Item> itemList){
        for(Item i: itemList){
            System.out.format("%-15s%-12s", i.getItemName(), i.getUnitPrice());
            System.out.println();
        }
    }*/
}
