package net.kenkku.swagsweeper.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import net.kenkku.swagsweeper.util.Position;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class Field implements Observer {

    private Map<Position, Square> squares = new HashMap<Position, Square>();

    void setSquare(Square square) {
        squares.put(square.getPosition(), square);
    }

    Set<Square> getAdjacent(Square aroundSquare) {
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

    public Square getSquare(Position position) {
        return squares.get(position);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass() != Square.class) {
            return;
        }

        Square changedSquare = (Square) o;


    }
}
