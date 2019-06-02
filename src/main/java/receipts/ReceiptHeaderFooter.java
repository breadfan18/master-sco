package receipts;

import orderprocessor.OrderProcessor;
import utils.BasePage;

import java.io.PrintStream;
import java.util.Calendar;

public class ReceiptHeaderFooter extends OrderProcessor {
    private BasePage bp = new BasePage();

    public void printReceiptHeader(PrintStream outputReceipt) {
        String transactionTime = String.valueOf(Calendar.getInstance().getTime());
        System.setOut(outputReceipt);

        System.out.println();
        System.out.println(DOUBLELINE);
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), STORE_NAME));
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), STORE_STREET_ADDRESS));
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), STORE_CITY_ZIP));
        System.out.println(bp.centerAlignString(DOUBLELINE.length(), transactionTime));
        System.out.println(DOUBLELINE);
    }

}