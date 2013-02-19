package net.kenkku.swagsweeper.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import net.kenkku.swagsweeper.game.MinesweeperGame;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class InfoBar extends JPanel {

    private JLabel timeLabel;
    private JLabel minesLabel;
    private Timer timer;
    private int minecount;
    private MinesweeperGame game;

    public InfoBar() {
        super(new BorderLayout());

        setBorder(new BevelBorder(BevelBorder.RAISED));

        initializeComponents();
    }

    public void setGame(MinesweeperGame gameToSet) {
        this.game = gameToSet;

        ActionListener timerListener = new ActionListener() {
    
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game != null) {
                    timeLabel.setText("Time: " + String.format("%.1f", game.getTime() / 1000.0));
                }
            }
        };
        
        if(timer != null) {
            timer.stop();
        }
        timer = new Timer(100, timerListener);
        timer.start();
        
        minecount = game.getField().getMineCount();
        setMinecount(minecount);
    }

    private void initializeComponents() {
        timeLabel = new JLabel("Time: 0.0");
        timeLabel.setBorder(new EmptyBorder(3, 3, 3, 3));
        add(timeLabel, BorderLayout.WEST);

        minesLabel = new JLabel("Mines left: 0");
        minesLabel.setBorder(new EmptyBorder(3, 3, 3, 3));
        add(minesLabel, BorderLayout.EAST);
    }

    public int getMinecount() {
        return minecount;
    }

    public void setMinecount(int minecount) {
        this.minecount = minecount;
        minesLabel.setText("Mines left: " + minecount);
    }
}
