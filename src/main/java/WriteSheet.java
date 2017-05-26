import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 9/5/17.
 */
public class WriteSheet {

    public static String spreadsheetId = "1OQiHqP6x7AcVgB_PD3O9nR0edKaXgB4KuVlaVXVZBT8"; // GJ Maintenance
    private static String spreadsheetName = "Copy of Invoice!";
    private final String RANGE_OWNER_NAME = "A6";
    private final String RANGE_FLAT_NO = "A7";
    private final String RANGE_SHOP_NO = "A7";
    private  final String RANGE_INVOICE_NO = "F3";
    private final String RANGE_DATE = "F2";
    private final String RANGE_DESCRIPTION = "A11";
    private final String RANGE_AMOUNT = "F11";

    String fileId;
    WriteSheet(String fileId) {
        this.fileId = fileId;
    }


    void appendValue(String value, String range) {
        ValueRange requestBody = new ValueRange();
        List<List<Object>> values = new ArrayList<> ();
        List<Object> row = new ArrayList<> ();
        row.add(value);
        values.add(row);

        requestBody.setValues(values);
        String valueInputOption = "USER_ENTERED";


            try {
                Sheets service = MaintenanceReceipts.getSheetsWriteService();
                Sheets.Spreadsheets.Values.Update request = service.spreadsheets().values().update(/*spreadsheetId*/fileId, spreadsheetName + range, requestBody);
                request.setValueInputOption(valueInputOption);

                UpdateValuesResponse response = request.execute();
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public void updateName(String name) {
        appendValue(name, RANGE_OWNER_NAME);
    }
    
    public void updateInvoice(String invoice) {
        appendValue(invoice, RANGE_INVOICE_NO);
    }

    public void updateFlatNo(String flatNo) {
        appendValue("Flat - " + flatNo, RANGE_FLAT_NO);
    }

    public void updateShopNo(String shopNo) {
//        updateFlatNo(flat);
        appendValue("Shop - " + shopNo, RANGE_SHOP_NO);
    }

    public void updateDate(String date) {
        appendValue(date, RANGE_DATE);
    }

    public void updateAmount(String amount) {
        appendValue(amount, RANGE_AMOUNT);
    }

    public static Sheets createSheetsService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        // TODO: Change placeholder below to generate authentication credentials. See
        // https://developers.google.com/sheets/quickstart/java#step_3_set_up_the_sample
        //
        // Authorize using one of the following scopes:
        //   "https://www.googleapis.com/auth/drive"
        //   "https://www.googleapis.com/auth/spreadsheets"
        GoogleCredential credential = null;

        return new Sheets.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Google-SheetsSample/0.1")
                .build();
    }


    public void updateDescription(String description) {
        appendValue(description, RANGE_DESCRIPTION);
    }
}
