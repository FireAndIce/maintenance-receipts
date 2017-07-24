import com.MaintenanceReceipts;
import junit.framework.TestCase;

public class AuthorizeTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAuthorize() throws Exception {
        MaintenanceReceipts.authorize();
    }
}
