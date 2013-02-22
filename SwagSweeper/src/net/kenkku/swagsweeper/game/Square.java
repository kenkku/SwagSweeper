package net.kenkku.swagsweeper.game;

import java.util.Observable;
import net.kenkku.swagsweeper.util.Position;

/**
 * Miinakentän yksi ruutu
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class Square {
    
    /** Onko ruutu miina */
    private boolean isMine;
    /** Ruudun sijainti kentällä */
    private Position position;
    /** Onko pelaaja avannut ruudun */
    private boolean revealed;
    
    Square(Position position, boolean isMine) {
        this.position = position;
        this.isMine = isMine;
    }
    
    /**
     * Asettaa ruudun piilossa/näkyvillä -statuksen ja päivittää 
     * käyttöliittymäkomponentit (UISquare)
     */
    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }
    
    public boolean isRevealed() {
        return revealed;
    }

    public Position getPosition() {
        return position;
    }
    
    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isMine() {
        return isMine;
    }
}
