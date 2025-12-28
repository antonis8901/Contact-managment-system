import buttons.*;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame();
            frame.setBounds(0, 0, 1024, 600);
            frame.setLocationRelativeTo(null);
            frame.setResizable(true);
            frame.setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(null);

            JButton buttonShowAll = new JButton("Show Database");
            buttonShowAll.setBounds(450, 100, 150, 70);
            buttonShowAll.addActionListener(new ButtonActionListener());

            JButton buttonAdd = new JButton("Add Contact");
            buttonAdd.setBounds(300, 200, 150, 70);
            buttonAdd.addActionListener(new AddButtonListener());

            JButton buttonModify = new JButton("Modify Contact");
            buttonModify.setBounds(450, 200, 150, 70);
            buttonModify.addActionListener(new ModifyButtonActionListener());

            JButton buttonDelete = new JButton("Delete Contact");
            buttonDelete.setBounds(600, 200, 150, 70);
            buttonDelete.addActionListener(new DeleteButtonActionListener());

            JButton buttonSearchName = new JButton("Contact search by name");
            buttonSearchName.setBounds(230, 300, 200, 70);
            buttonSearchName.addActionListener(new SearchNameActionListener());

            JButton buttonSearchPhoneNumber = new JButton("Contact search by phone number");
            buttonSearchPhoneNumber.setBounds(430, 300, 200, 70);
            buttonSearchPhoneNumber.addActionListener(new SearchActionListener());

            JButton buttonSearchCategory = new JButton("Contact search by category");
            buttonSearchCategory.setBounds(630, 300, 200, 70);
            buttonSearchCategory.addActionListener(new SearchCategoryActionListener());

            JButton buttonImport = new JButton("Import Contact");
            buttonImport.setBounds(370, 400, 150, 70);
            buttonImport.addActionListener(new ImportButtonActionListener());

            JButton buttonExport = new JButton("Export Contact");
            buttonExport.setBounds(520, 400, 150, 70);
            buttonExport.addActionListener(new ExportButtonActionListener());

            JButton buttonExit = new JButton("Exit");
            buttonExit.setBounds(0, 500, 100, 50);
            buttonExit.addActionListener(new ExitButtonActionListener());

            panel.add(buttonShowAll);
            panel.add(buttonAdd);
            panel.add(buttonModify);
            panel.add(buttonDelete);
            panel.add(buttonSearchName);
            panel.add(buttonSearchPhoneNumber);
            panel.add(buttonSearchCategory);
            panel.add(buttonImport);
            panel.add(buttonExport);
            panel.add(buttonExit);
            frame.setContentPane(panel);
            frame.setVisible(true);

        } catch (Exception e) {
            System.err.println("Invalid input");
        }
    }
}

