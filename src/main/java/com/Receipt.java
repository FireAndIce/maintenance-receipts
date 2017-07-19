package com;

import com.google.api.services.drive.Drive;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by root on 16/5/17.
 */
public class Receipt {

    private static final String MAINTENANCE_FEE_DESCRIPTION = "Maintenance Fee x 3 months ";
    static String FOLDER_QUARTER1 = "0ByZTOYXLr6P9Y1d1eHZKaWIzejA";
    static String FOLDER_QUARTER2 = "0ByZTOYXLr6P9MHVCWXM0WndaZk0";
    static String FOLDER_QUARTER3 = "0ByZTOYXLr6P9Q1RVT0hQS3VWZlk";
    static String FOLDER_QUARTER4 = "0ByZTOYXLr6P9UnVtSmZnb2NJa1E";

    Map<MaintenanceReceipts.Quarter, String> quarterHashMap = new HashMap<MaintenanceReceipts.Quarter, String>() ;

    Unit unit;

    String invoiceNo;
    String date;
    String billTo;
//    String unitNo;
    String description;
    String amount;
    String lateFee;
    private MaintenanceReceipts.Quarter quarter;

    public Receipt(Unit unit) {
        this.unit = unit;
        quarterHashMap.put(MaintenanceReceipts.Quarter.QUARTER_1, FOLDER_QUARTER1);
        quarterHashMap.put(MaintenanceReceipts.Quarter.QUARTER_2, FOLDER_QUARTER2);
        quarterHashMap.put(MaintenanceReceipts.Quarter.QUARTER_3, FOLDER_QUARTER3);
        quarterHashMap.put(MaintenanceReceipts.Quarter.QUARTER_4, FOLDER_QUARTER4);
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
        String moveFileToFolderId;
        moveFileToFolderId = quarterHashMap.get(quarter);
        driveOps.moveFile(driveservice, fileId, moveFileToFolderId/*FOLDER_QUARTER2*/);



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

    public void printReceipt() {
        Desktop desktop = Desktop.getDesktop();
//        desktop.
    }

    public void setQuarter(MaintenanceReceipts.Quarter quarter) {
        this.quarter = quarter;
    }
}
