package buttons;

import objects.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ImportButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        try {

            List<Contact> contacts = Contact.importFromDatabase();
            Contact.importToFile(contacts);
            JOptionPane.showMessageDialog(
                    null,
                    "Import has been successful",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(
                    null,
                    "Error trying to import",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
