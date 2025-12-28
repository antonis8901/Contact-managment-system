package buttons;

import objects.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        try {
            Contact.exportToDatabase(Contact.exportFromFile());
            JOptionPane.showMessageDialog(
                    null,
                    "Export has been successful",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error trying to export",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
