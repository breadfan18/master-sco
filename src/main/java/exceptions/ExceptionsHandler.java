package exceptions;

import database.CreateItemList;
import itemfactory.Item;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExceptionsHandler extends CreateItemList {

    public boolean isWeightReqd(Item item){
        //return sheet.getRow(rowNum).getCell(2).getBooleanCellValue();
       return item.isWeightReqd();
    }

    public boolean isQuantityReqd(XSSFSheet sheet, int rowNum){
        return sheet.getRow(rowNum).getCell(3).getBooleanCellValue();
    }

    public boolean isRecalled(XSSFSheet sheet, int rowNum){
        return sheet.getRow(rowNum).getCell(4).getBooleanCellValue();
    }

    public boolean isAgeRestricted(XSSFSheet sheet, int rowNum){
        return sheet.getRow(rowNum).getCell(5).getBooleanCellValue();
    }

    public boolean hasElecCpn(XSSFSheet sheet, int rowNum){
        return sheet.getRow(rowNum).getCell(6).getBooleanCellValue();
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







