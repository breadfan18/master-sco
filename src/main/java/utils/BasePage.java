package utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class BasePage {


    public String twoDecimalsFromString(Double input) {
        DecimalFormat newFormat = new DecimalFormat("##.00");
        return newFormat.format(input);
    }

    public String getStringFromScanner(String userQuestion) {
        Scanner scan = new Scanner(System.in);
        System.out.print(userQuestion);
        return scan.next();
    }

    public  double getDoubleFromScanner(String userQuestion) {
        //This method will get a double from scanner and then round it up to two decimal places.
        Scanner scan = new Scanner(System.in);
        System.out.print(userQuestion);
        double userEntry = scan.nextDouble();
        String doubleToString = Double.toString(userEntry);
        BigDecimal biggie = new BigDecimal(doubleToString);
        biggie = biggie.setScale(2, BigDecimal.ROUND_HALF_UP);
        return  biggie.doubleValue();
    }

    public int getIntFromScanner(String userQuestion) {
        Scanner scan = new Scanner(System.in);
        System.out.print(userQuestion);
        return scan.nextInt();
    }

    public double getUserNumber(String userQuestion) {
        Scanner scan = new Scanner(System.in);
        String userResponse = "";
        double parsedResponse = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print(userQuestion);
                userResponse = scan.nextLine();
                parsedResponse = Double.parseDouble(userResponse);
                flag = false;
            } catch (NumberFormatException a) {
                System.out.println("'" + userResponse + "'" + " is not a valid number");
            }
        }
        return parsedResponse;
    }

    public  boolean findIntElementInArray(int[] intArray, int a) {
        for (int b : intArray) {
            if (a == b)
                return true;
        }
        return false;
    }

    public void headerDoubleUnderline(String a){
        String b = "=";
        for (int i = 0; i < a.length() ; i++) {
            System.out.print(b);
        }
        System.out.println();
    }

    public void headerSingleUnderline(String a){
        String b = "-";
        for (int i = 0; i < a.length() ; i++) {
            System.out.print(b);
        }
        System.out.println();
    }

    public int findNumberOfDuplicatesInList(int arrayElement, List<Integer> myList){
        int duplicates = 0;

        for (int i: myList)
        {
            if (i == arrayElement)
            {
                duplicates++;
            }
        }
        return duplicates;
    }

    public void generateRandomIntList(int rangeMax, int rangeMin, int numberOfElements, List<Integer> myList){
        /*for (int i = 0; i < numberOfElements; i++) {
            int randomGenerator = rand.nextInt(rangeMax)+rangeMin;
            myList.add(randomGenerator);
        }*/
    }

    public int returnSumOfListElements (List<Integer> myList){
        int sum = 0;

        for (int i: myList){
              sum = sum + i;
        }
        return sum;
    }

    public int maxValueInList (List<Integer> myList){
        int maxNow = myList.get(0);

        for (int i: myList){
            if (i > maxNow){
                maxNow = i;
            }
        }
        return maxNow;
    }

    public int minValueInList (List<Integer> myList){
        int minNow = myList.get(0);

        for (int i: myList){
            if (i < minNow){
                minNow = i;
            }
        }
        return minNow;
    }

    public String addDollarFormat(double input){
        DecimalFormat dollarformatter = new DecimalFormat("'$'0.00");
        return dollarformatter.format(input);
    }

    public double roundUpToTwoDecimals(double input){
        String doubleToString = Double.toString(input);
        BigDecimal biggie = new BigDecimal(doubleToString);
        biggie = biggie.setScale(2, BigDecimal.ROUND_HALF_UP);
        return  biggie.doubleValue();
    }

    public String centerAlignString (int width, String stringToAlign){
        return StringUtils.center(stringToAlign, width);
    }

    public String rightAlignString (int width, String stringToAlign){
        return StringUtils.right(stringToAlign, width);
    }
}