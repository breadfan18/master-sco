package receipts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ExportReceipt {
    public PrintStream receiptCreator() {
        PrintStream finalReceipt = null;
        try {
            finalReceipt = new PrintStream(new FileOutputStream("Receipt.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return finalReceipt;
    }

}
