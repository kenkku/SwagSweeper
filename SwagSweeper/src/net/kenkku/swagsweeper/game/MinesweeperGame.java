package net.kenkku.swagsweeper.game;

import java.util.ArrayList;
import java.util.List;
import net.kenkku.swagsweeper.game.actions.Action;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class MinesweeperGame {

    private Field field;
    private List<Action> history = new ArrayList();
    private boolean gameOver = false;
    private boolean running;
    private long startTime;
    private long endTime;

    public MinesweeperGame(int width, int height) {
        field = FieldGenerator.randomField(width, height);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void gameOver() {
        gameOver = true;
        running = false;
        endTime = System.nanoTime();
    }
    
    public boolean isRunning() {
        return running;
    }

    public boolean addMove(Action action) {
        if (!gameOver) {
            if (!running) {
                startTime = System.nanoTime();
                running = true;
            }
            history.add(action);
            action.execute();
            return true;
        }
        return false;
    }

    public void setField(Field field) {
        this.field = field;
    }
    
    public Field getField() {
        return field;
    }

    public long getTime() {
        if (running) {
            return System.nanoTime() - startTime;
        } else {
            return endTime - startTime;
        }
    }
}
