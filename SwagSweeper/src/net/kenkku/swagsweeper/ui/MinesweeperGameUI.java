package net.kenkku.swagsweeper.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
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
    private Container squareContainer;

    @Override
    public void run() {
        frame = new JFrame("SwagSweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 300));

        createComponents(frame.getContentPane());

        frame.setVisible(true);
    }

    private void createComponents(Container c) {
        newGame(10, 10);
    }

    private void createSquares(Container c, int width, int height) {
        c.setLayout(new GridLayout(width, height));

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Position pos = new Position(x, y);
                Square s = currentGame.getField().getSquare(pos);
                c.add(new UISquare(pos, s, currentGame));
            }
        }
    }

    private void newGame(int width, int height) {
        Container c = frame.getContentPane();
        if(squareContainer != null) {
            c.remove(squareContainer);
        }

        squareContainer = new JPanel();
        c.add(squareContainer);
        
        currentGame = new MinesweeperGame(width, height);
        
        createSquares(squareContainer, width, height);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new MinesweeperGameUI());
    }
}
