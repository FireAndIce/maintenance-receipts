package com;

import com.google.api.services.drive.Drive;


/**
 * Created by root on 16/5/17.
 */
public class Receipt {

    private static final String MAINTENANCE_FEE_DESCRIPTION = "Maintenance Fee x 3 months ";
    static String FOLDER_QUARTER1 = "0ByZTOYXLr6P9Y1d1eHZKaWIzejA";
    static String FOLDER_QUARTER2 = "Quarter2";
    static String FOLDER_QUARTER3 = "Quarter3";
    static String FOLDER_QUARTER4 = "Quarter4";

    Unit unit;

    String invoiceNo;
    String date;
    String billTo;
//    String unitNo;
    String description;
    String amount;
    String lateFee;

    public Receipt(Unit unit) {
        this.unit = unit;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

//    public String getUnitNo() {
//        return unitNo;
//    }
//
//    public void setUnitNo(String unitNo) {
//        this.unitNo = unitNo;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = MAINTENANCE_FEE_DESCRIPTION + description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLateFee() {
        return lateFee;
    }

    public void setLateFee(String lateFee) {
        this.lateFee = lateFee;
    }

    public void generateReceipt(Drive driveservice) {

        //Create copy of the receipt template and move it to Quarter folder
        DriveOps driveOps = new DriveOps();

        String fileId = driveOps.copyFile(driveservice, WriteSheet.spreadsheetId, unit.getInvoice() + "-" + unit.getUnitNo() + "-" + unit.getOwnerName());
        System.out.println(fileId);
        driveOps.moveFile(driveservice, fileId, FOLDER_QUARTER1);



        //Write values for the receipt
        WriteSheet writeSheet = new WriteSheet(fileId);
        writeSheet.updateInvoice(invoiceNo);
        writeSheet.updateName(billTo);
        if(unit instanceof Flat)
            writeSheet.updateFlatNo(unit.getUnitNo());
        else if(unit instanceof Shop)
            writeSheet.updateShopNo(unit.getUnitNo());

        writeSheet.updateDate(unit.getDate());
        writeSheet.updateDescription(description);
        writeSheet.updateAmount(unit.getAmountReceived());

        driveOps.exportFileToPDF(driveservice, fileId, unit.getInvoice() + "-" + unit.getUnitNo() + "-" + unit.getOwnerName() +".pdf"/*"t.pdf"*/);
    }
}
