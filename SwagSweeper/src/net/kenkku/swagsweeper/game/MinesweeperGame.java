package net.kenkku.swagsweeper.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import net.kenkku.swagsweeper.game.actions.Action;

/**
 * Kuvaa yhtä miinaharavapeliä
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class MinesweeperGame extends Observable {

    private Field field;
    private List<Action> history = new ArrayList();
    /**
     * Peli on ohi (uusia siirtoja ei saisi tehdä)
     */
    private boolean gameOver = false;
    /**
     * Peli on käynnissä
     */
    private boolean running = false;
    private long startTime;
    private long endTime;
    private int mines;

    public MinesweeperGame(int width, int height) {
        this(width, height, 0);
    }

    public MinesweeperGame(int width, int height, int mines) {
        this.mines = mines;
        field = FieldGenerator.randomField(width, height, mines);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void gameOver() {
        gameOver = true;
        running = false;
        endTime = System.nanoTime();

        setChanged();
        notifyObservers();
    }

    public boolean isRunning() {
        return running;
    }

    public void addMove(Action action) {
        if (!gameOver) {
            if (!running) {
                startTime = System.nanoTime();
                running = true;
            }
            history.add(action);
            action.execute();
            isVictorious();
        }
    }

    /**
     * Palauttaa listan siirroista. Lähinnä testaamista varten.
     */
    List<Action> getActions() {
        return history;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    /**
     * Palauttaa pelin keston millisekunteina
     */
    public long getTime() {
        if (running) {
            return (System.nanoTime() - startTime) / 1000000;
        } else {
            return (endTime - startTime) / 1000000;
        }
    }

    public boolean isVictorious() {
        for (Square s : field.getAllSquares()) {
            if ((!s.isMine() && !s.isRevealed()) || (s.isMine() && s.isRevealed())) {
                return false;
            }
        }
        if (!gameOver) {
            gameOver();
        }
        return true;
    }
}