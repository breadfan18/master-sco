package orderprocessor;

import exceptions.ExceptionsHandler;
import itemfactory.Item;
import utils.BasePage;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProcessor extends OrderData {
    private Map<Integer, Object[]> orderMap = new HashMap<Integer, Object[]>();
    private BasePage bp = new BasePage();
    private ExceptionsHandler xcep = new ExceptionsHandler();
    private ItemsProcessor ip = new ItemsProcessor();
    private boolean ageNotValidated = true;

    public void orderProcessor(Map<Integer, Item> itemMap){
        System.out.println(DOUBLELINE);
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), RECEIPT_HEADER_ROW1));
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), RECEIPT_HEADER_ROW2));
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), RECEIPT_HEADER_ROW3));
        System.out.println(DOUBLELINE);

        int orderMapKeyNum = 0;
        String itemScanned;
        String itemUnit = "N/A";

        do {
            itemScanned = bp.getStringFromScanner("Enter item: ");
            if (itemValidator(itemMap, itemScanned)){

                int itemKey = getItemKey(itemMap, itemScanned);
                Item i = itemMap.get(itemKey);

                if (xcep.isWeightReqd(itemMap, itemKey)){
                    ip.wghtReqdProcessor(i, itemKey, orderMapKeyNum++, i.getItemName(), i.getUnitPrice(), itemMap, orderMap);
                }
                else if (xcep.isQuantityReqd(itemMap, itemKey)){
                    ip.quantityReqdProcessor(i, itemKey, orderMapKeyNum++, i.getItemName(), i.getUnitPrice(), itemMap, orderMap);
                }
                else if (xcep.isRecalled(itemMap, itemKey)){
                    System.out.println(RECALL_TEXT);
                }
                else if (xcep.isAgeRestricted(itemMap, itemKey) && ageNotValidated){
                    ageNotValidated = ip.ageRestrProcessor(i, itemKey, orderMapKeyNum++, i.getItemName(), i.getUnitPrice(), itemUnit, itemMap, orderMap);
                }
                else {
                    ip.normalItemProcessor(i, itemKey, orderMapKeyNum++, i.getItemName(), i.getUnitPrice(), itemUnit, itemMap, orderMap);
                }
            }
            else if (!itemScanned.equalsIgnoreCase("1")){
                System.out.println(NOF_TEXT);
            }
        }
        while (!itemScanned.equalsIgnoreCase("1"));
    }

    public boolean itemValidator(Map<Integer, Item> itemMap, String scannedItem){
        for (Integer key: itemMap.keySet()) {
            Item i = itemMap.get(key);
            if (i.getItemName().equalsIgnoreCase(scannedItem)){
                return true;
            }
        }
        return false;
    }

    public int getItemKey(Map<Integer, Item> itemMap, String scannedItem){
        for (Integer key: itemMap.keySet()) {
            Item i = itemMap.get(key);
            if (i.getItemName().equalsIgnoreCase(scannedItem)){
                return key;
            }
        }
        return 9999;
    }

    void receiptProcessor(PrintStream outputFile){
        System.setOut(outputFile);
        String format = "%-15s%-12s%-10s%-10s%-10s";

        //Print the item headers and then iterate through the itemMap to print name and total for each item
        System.out.format(format , ITEM_NAME_HEADER, PRICE_PER_UNIT_HEADER, UNIT_HEADER, COUPON_AMT_HEADER, TOTAL_PRICE_HEADER);
        System.out.println();

        for (Integer key: orderMap.keySet()){
            Object[] value = orderMap.get(key);
            System.out.format(format , value[0], value[1], value[2], value[4], value[5]);
            System.out.println();
        }

        System.out.println(DOUBLELINE);
       /* System.out.print(bp.rightAlignString(DOUBLELINE.length(), "Subtotal: " + bp.addDollarFormat(subTotalCalculator())));
        System.out.println(bp.rightAlignString(DOUBLELINE.length(), "Tax:      " + bp.addDollarFormat(taxCalculator())));
        System.out.println(SINGLELINE);
        System.out.println(bp.rightAlignString(DOUBLELINE.length(), "Balance:  " + bp.addDollarFormat(balanceDue())));*/
        System.out.println("                                     Subtotal: " + bp.addDollarFormat(subTotalCalculator()));
        System.out.println("                                     Tax:      " + bp.addDollarFormat(taxCalculator()));
        System.out.println(SINGLELINE);
        System.out.println("                                     Balance:  " + bp.addDollarFormat(balanceDue()));
        System.out.println(DOUBLELINE);
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), RECEIPT_FOOTER_ROW1));
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), RECEIPT_FOOTER_ROW2));
        System.out.println(DOUBLELINE);

    }

    private double subTotalCalculator(){
        double subTotal = 0;
        double eachItemPrice;
        for (Integer key: orderMap.keySet()){
            Object[] value = orderMap.get(key);
            eachItemPrice = Double.parseDouble(value[3].toString());
            subTotal += eachItemPrice;
        }
        return subTotal;
    }

    private double taxCalculator(){
        return subTotalCalculator()*TAX_RATE;
    }

    private double balanceDue(){
        return subTotalCalculator()+ taxCalculator();
    }


}