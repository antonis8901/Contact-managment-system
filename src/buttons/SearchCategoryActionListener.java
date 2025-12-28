package buttons;

import objects.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchCategoryActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        String category = JOptionPane.showInputDialog(
                null,
                "Enter category name:",
                "Search objects.Contact",
                JOptionPane.QUESTION_MESSAGE
        );

        if (category == null || category.isEmpty()) {
            return;
        }

        String result = Contact.searchByCategory(category);

        JOptionPane.showMessageDialog(
                null,
                result,
                "Search Result",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}


