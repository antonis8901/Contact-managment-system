package buttons;

import objects.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchNameActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        String name = JOptionPane.showInputDialog(
                null,
                "Enter First Name:",
                "Search objects.Contact",
                JOptionPane.QUESTION_MESSAGE
        );

        if (name == null || name.isEmpty()) {
            return;
        }

        String result = Contact.searchByName(name);

        JOptionPane.showMessageDialog(
                null,
                result,
                "Search Result",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
