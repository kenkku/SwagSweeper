package net.kenkku.swagsweeper.ui;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import net.kenkku.swagsweeper.game.MinesweeperGame;
import net.kenkku.swagsweeper.game.Square;
import net.kenkku.swagsweeper.game.actions.Action;
import net.kenkku.swagsweeper.game.actions.RevealAction;
import net.kenkku.swagsweeper.util.Position;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class FieldPanel extends JPanel {

    private int width, height;
    private MinesweeperGame game;
    private Image background;
    private Image[] numbers = new Image[9];
    private Set<Position> flagged = new HashSet();

    public FieldPanel(int width, int height, MinesweeperGame game) {
        this.width = width;
        this.height = height;
        this.game = game;

        try {
            background = ImageIO.read(new File("images/CLP_gravel2_resized.jpg"));
            for (int i = 1; i <= 8; i++) {
                numbers[i] = ImageIO.read(new File("images/" + i + ".png"));
            }
        } catch (IOException ex) {
            Logger.getLogger(FieldPanel.class.getName()).log(Level.SEVERE, "Failed to load image", ex);
        }

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);

        setPreferredSize(new Dimension(width * 40, height * 40));
    }

    private void addMove(Position pos) {
        Action act = new RevealAction(game, pos);
        game.addMove(act);
        if(game.isGameOver()) {
            System.out.println("Game over");
            if(game.isVictorious()) {
                System.out.println("...and winnage");
            }
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_CLICKED && e.getClickCount() == 1) {
            Position pos = new Position(e.getX() / 40, e.getY() / 40);
            if (e.getButton() == MouseEvent.BUTTON1 
                    && !flagged.contains(pos)) {
                addMove(pos);
            } else if(e.getButton() == MouseEvent.BUTTON3) {
                if(flagged.contains(pos)) {
                    flagged.remove(pos);
                } else {
                    flagged.add(pos);
                }
            }
            repaint();
        }
    }

    private void drawBackground(Graphics2D g) {
        for (int x = 0; x < getWidth(); x += background.getWidth(null)) {
            for (int y = 0; y < getHeight(); y += background.getHeight(null)) {
                g.drawImage(background, x, y, null);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;

        drawBackground(g);
        drawSquares(g);
    }

    private void drawSquares(Graphics2D g) {
        g.setPaint(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        for (Square s : game.getField().getAllSquares()) {
            Position pos = s.getPosition();
            
            if (s.isRevealed()) {
                g.setPaint(new Color(0.7f, 0.7f, 0.7f, 0.8f));
            } else if(flagged.contains(pos)) {
                g.setPaint(new Color(1.0f, 0.3f, 0.3f, 0.5f));
            }
            g.fill3DRect(pos.getX() * 40, pos.getY() * 40, 40, 40, true);
            g.setPaint(new Color(1.0f, 1.0f, 1.0f, 0.0f));
            
            if (s.isRevealed()) {
                int numMines = game.getField().getNumMines(s);
                if (numMines > 0) {
                    g.drawImage(numbers[numMines], pos.getX() * 40, pos.getY() * 40, null);
                }
            }
        }
    }
}