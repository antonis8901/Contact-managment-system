package buttons;

import objects.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){

        String firstName = JOptionPane.showInputDialog("First Name:");
        if (firstName == null) return;

        Contact contact = new Contact();
        contact.setFirstName(firstName);

        Contact.deleteContact(contact);

        JOptionPane.showMessageDialog(
                null,
                "objects.Contact deleted successfully!",
                "Delete",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
