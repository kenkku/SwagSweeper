package net.kenkku.swagsweeper.game.actions;

import net.kenkku.swagsweeper.game.Field;
import net.kenkku.swagsweeper.game.FieldGenerator;
import net.kenkku.swagsweeper.util.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class RevealActionTest {
    
    private Field f;
    
    public RevealActionTest() {
    }
    
    @Before
    public void setUp() {
        f = FieldGenerator.emptyField(3, 3);
    }
    
    /**
     * Testataan tapaus, jossa vain yksi ruutu paljastetaan
     * 
     * -----
     * |   |
     * | 11|
     * | 1*|
     * -----
     */
    @Test
    public void testRevealOnlyOneSquare() {
        f.getSquare(new Position(2, 2)).setMine(true);
        
        Action r = new RevealAction(f, new Position(1, 1));
        r.execute();

        String mask = "000\n"
                    + "010\n"
                    + "000";
        assertTrue(ActionTestUtils.revealedMatches(f, mask));
    }
    
    @Test
    public void testRevealEmptySquares() {
        f.getSquare(new Position(2, 2)).setMine(true);
        
        Action r = new RevealAction(f, new Position(0, 0));
        r.execute();
        
        String mask = "111\n"
                    + "111\n"
                    + "110";
        assertTrue(ActionTestUtils.revealedMatches(f, mask));
    }

}
