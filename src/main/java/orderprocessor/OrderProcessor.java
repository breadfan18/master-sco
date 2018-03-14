package orderprocessor;

import exceptions.ExceptionsHandler;
import itemfactory.Item;
import utils.BasePage;

import java.util.List;
import java.util.Map;

public class OrderProcessor extends OrderData {

    BasePage bp = new BasePage();
    ExceptionsHandler xcep = new ExceptionsHandler();

    public void orderProcessor(Map<Integer, Item> itemMap, Item i){
        System.out.println(DOUBLELINE);
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), RECEIPT_HEADER_ROW1));
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), RECEIPT_HEADER_ROW2));
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), RECEIPT_HEADER_ROW3));
        System.out.println(DOUBLELINE);

        String itemScanned;
        String itemUnit = "N/A";

        do {
            itemScanned = bp.getStringFromScanner("Enter item: ");
            if (itemValidator(itemMap, itemScanned) != 99999){
                int itemKey = itemValidator(itemMap, itemScanned);

                if (xcep.isWeightReqd(itemMap, itemKey)){
                    System.out.println("Enter weight: ");
                }

                //STUCK HERE...HOW DO I MAKE THE EXCEPTIONS WORK? HOW DO I USE THE GETTERS IN THE EXCEPTION TO
                //RETURN BOOLEAN VALUES?

                /*Item item = new Item();
                System.out.println(item.getUnitPrice());
                System.out.println(item.geteCpnAmt());*/
            }
            else if (!itemScanned.equalsIgnoreCase("1")){
                System.out.println(NOF_TEXT);
            }
        }
        while (!itemScanned.equalsIgnoreCase("1"));
    }

   /* public boolean itemValidator(Map<Integer, Item> itemMap, String scannedItem){
        for (Integer key: itemMap.keySet()) {
            Item i = itemMap.get(key);
            if (i.getItemName().equalsIgnoreCase(scannedItem)){
                return true;
            }
        }
        return false;
    }*/

    public int itemValidator(Map<Integer, Item> itemMap, String scannedItem){
        for (Integer key: itemMap.keySet()) {
            Item i = itemMap.get(key);
            if (i.getItemName().equalsIgnoreCase(scannedItem)){
                return key;
            }
        }
        return 99999;
    }
}
