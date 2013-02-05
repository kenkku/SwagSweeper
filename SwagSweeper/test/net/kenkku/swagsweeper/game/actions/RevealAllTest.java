package net.kenkku.swagsweeper.game.actions;

import net.kenkku.swagsweeper.game.Field;
import net.kenkku.swagsweeper.game.FieldGenerator;
import net.kenkku.swagsweeper.game.MinesweeperGame;
import net.kenkku.swagsweeper.util.Position;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class RevealAllTest {
    
    private Field field;
    private MinesweeperGame game;
    
    public RevealAllTest() {
        game = new MinesweeperGame(3, 3);
        game.setField(FieldGenerator.emptyField(3, 3));
        field = game.getField();
    }
    
    @Test
    public void testRevealAll() {
        Action r = new RevealAll(game);
        r.execute();
        
        String mask = "111\n"
                    + "111\n"
                    + "111";
        assertTrue(ActionTestUtils.revealedMatches(field, mask));
    }
    
    @Test
    public void testRevealAllUndo() {
        field.getSquare(new Position(1, 1)).setRevealed(true);
        
        Action r = new RevealAll(game);
        r.execute();
        r.undo();

        String mask = "000\n"
                    + "010\n"
                    + "000";
        assertTrue(ActionTestUtils.revealedMatches(field, mask));
    }
}
