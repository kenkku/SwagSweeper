package net.kenkku.swagsweeper.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class EndscreenGlassPane extends JPanel {

    private JLabel message;
    private JButton newGameButton;
    private MinesweeperGameUI gameGui;

    public EndscreenGlassPane(MinesweeperGameUI gui) {
        gameGui = gui;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        message = new JLabel();
        add(message);

        newGameButton = new JButton("New game");
        add(newGameButton);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGui.newGame(10, 10);
                setVisible(false);
            }
        });
    }

    public void setVictorious(boolean victorious) {
        if (victorious) {
            message.setText("AWWW YISSS. Your time was: "
                    + String.format("%.2f", gameGui.getCurrentGame().getTime() / 1000.0)
                    + " seconds");
        } else {
            message.setText("YOU FAIL");
        }
    }
}
