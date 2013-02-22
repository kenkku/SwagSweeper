package net.kenkku.swagsweeper.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import net.kenkku.swagsweeper.game.MinesweeperGame;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class MinesweeperGameUI implements Runnable, ActionListener {

    private MinesweeperGame currentGame;
    private JFrame frame;
    private FieldPanel fieldPanel;
    private JMenuBar menuBar;

    @Override
    public void run() {
        frame = new JFrame("SwagSweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.setVisible(true);
    }

    private void createComponents(Container c) {
        c.setLayout(new BorderLayout());

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);

        JMenuItem newGameButton = new JMenuItem("New game");
        gameMenu.add(newGameButton);
        newGameButton.addActionListener(this);



        newGame(c, 20, 20);

        frame.setResizable(false);
        frame.pack();
    }

    private void newGame(Container c, int width, int height) {
        currentGame = new MinesweeperGame(width, height, width * height / 8);

        if (fieldPanel != null) {
            c.remove(fieldPanel);
        }
        fieldPanel = new FieldPanel(width, height, currentGame);
        c.add(fieldPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new MinesweeperGameUI());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New game")) {
            OptionsDialog optionsDialog = new OptionsDialog(frame);
            optionsDialog.setVisible(true);
            
            newGame(frame.getContentPane(), optionsDialog.getWidthValue(),
                    optionsDialog.getHeightValue());
            frame.pack();
        }
    }
}
