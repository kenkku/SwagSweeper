package net.kenkku.swagsweeper.game;

import java.util.Observable;
import net.kenkku.swagsweeper.util.Position;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class Square extends Observable {
    
    private boolean isMine;
    private Position position;
    private boolean revealed;
    
    Square(Position position, boolean isMine) {
        this.position = position;
        this.isMine = isMine;
    }
    
    boolean isAt(Position otherPos) {
        return position.equals(otherPos);
    }
    
    boolean reveal() {
        revealed = true;
        setChanged();
        notifyObservers();
        return false;
    }
    
    boolean isRevealed() {
        return revealed;
    }

    Position getPosition() {
        return position;
    }
    
    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isMine() {
        return isMine;
    }
}
