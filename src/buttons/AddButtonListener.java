package buttons;

import objects.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        String firstName = JOptionPane.showInputDialog("First Name:");
        if (firstName == null) return;

        String lastName = JOptionPane.showInputDialog("Last Name:");
        if (lastName == null) return;

        String phone = JOptionPane.showInputDialog("Phone Number:");
        if (phone == null) return;

        String email = JOptionPane.showInputDialog("Email:");
        if (email == null) return;

        String address = JOptionPane.showInputDialog("Address:");
        if (address == null) return;

        String category = JOptionPane.showInputDialog("objects.Category:");
        if (category == null) return;

        // Create objects.Contact object
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhoneNumber(phone);
        contact.setEmail(email);
        contact.setAddress(address);
        contact.setCategory(category);

        // Call DB method
        Contact.addContact(contact);

        JOptionPane.showMessageDialog(
                null,
                "objects.Contact added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
