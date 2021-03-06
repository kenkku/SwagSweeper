package net.kenkku.swagsweeper.game;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.kenkku.swagsweeper.util.Position;

/**
 * Miinakenttä, pitää kirjaa Square-olioista
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class Field {

    private Map<Position, Square> squares = new HashMap<Position, Square>();

    void setSquare(Square square) {
        squares.put(square.getPosition(), square);
    }

    public Set<Square> getAdjacent(Square aroundSquare) {
        Set<Square> adjacents = new HashSet<Square>();

        Position pos = aroundSquare.getPosition();
        for (int x = pos.getX() - 1; x <= pos.getX() + 1; x++) {
            for (int y = pos.getY() - 1; y <= pos.getY() + 1; y++) {
                Square s = squares.get(new Position(x, y));
                if (s != null) {
                    adjacents.add(s);
                }
            }
        }
        adjacents.remove(aroundSquare);
        return adjacents;
    }

    public Collection<Square> getAllSquares() {
        return squares.values();
    }
    
    public int getMineCount() {
        int minecount = 0;
        for(Square square : squares.values()) {
            if(square.isMine()) {
                minecount++;
            }
        }
        return minecount;
    }
    
    public int getNumMines(Square aroundSquare) {
        Set<Square> adjacents = getAdjacent(aroundSquare);
        
        int mines = 0;
        for (Square square : adjacents) {
            if(square.isMine()) {
                mines++;
            }
        }
        return mines;
    }
    
    public Square getSquare(Position position) {
        return squares.get(position);
    }

}
