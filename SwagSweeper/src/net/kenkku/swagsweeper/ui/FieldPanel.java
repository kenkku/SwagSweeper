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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.kenkku.swagsweeper.game.MinesweeperGame;
import net.kenkku.swagsweeper.game.Square;
import net.kenkku.swagsweeper.game.actions.Action;
import net.kenkku.swagsweeper.game.actions.RevealAction;
import net.kenkku.swagsweeper.util.Position;
import sun.tools.jar.Main;

/**
 * Komponentti, johon miinakenttä piirretään
 * 
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class FieldPanel extends JPanel {

    private MinesweeperGame game;
    private Image background;
    private Image[] numbers = new Image[9];
    private Image bomb;
    private Set<Position> flagged = new HashSet();

    public FieldPanel(int width, int height, MinesweeperGame game) {
        this.game = game;
        loadImages();

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);

        setPreferredSize(new Dimension(width * 40, height * 40));
    }

    private void addMove(Position pos) {
        Action act = new RevealAction(game, pos);
        game.addMove(act);
        if (game.isGameOver()) {
            if (game.isVictorious()) {
                JOptionPane.showMessageDialog(this, "You are win!");
            } else {
                JOptionPane.showMessageDialog(this, "You are fail :(");
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
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if (flagged.contains(pos)) {
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
            } else if (flagged.contains(pos)) {
                g.setPaint(new Color(1.0f, 0.3f, 0.3f, 0.5f));
            }
            g.fill3DRect(pos.getX() * 40, pos.getY() * 40, 40, 40, true);
            g.setPaint(new Color(1.0f, 1.0f, 1.0f, 0.0f));

            if (s.isRevealed()) {
                if (s.isMine()) {
                    g.drawImage(bomb, pos.getX() * 40, pos.getY() * 40, null);
                    continue;
                }

                int numMines = game.getField().getNumMines(s);
                if (numMines > 0) {
                    g.drawImage(numbers[numMines], pos.getX() * 40, pos.getY() * 40, null);
                }
            }
        }
    }

    private void loadImages() {
        try {
            File backgroundFile = new File("images/CLP_gravel2_resized.jpg");
            if (backgroundFile.exists()) {
                background = ImageIO.read(backgroundFile);
            } else {
                background = ImageIO.read(MinesweeperGameUI.class.getResource("CLP_gravel2_resized.jpg"));
            }

            for (int i = 1; i <= 8; i++) {
                File imageFile = new File("images/" + i + ".jpg");
                if (imageFile.exists()) {
                    numbers[i] = ImageIO.read(imageFile);
                } else {
                    numbers[i] = ImageIO.read(MinesweeperGameUI.class.getResource(i + ".png"));
                }
            }
            File imageFile = new File("images/bomb.png");
            if (imageFile.exists()) {
                bomb = ImageIO.read(imageFile);
            } else {
                bomb = ImageIO.read(MinesweeperGameUI.class.getResource("bomb.png"));
            }


        } catch (IOException ex) {
            Logger.getLogger(FieldPanel.class.getName()).log(Level.SEVERE, "Failed to load image", ex);
        }
    }
}
