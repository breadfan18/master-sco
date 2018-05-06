package exceptions;

import itemfactory.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class ExceptionsHandler  {

    // these methods handles all the exceptions for exception items as shopper scans them
    public boolean isWeightReqd(Map<Integer, Item> itemMap, int key){
        Item thisItem = itemMap.get(key);
        return thisItem.isWeightReqd();
    }

    public boolean isQuantityReqd(Map<Integer, Item> itemMap, int key){
        Item thisItem = itemMap.get(key);
        return thisItem.isQuanReqd();
    }

    public boolean isRecalled(Map<Integer, Item> itemMap, int key){
        Item thisItem = itemMap.get(key);
        return thisItem.isRecalled();
    }

    public boolean isAgeRestricted(Map<Integer, Item> itemMap, int key){
        Item thisItem = itemMap.get(key);
        return thisItem.isAgeRest();
    }

    public boolean hasElecCpn(Map<Integer, Item> itemMap, int key){
        Item thisItem = itemMap.get(key);
        return thisItem.haseCpn();
    }

    public boolean ageValidator(String dob) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -21);
        Date validDate = cal.getTime();

            Date parsedUserDob = df.parse(dob);
            long userAgeInMilliSec = currentDate.getTime() - parsedUserDob.getTime();
            long validAgeInMilliSec = currentDate.getTime() - validDate.getTime();
            if (userAgeInMilliSec >= validAgeInMilliSec){
                return true;
        }
        return false;
    }

    public boolean age18Validator(String dob) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18);
        Date validDate = cal.getTime();

        Date parsedUserDob = df.parse(dob);
        long userAgeInMilliSec = currentDate.getTime() - parsedUserDob.getTime();
        long validAgeInMilliSec = currentDate.getTime() - validDate.getTime();
        if (userAgeInMilliSec >= validAgeInMilliSec){
            return true;
        }
        return false;
    }


}

    /*double couponProcessor (XSSFSheet sheet, String itemScanned){
        if (hasElecCpn(sheet, pr.findRowNum(sheet, itemScanned))){

        }
    }*/







