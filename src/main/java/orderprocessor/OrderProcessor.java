package orderprocessor;

import exceptions.ExceptionsHandler;
import itemfactory.Item;
import utils.BasePage;

import java.util.List;

public class OrderProcessor extends OrderData {

    BasePage bp = new BasePage();
    ExceptionsHandler xcep = new ExceptionsHandler();

    public void orderProcessor(List<Item> itemList){
        System.out.println(DOUBLELINE);
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), RECEIPT_HEADER_ROW1));
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), RECEIPT_HEADER_ROW2));
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), RECEIPT_HEADER_ROW3));
        System.out.println(DOUBLELINE);

        String itemScanned;
        String itemUnit = "N/A";

        do {
            itemScanned = bp.getStringFromScanner("Enter item: ");
            if (itemValidator(itemList, itemScanned)){
                Item item = new Item();
                System.out.println(item.getUnitPrice());
                System.out.println(item.geteCpnAmt());
            }
            else if (!itemScanned.equalsIgnoreCase("1")){
                System.out.println(NOF_TEXT);
            }
        }
        while (!itemScanned.equalsIgnoreCase("1"));
    }

    public boolean itemValidator(List<Item> itemList, String scannedItem){
        for (Item i: itemList) {
            if (i.getItemName().equalsIgnoreCase(scannedItem)){
                return true;
            }
        }
        return false;
    }
}
