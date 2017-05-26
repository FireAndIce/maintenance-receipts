import com.google.api.services.drive.Drive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by root on 18/5/17.
 */
public class GangaJamunaUI {
    private JPanel panel;
    private JComboBox comboBoxUnitType;
    private JTextField textFieldUnitNo;
    private JButton generateReceiptButton;
    private JComboBox comboBoxPeriod;

    GangaJamunaUI() {

        generateReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Unit unit = null;
                MaintenanceReceipts.Quarter quarter = null;
                int comboBoxIndex = comboBoxUnitType.getSelectedIndex();

                String unitNo = textFieldUnitNo.getText();
                String quarterDescription = null;

                // To read selected quarter
                switch(comboBoxPeriod.getSelectedIndex()) {
                    case 0: quarter = MaintenanceReceipts.Quarter.QUARTER_1;
                            quarterDescription = "(Apr, May, Jun)";
                            break;
                    case 1: quarter = MaintenanceReceipts.Quarter.QUARTER_2;
                            quarterDescription = "(Jul, Aug, Sep)";
                            break;
                    case 2: quarter = MaintenanceReceipts.Quarter.QUARTER_3;
                            quarterDescription = "(Oct, Nov, Dec)";
                            break;
                    case 3: quarter = MaintenanceReceipts.Quarter.QUARTER_4;
                            quarterDescription = "(Jan, Feb, Mar)";
                            break;
                }
                //Check whether unit is Flat or Shop and read sheet accordingly
                if(comboBoxIndex == MaintenanceReceipts.unitType.FLAT.ordinal()) {
                    ReadSheet readSheet = new ReadSheet(quarter);
                    unit = readSheet.readByFlatNo(unitNo);
                } else if(comboBoxIndex == MaintenanceReceipts.unitType.SHOP.ordinal()) {
                    ReadSheet readSheet = new ReadSheet(quarter);
                    unit = readSheet.readByShopNo(unitNo);
                }

                Drive driveservice = null;
                try {
                    driveservice = MaintenanceReceipts.getDriveService();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Receipt receipt = new Receipt(unit);
                receipt.setInvoiceNo(unit.getInvoice());
                receipt.setDate(unit.getDate());
                receipt.setBillTo(unit.getOwnerName());

        //        receipt.setUnitNo(unit.getUnitNo());
                  receipt.setDescription(quarterDescription);
        //        receipt.setLateFee();
                receipt.setAmount(unit.getAmountReceived());

                receipt.generateReceipt(driveservice);

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GangaJamunaUI");
        frame.setContentPane(new GangaJamunaUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}