package net.kenkku.swagsweeper.game.actions;

import java.util.LinkedList;
import java.util.List;
import net.kenkku.swagsweeper.game.Field;
import net.kenkku.swagsweeper.game.Square;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class RevealAll extends Action {

    private List<Square> revealed = new LinkedList<Square>();
    
    
    RevealAll(Field field) {
        this.field = field;
    }

    @Override
    public void execute() {
        for(Square square : field.getAllSquares()){
            if(!square.isRevealed()) {
                square.setRevealed(true);
                revealed.add(square);
            }
        }
    }

    @Override
    public void undo() {
        for (Square square : revealed) {
            square.setRevealed(false);
        }
    }
    
}
