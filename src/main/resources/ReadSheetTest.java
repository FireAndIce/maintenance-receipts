import com.Flat;
import com.MaintenanceReceipts;
import com.ReadSheet;
import junit.framework.TestCase;

/**
 * Created by root on 26/5/17.
 */
public class ReadSheetTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();


    }

    public void testReadByFlatNo() {
        ReadSheet readSheet = new ReadSheet(MaintenanceReceipts.Quarter.QUARTER_1);
        Flat flat = readSheet.readByFlatNo("206");
        String[] phoneNumbers = flat.getPhoneNumbers();
        System.out.println(phoneNumbers.length);
    }

    public void tearDown() throws Exception {

    }
}