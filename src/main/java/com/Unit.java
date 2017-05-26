package com;

/**
 * Created by root on 16/5/17.
 */
public class Unit {

    String unitNo;
    String ownerName;
    String[] phoneNumbers;
    String tenantName;
    String date;
    String chequeNo;
    String amountReceived;

    String invoice;

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        if(!ownerName.isEmpty())
            this.ownerName = ownerName;
    }

    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
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
    }



}
