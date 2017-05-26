/**
 * Created by root on 9/5/17.
 */
public class Flat extends Unit{

    //String flatNo;
    String ownerName;
    String tenantName;
    String date;
    String chequeNo;
    String amountReceived;

    public Flat() {

    }

    public Flat(String flatNo) {
        this.unitNo = flatNo;
    }

 /*   public String getFlatNo() {
        return unitNo;
    }

    public void setFlatNo(String flatNo) {
        if(!flatNo.isEmpty())
            this.unitNo = flatNo;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        if(!ownerName.isEmpty())
        this.ownerName = ownerName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        if(!tenantName.isEmpty())
        this.tenantName = tenantName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if(!date.isEmpty())
        this.date = date;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        if(!chequeNo.isEmpty())
        this.chequeNo = chequeNo;
    }

    public void setAmountReceived(String amountReceived) {
        if(!amountReceived.isEmpty())
        this.amountReceived = amountReceived;
    }

    public String getAmountReceived() {
        return amountReceived;
    }*/
}
