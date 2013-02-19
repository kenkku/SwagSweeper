package net.kenkku.swagsweeper.ui;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class EndscreenGlassPane extends JPanel {

    private JLabel message;
    
    public EndscreenGlassPane() {
        setLayout(new BorderLayout());
        message = new JLabel("ASDF");
        add(message, BorderLayout.CENTER);
    }

    public void setVictorious(boolean victorious) {
        if(victorious) {
            message.setText("AWWW YISSS");
        } else {
            message.setText("YOU FAIL");
        }
    }

}
