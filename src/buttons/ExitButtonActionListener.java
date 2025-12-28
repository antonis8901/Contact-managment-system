package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButtonActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e){
        System.exit(0);
    }
}
