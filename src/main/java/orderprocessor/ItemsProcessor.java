package orderprocessor;

import exceptions.ExceptionsHandler;
import itemfactory.Item;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import utils.BasePage;

import java.text.ParseException;
import java.util.Map;

public class ItemsProcessor {
    private BasePage bp = new BasePage();
    private ExceptionsHandler xcep = new ExceptionsHandler();
    private String eCpnNA = "N/A";

    void wghtReqdProcessor(Item i, int key, int keyNum, String itemNameFromDB, double itemUnitPriceFromDB, Map<Integer, Item> itemMap, Map<Integer, Object[]> orderMap){
        int maxTries = 0;
        while (true){
            try {
                double itemWeight = bp.getDoubleFromScanner("Enter weight: ");
                double totalWeightedItemPrice = bp.roundUpToTwoDecimals(itemWeight*itemUnitPriceFromDB);
                String dollarWeightedItemPrice = bp.addDollarFormat(totalWeightedItemPrice);
                System.out.println("\tTotal Price: " + dollarWeightedItemPrice);
                if (xcep.hasElecCpn(itemMap, key)){
                    String eCpnAmt = bp.addDollarFormat(eCpnAmt(itemMap, key));
                    double cpnAdjustedItemPrice = eCpnProcessor(itemMap, key, totalWeightedItemPrice);
                    String dollarCpnAdjustedPrice = bp.addDollarFormat(cpnAdjustedItemPrice);
                    orderMap.put(keyNum, new Object[] {i.getItemName(), i.getUnitPrice(), itemWeight + "lb", cpnAdjustedItemPrice, "(" + eCpnAmt + ")", dollarCpnAdjustedPrice});
                    break;
                }
                else {
                    //map.put(keyNum, new Object[] {itemNameFromDB, itemUnitPriceFromDB, itemWeight + "lb", totalWeightedItemPrice, eCpnNA, dollarWeightedItemPrice});
                    break;
                }

            } catch (Exception e) {
                System.out.println("\tInvalid weight. ");
                maxTries++;
                if (maxTries >= 5){
                    System.out.println("\tEntry error. Set item aside");
                    break;
                }
            }
        }
    }

    void quantityReqdProcessor(Item i, int key, int keyNum, String itemNameFromDB, double itemUnitPriceFromDB, Map<Integer, Item> itemMap, Map<Integer, Object[]> orderMap){
        int maxTries = 0;
        while (true){
            try {
                int itemQuantity = bp.getIntFromScanner("Enter quantity: ");
                double totalQuantityItemPrice = bp.roundUpToTwoDecimals(itemQuantity*itemUnitPriceFromDB);
                String dollarQuantityItemPrice = bp.addDollarFormat(totalQuantityItemPrice);
                System.out.println("\tTotal Price: " + dollarQuantityItemPrice);
                if (xcep.hasElecCpn(itemMap, key)){
                    String eCpnAmt = bp.addDollarFormat(eCpnAmt(itemMap, key));
                    double cpnAdjustedItemPrice = eCpnProcessor(itemMap, key, totalQuantityItemPrice);
                    String dollarCpnAdjustedPrice = bp.addDollarFormat(cpnAdjustedItemPrice);
                    orderMap.put(keyNum, new Object[] {i.getItemName(), i.getUnitPrice(), itemQuantity, cpnAdjustedItemPrice, "(" + eCpnAmt + ")", dollarCpnAdjustedPrice});
                    break;
                }
                else {
                    orderMap.put(keyNum, new Object[]{itemNameFromDB, itemUnitPriceFromDB, itemQuantity, totalQuantityItemPrice, eCpnNA, dollarQuantityItemPrice});
                    break;
                }
            } catch (Exception e) {
                System.out.println("\tInvalid quantity. ");
                maxTries++;
                if (maxTries >= 5){
                    System.out.println("\tEntry error. Set item aside");
                    break;
                }
            }
        }
    }

    boolean ageRestrProcessor(Item i, int key, int keyNum, String itemNameFromDB, double itemUnitPriceFromDB, String itemUnit, Map<Integer, Item> itemMap, Map<Integer, Object[]> orderMap){
        boolean invalidDob = true;
        int maxTries = 0;
        while (invalidDob){
            try {
                String ageEntered = bp.getStringFromScanner("\tEnter DOB in 'mm-dd-yyyy' format: ");
                double ageRstrItemPrice = bp.roundUpToTwoDecimals(itemUnitPriceFromDB);
                String dollarAgeRestPrice = bp.addDollarFormat(ageRstrItemPrice);
                if (xcep.ageValidator(ageEntered)){
                    System.out.println("\tAge Validation Successful");
                    System.out.println("\tTotal Price: " + dollarAgeRestPrice);
                    orderMap.put(keyNum, new Object[] {i.getItemName(), i.getUnitPrice(), itemUnit, ageRstrItemPrice, eCpnNA, dollarAgeRestPrice});
                    invalidDob = false;
                    return false;
                }
                else {
                    System.out.println("\tShopper is underage. Item not added");
                    invalidDob = false;
                }
            } catch (ParseException e) {
                maxTries++;
                System.out.println("\tDate not valid");
                if (maxTries >= 5){
                    System.out.println("\tEntry error. Set item aside");
                    break;
                }
            }
        }

        return true;
    }

    void normalItemProcessor(Item i, int key, int keyNum, String itemNameFromDB, double itemUnitPriceFromDB, String itemUnit, Map<Integer, Item> itemMap, Map<Integer, Object[]> orderMap){
        double normalItemPrice = bp.roundUpToTwoDecimals(itemUnitPriceFromDB);
        String dollarNormalItemPrice = bp.addDollarFormat(normalItemPrice);
        System.out.println("\tTotal Price: " + dollarNormalItemPrice);
        if (xcep.hasElecCpn(itemMap, key)){
            String eCpnAmt = bp.addDollarFormat(eCpnAmt(itemMap, key));
            double cpnAdjustedItemPrice = eCpnProcessor(itemMap, key, normalItemPrice);
            String dollarCpnAdjustedPrice = bp.addDollarFormat(cpnAdjustedItemPrice);
            orderMap.put(keyNum, new Object[] {itemNameFromDB, itemUnitPriceFromDB, itemUnit, cpnAdjustedItemPrice, "(" + eCpnAmt + ")", dollarCpnAdjustedPrice});
        }
        else {
            orderMap.put(keyNum, new Object[] {itemNameFromDB, itemUnitPriceFromDB, itemUnit, normalItemPrice, eCpnNA, dollarNormalItemPrice});
        }
    }

    private double eCpnAmt(Map<Integer, Item> itemMap, int key){
        Item thisItem = itemMap.get(key);
        return thisItem.geteCpnAmt();
    }

    private double eCpnProcessor(Map<Integer, Item> itemMap, int key, double totalItemPrice){
        double couponAmt = eCpnAmt(itemMap, key);
        String dollarCpnAmt = bp.addDollarFormat(couponAmt);
        double cpnAdjustedItemPrice = totalItemPrice - couponAmt;
        String dollarCpnAdjustedPrice = bp.addDollarFormat(cpnAdjustedItemPrice);
        System.out.println("\t(eCpn: " + dollarCpnAmt + ")");
        System.out.println("\tAdjusted Price: " + dollarCpnAdjustedPrice);
        return cpnAdjustedItemPrice;
    }

    boolean hasPaperCpns() {
        String hasCoupon = bp.getStringFromScanner("Do you have coupons? (1 = Yes; 2 = No)");
        if (hasCoupon.equalsIgnoreCase("1")){
            return true;
        }
        System.out.println("Thank you for shopping with us!");
        return false;
    }
}
