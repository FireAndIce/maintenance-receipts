package com;

/**
 * Created by root on 16/5/17.
 */
public class Shop extends Unit {

    public Shop(String shopNo) {
        this.unitNo = shopNo;
    }

    public String getShopNo() {
        return unitNo;
    }

    public void setShopNo(String shopNo) {
        this.unitNo = shopNo;
    }
}
