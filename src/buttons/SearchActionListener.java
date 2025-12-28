package buttons;

import objects.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchActionListener implements ActionListener {
    @Override
        public void actionPerformed(ActionEvent e){
        String phone = JOptionPane.showInputDialog(
                null,
                "Enter phone number:",
                "Search objects.Contact",
                JOptionPane.QUESTION_MESSAGE
        );

        if (phone == null || phone.isEmpty()) {
            return;
        }

        String result = Contact.searchByPhoneNumber(phone);

        JOptionPane.showMessageDialog(
                null,
                result,
                "Search Result",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}