package net.kenkku.swagsweeper.ui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import net.kenkku.swagsweeper.game.MinesweeperGame;
import net.kenkku.swagsweeper.game.Square;
import net.kenkku.swagsweeper.game.actions.RevealAction;
import net.kenkku.swagsweeper.util.Position;

/**
 * Ruutua GUIssa vastaava komponentti
 * 
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class UISquare extends JButton implements Observer, MouseListener {

    private Position position;
    private Square square;
    private MinesweeperGame game;
    private boolean flagged = false;

    public UISquare(Position position, Square square, MinesweeperGame game) {
        super();
        this.position = position;
        this.square = square;
        this.game = game;

        setMargin(new Insets(0, 0, 0, 0));
        
        addMouseListener(this);

        square.addObserver(this);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (square.isRevealed()) {
            if (square.isMine()) {
                setText("*");
            } else {
                setText("" + game.getField().getNumMines(square));
            }
            setEnabled(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!square.isRevealed() && isEnabled()) {
            if (e.getButton() == MouseEvent.BUTTON1 && !flagged) {
                game.addMove(new RevealAction(game, position));
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if(flagged) {
                    setText("");
                    flagged = false;
                } else {
                    setText("M!");
                    flagged = true;
                }
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}