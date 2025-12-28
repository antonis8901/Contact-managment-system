package buttons;

import objects.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        String firstName = JOptionPane.showInputDialog("Search Name:");
        if (firstName == null) return;

        String newFirstName = JOptionPane.showInputDialog("Enter new name:");
        if (newFirstName == null) return;

        Contact contact = new Contact();
        contact.setFirstName(newFirstName);

        Contact.modifyContact(firstName, newFirstName);

        JOptionPane.showMessageDialog(
                null,
                "objects.Contact modified successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
