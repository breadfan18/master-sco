package orderprocessor;

public class OrderData {
    private static OrderData od = new OrderData();
    protected static final String DOUBLELINE = "=====================================================";

    public String singleLine(){
        String singleLine = "-";
        String concSingleLine = "";
        for (int i = 0; i < DOUBLELINE.length() ; i++) {
            concSingleLine += singleLine;
        }
        return concSingleLine;
    }
    static final String SINGLELINE = od.singleLine() ;

    static final String ITEM_NAME_HEADER = "Item Name";
    static final String PRICE_PER_UNIT_HEADER = "Price/Unit";
    static final String UNIT_HEADER = "Unit";
    static final String TOTAL_PRICE_HEADER = "Total";
    static final String COUPON_AMT_HEADER = "Coupon";

    static final String RECEIPT_HEADER_ROW1 = "Welcome Valued Customer";
    static final String RECEIPT_HEADER_ROW2 = "Scan Items";
    static final String RECEIPT_HEADER_ROW3 = "Enter 1 when done";

    static final String RECEIPT_FOOTER_ROW1 = "Thank you for";
    static final String RECEIPT_FOOTER_ROW2 = "shopping with us";

    protected static final String STORE_NAME = "Kroger";
    protected static final String STORE_STREET_ADDRESS = "3760 Paxton Ave";
    protected static final String STORE_CITY_ZIP = "Cincinnati, OH 45209";


    static final String NOF_TEXT = "\tItem not on file";
    static final String RECALL_TEXT = "\tItem has been Recalled";

    static final double TAX_RATE = 0.07;


}
