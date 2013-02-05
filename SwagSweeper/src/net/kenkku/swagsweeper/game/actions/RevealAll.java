package net.kenkku.swagsweeper.game.actions;

import java.util.LinkedList;
import java.util.List;
import net.kenkku.swagsweeper.game.Field;
import net.kenkku.swagsweeper.game.MinesweeperGame;
import net.kenkku.swagsweeper.game.Square;

/**
 * Kaikkien jäljelläolevien ruutujen avaaminen, käytetään miinaan osuttaessa
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class RevealAll extends Action {

    private List<Square> revealed = new LinkedList<Square>();
    
    
    RevealAll(MinesweeperGame game) {
        this.game = game;
        this.field = game.getField();
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
