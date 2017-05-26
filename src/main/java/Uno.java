import com.sun.org.apache.xml.internal.resolver.helpers.BootstrapResolver;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.frame.XDesktop;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.*;
import com.sun.star.uno.Exception;

/**
 * Created by root on 25/4/17.
 */
public class Uno {
public static void main(String[] args) throws Exception {
    XDesktop xDesktop = getDesktop();
}


    private static XDesktop getDesktop() {
        XDesktop xDesktop = null;
        try {
            XMultiComponentFactory xMCF = null;

            XComponentContext xContext = null;

            xContext = Bootstrap.bootstrap(new String[] {"/opt/libreoffice5.3/program/"});

            xMCF = xContext.getServiceManager();
            if (xMCF != null) {
                System.out.println("Connected to a running office ...");

                Object oDesktop = xMCF.createInstanceWithContext("com.sun.star.frame.Desktop", xContext);
                xDesktop = UnoRuntime.queryInterface(com.sun.star.frame.XDesktop.class, oDesktop);
            } else
                System.out.println("Can't create a desktop. No connection, no remote office servicemanager available!");
        } catch (java.lang.Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }

        return xDesktop;
    }
}
