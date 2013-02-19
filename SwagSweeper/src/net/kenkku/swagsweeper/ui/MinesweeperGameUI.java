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
public class MinesweeperGameUI implements Runnable, Observer {

    private MinesweeperGame currentGame;
    private JFrame frame;
    private Container squareContainer;
    private InfoBar infobar;
    private EndscreenGlassPane endscreen;

    @Override
    public void run() {
        frame = new JFrame("SwagSweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 300));

        createComponents(frame.getContentPane());

        frame.setVisible(true);
    }

    private void createComponents(Container c) {
        c.setLayout(new BorderLayout());

        infobar = new InfoBar();
        c.add(infobar, BorderLayout.NORTH);

        newGame(10, 10);

        frame.pack();
    }

    /**
     * Luo halutun kokoisen ruudukon UISquare-komponentteja
     */
    private void createSquares(Container c, int width, int height) {
        c.setLayout(new GridLayout(width, height));

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Position pos = new Position(x, y);
                Square s = currentGame.getField().getSquare(pos);
                c.add(new UISquare(pos, s, currentGame, infobar));
            }
        }
    }

    /**
     * Luo uuden pelin (MinesweeperGame) ja siihen liittyvät
     * käyttöliittymäkomponentit (UISquare)
     */
    public void newGame(int width, int height) {
        Container c = frame.getContentPane();
        
        if (squareContainer != null) {
            c.remove(squareContainer);
        }

        squareContainer = new JPanel();
        c.add(squareContainer, BorderLayout.CENTER);

        currentGame = new MinesweeperGame(width, height, width * height / 8);
        currentGame.addObserver(this);

        createSquares(squareContainer, width, height);

        infobar.setGame(currentGame);
    }

    public MinesweeperGame getCurrentGame() {
        return currentGame;
    }
    @Override
    public void update(Observable o, Object arg) {
        if (currentGame.isGameOver()) {
            endscreen = new EndscreenGlassPane(this);
            
            frame.setGlassPane(endscreen);
            endscreen.setVictorious(currentGame.isVictorious());
            
            endscreen.setVisible(true);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new MinesweeperGameUI());
    }
}
