package buttons;

import objects.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener {
    @Override
    public  void actionPerformed(ActionEvent e){
        String data = Contact.showAllPersonsRecords();

        JOptionPane.showMessageDialog(
                null,
                data.isEmpty() ? "No contacts found" : data,
                "Contacts",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
