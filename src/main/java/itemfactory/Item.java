package itemfactory;

public class Item {
    private String itemName;
    private double unitPrice;
    private boolean weightReqd;
    private boolean quanReqd;
    private boolean recalled;
    private boolean ageRest;
    private boolean eCpn;
    private double eCpnAmt;

    public Item(){}

    public Item(String itemName, double unitPrice, boolean weightReqd, boolean quanReqd, boolean recalled,
                boolean ageRest, boolean eCpn, double eCpnAmt){
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.weightReqd = weightReqd;
        this.quanReqd = quanReqd;
        this.recalled = recalled;
        this.ageRest = ageRest;
        this.eCpn = eCpn;
        this.eCpnAmt = eCpnAmt;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isWeightReqd() {
        return weightReqd;
    }

    public void setWeightReqd(boolean weightReqd) {
        this.weightReqd = weightReqd;
    }

    public boolean isQuanReqd() {
        return quanReqd;
    }

    public void setQuanReqd(boolean quanReqd) {
        this.quanReqd = quanReqd;
    }

    public boolean isRecalled() {
        return recalled;
    }

    public void setRecalled(boolean recalled) {
        this.recalled = recalled;
    }

    public boolean isAgeRest() {
        return ageRest;
    }

    public void setAgeRest(boolean ageRest) {
        this.ageRest = ageRest;
    }

    public boolean iseCpn() {
        return eCpn;
    }

    public void seteCpn(boolean eCpn) {
        this.eCpn = eCpn;
    }

    public double geteCpnAmt() {
        return eCpnAmt;
    }

    public void seteCpnAmt(double eCpnAmt) {
        this.eCpnAmt = eCpnAmt;
    }

}
