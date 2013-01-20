package net.kenkku.swagsweeper.game.actions;

import net.kenkku.swagsweeper.game.Field;
import net.kenkku.swagsweeper.game.Square;
import net.kenkku.swagsweeper.util.Position;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class RevealAction extends Action {

    private Square square;

    public RevealAction(Field field, Position position) {
        this.field = field;
        square = field.getSquare(position);
    }

    @Override
    public void execute() {
        square.reveal();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
