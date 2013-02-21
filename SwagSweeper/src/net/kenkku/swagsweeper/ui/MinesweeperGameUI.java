package net.kenkku.swagsweeper.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import net.kenkku.swagsweeper.game.MinesweeperGame;
import net.kenkku.swagsweeper.game.Square;
import net.kenkku.swagsweeper.util.Position;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class MinesweeperGameUI implements Runnable {

    private MinesweeperGame currentGame;
    private JFrame frame;
    private FieldPanel fieldPanel;

    @Override
    public void run() {
        frame = new JFrame("SwagSweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.setVisible(true);
    }

    private void createComponents(Container c) {
        c.setLayout(new BorderLayout());

        newGame(c, 20, 20);

        frame.setResizable(false);
        frame.pack();
    }

    private void newGame(Container c, int width, int height) {
        currentGame = new MinesweeperGame(width, height, width*height/10);

        fieldPanel = new FieldPanel(width, height, currentGame);
        c.add(fieldPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new MinesweeperGameUI());
    }
}
