package orderprocessor;

import database.CreateItemList;
import receipts.ExportReceipt;
import receipts.ReceiptHeaderFooter;

import java.io.PrintStream;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CreateItemList im = new CreateItemList();
        OrderProcessor op = new OrderProcessor();
        ReceiptHeaderFooter rd = new ReceiptHeaderFooter();
        PrintStream er = new ExportReceipt().receiptCreator();

        im.createDbConnection();
        im.addItemsToItemMap();
        op.orderProcessor(im.itemMap);
        rd.printReceiptHeader(er);
        op.receiptProcessor(er);

    }

    /*public static void printList(Map<Integer, Item> itemList){
        for(Integer key: itemList.keySet()){
            Item value = itemList.get(key);
            System.out.format("%-15s%-12s", value.getItemName(), value.getUnitPrice() );
            System.out.println();
        }
    }*/
}
