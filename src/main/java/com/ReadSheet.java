package com;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by root on 6/5/17.
 */
public class ReadSheet {
    String spreadsheetId = "1RFyFVT8nRBPMYSCcpHc6x4Q6GURHJUjeicB4SppOh7s"; // GJ Maintenance
    String FLAT_SHEET_RANGE = "Sheet1!A3:AB50";
    String SHOP_SHEET_RANGE = "Sheet2!A3:AB50";
    String QUARTER_1_RANGE = "";
    String QUARTER_2_RANGE = "";
    String QUARTER_3_RANGE = "";
    String QUARTER_4_RANGE = "";



    public enum DateColumn {
        DATE (1);

        private final int dateColumn;


        DateColumn(int dateColumn) {
            this.dateColumn = dateColumn;
        }

        public int getDateColumn() {
            return dateColumn;
        }

        public int getRange(MaintenanceReceipts.Quarter quarter) {
            return (4 + (DATE.getDateColumn() + (6 * quarter.ordinal()))) - 1;
        }
    }

    public enum InvoiceColumn {
        INVOICE_NO (2);

        private final int invoiceColumn;


        InvoiceColumn(int invoiceColumn) {
            this.invoiceColumn = invoiceColumn;
        }

        public int getInvoiceColumn() {
            return invoiceColumn;
        }

        public int getRange(MaintenanceReceipts.Quarter quarter) {
            return (4 + (INVOICE_NO.getInvoiceColumn() + (6 * quarter.ordinal()))) - 1;
        }
    }

    public enum ChequeColumn {
        CHEQUE_NO (3);

        private final int chequeColumn;


        ChequeColumn(int chequeColumn) {
            this.chequeColumn = chequeColumn;
        }

        public int getChequeColumn() {
            return chequeColumn;
        }

        public int getRange(MaintenanceReceipts.Quarter quarter) {
            return (4 + (CHEQUE_NO.getChequeColumn() + (6 * quarter.ordinal()))) - 1;
        }
    }

    public enum Month1Column {
        MONTH_1 (4);

        private final int month1Column;


        Month1Column(int month1Column) {
            this.month1Column = month1Column;
        }

        public int getMonth1Column() {
            return month1Column;
        }

        public int getRange(MaintenanceReceipts.Quarter quarter) {
            return (4 + (MONTH_1.getMonth1Column() + (6 * quarter.ordinal()))) - 1;
        }
    }

    public enum Month2Column {
        MONTH_2 (5);

        private final int month2Column;


        Month2Column(int month2Column) {
            this.month2Column = month2Column;
        }

        public int getMonth2Column() {
            return month2Column;
        }

        public int getRange(MaintenanceReceipts.Quarter quarter) {
            return (4 + (MONTH_2.getMonth2Column() + (6 * quarter.ordinal()))) - 1;
        }
    }

    public enum Month3Column {
        MONTH_3 (6);

        private final int month3Column;


        Month3Column(int month3Column) {
            this.month3Column = month3Column;
        }

        public int getMonth3Column() {
            return month3Column;
        }

        public int getRange(MaintenanceReceipts.Quarter quarter) {
            return (4 + (MONTH_3.getMonth3Column() + (6 * quarter.ordinal()))) - 1;
        }
    }

    /*public enum SheetColumns {
        DATE (4),
        INVOICE_NO (5),
        CHEQUE_NO (6),
        MONTH_1 (7),
        MONTH_2 (8),
        MONTH_3 (9)
        ;

        private final int sheetColumn;

        SheetColumns(int sheetColumn) {
            this.sheetColumn = sheetColumn;
        }

        public int getDateColumn(MaintenanceReceipts.Quarter quarter) {
            return ((DATE.ordinal() + 6) * quarter.ordinal());
        }

        public int getInvoiceColumn(MaintenanceReceipts.Quarter quarter) {
            return ((INVOICE_NO.ordinal() + 6) * quarter.ordinal());
        }

        public int getChequeNoColumn(MaintenanceReceipts.Quarter quarter) {
            return ((CHEQUE_NO.ordinal() + 6) * quarter.ordinal());
        }

        public int getMonth1Column(MaintenanceReceipts.Quarter quarter) {
            return ((MONTH_1.ordinal() + 6) * quarter.ordinal());
        }

        public int getMonth2Column(MaintenanceReceipts.Quarter quarter) {
            return ((MONTH_2.ordinal() + 6) * quarter.ordinal());
        }
        public int getMonth3Column(MaintenanceReceipts.Quarter quarter) {
            return ((MONTH_3.ordinal() + 6) * quarter.ordinal());
        }
    }*/

    private Unit mFlat;
    MaintenanceReceipts.Quarter quarter;

    public ReadSheet() {

    }

    public ReadSheet(MaintenanceReceipts.Quarter quarter) {
        this.quarter = quarter;

    }

    public Flat readByFlatNo(String flatNo) {
/*        Flat flat = null;
        try {
            Sheets service = MaintenanceReceipts.getSheetsService();
            ValueRange response = service.spreadsheets().values()
                    .get(spreadsheetId, FLAT_SHEET_RANGE)
                    .execute();
            List<List<Object>> values = response.getValues();


            for(List row: values) {
                String fNo = (String) row.get(0);
                if(fNo != null && fNo.equals(flatNo)) {
                    String owner = (String) row.get(1);
                    String date = (String) row.get(3);
                    String chequeNo = (String) row.get(4);
                    String amountReceived = (String) row.get(5);
                    flat = new Flat();
                    flat.setFlatNo(fNo);
                    flat.setOwnerName(owner);
                    flat.setDate(date);
                    flat.setChequeNo(chequeNo);
                    flat.setAmountReceived(amountReceived);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    mFlat = flat;
    return mFlat;*/
        Unit flatUnit = new Flat(flatNo);

        flatUnit = readByUnitNo(flatUnit, FLAT_SHEET_RANGE);
        return (Flat) flatUnit;
    }

    Shop readByShopNo(String shopNo) {
        Unit shopUnit = new Shop(shopNo);
        shopUnit = readByUnitNo(shopUnit, SHOP_SHEET_RANGE);
        return (Shop) shopUnit;
    }

    private Unit readByUnitNo(Unit unit/*String unitNo*/, String sheetRange) {
//        Flat flat = null;
        try {
            Sheets service = MaintenanceReceipts.getSheetsService();
            ValueRange response = service.spreadsheets().values()
                    .get(spreadsheetId, /*FLAT_SHEET_RANGE*/sheetRange)
                    .execute();
            List<List<Object>> values = response.getValues();


            for(List row: values) {
                try {
                    String fNo = (String) row.get(0);
                    if (fNo != null && fNo.equals(unit.getUnitNo()/*unitNo*/)) {
                        System.out.println(row);
                        String invoice = (String) row.get(/*5*/InvoiceColumn.INVOICE_NO.getRange(quarter));
                        String owner = (String) row.get(1);
                        String[] phoneNumbers = splitPhoneNumbers((String) row.get(2));
                        String date = (String) row.get(/*4*/DateColumn.DATE.getRange(quarter));
                        String chequeNo = (String) row.get(/*6*/ChequeColumn.CHEQUE_NO.getRange(quarter));
                        String month1 = (String) row.get(Month1Column.MONTH_1.getRange(quarter));
                        String month2 = (String) row.get(Month2Column.MONTH_2.getRange(quarter));
                        String month3 = (String) row.get(Month3Column.MONTH_3.getRange(quarter));

                        String amountReceived = totalAmountReceived(month1, month2, month3);
                        System.out.println("amountReceived: " + amountReceived);
//                    String amountReceived = (String) row.get(7);

                        unit.setInvoice(invoice);
                        unit.setOwnerName(owner);
                        unit.setPhoneNumbers(phoneNumbers);
                        unit.setDate(date);
                        unit.setChequeNo(chequeNo);
                        unit.setAmountReceived(amountReceived);

                    /*flat = new Flat();
                    flat.setFlatNo(fNo);
                    flat.setOwnerName(owner);
                    flat.setDate(date);
                    flat.setChequeNo(chequeNo);
                    flat.setAmountReceived(amountReceived);*/
                    }
                }catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        mUnit = flat;
        return unit;
    }

    public List<Unit> readPhoneNumbers(){
        String[] sheetRanges = new String[] {FLAT_SHEET_RANGE, SHOP_SHEET_RANGE};
        List<Unit> listOfUnits = new ArrayList<>();

        for(int i=0; i < sheetRanges.length; i++) {
            try {
                Sheets service = MaintenanceReceipts.getSheetsService();
                ValueRange response = service.spreadsheets().values()
                        .get(spreadsheetId, /*FLAT_SHEET_RANGE*/sheetRanges[i])
                        .execute();
                List<List<Object>> values = response.getValues();


                for (List row : values) {
                    try {
                        String fNo = (String) row.get(0);
                        if (fNo != null) {
                            System.out.println(row);

                            String owner = (String) row.get(1);
                            String[] phoneNumbers = new String[0];

                            phoneNumbers = splitPhoneNumbers((String) row.get(2));


                            Unit unit = new Unit();
                            unit.setOwnerName(owner);
                            unit.setPhoneNumbers(phoneNumbers);

                            listOfUnits.add(unit);
                        }
                    } catch(IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        mUnit = flat;
        return listOfUnits;
    }

    private String[] splitPhoneNumbers(String phoneNumbers) {
        return phoneNumbers.split("/");
    }

    private String totalAmountReceived(String month1, String month2, String month3) {
        double m1 = new Integer(0);
        double m2 = new Integer(0);
        double m3 = new Integer(0);
        if(!month1.isEmpty())
            try {
                m1 = NumberFormat.getNumberInstance(Locale.US).parse(month1).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            m1 = Double.parseDouble(month1);
        if(!month2.isEmpty())
            try {
                m2 = NumberFormat.getNumberInstance(Locale.US).parse(month2).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            m2 = Double.parseDouble(month2);
        if(!month3.isEmpty())
            try {
                m3 = NumberFormat.getNumberInstance(Locale.US).parse(month3).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            m3 = Double.parseDouble(month3);
        double total = m1 + m2 + m3;
        return new String(String.valueOf(total));
    }

}

