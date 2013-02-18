package net.kenkku.swagsweeper.game.actions;

import net.kenkku.swagsweeper.game.Field;
import net.kenkku.swagsweeper.game.FieldGenerator;
import net.kenkku.swagsweeper.game.MinesweeperGame;
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
    
    private Field field;
    private MinesweeperGame game;
    
    public RevealActionTest() {
    }
    
    @Before
    public void setUp() {
        game = new MinesweeperGame(3, 3);
        game.setField(FieldGenerator.emptyField(3, 3));
        field = game.getField();
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
        field.getSquare(new Position(2, 2)).setMine(true);
        
        Action r = new RevealAction(game, new Position(1, 1));
        r.execute();

        String mask = "000\n"
                    + "010\n"
                    + "000";
        assertTrue(ActionTestUtils.revealedMatches(field, mask));
    }
    
    @Test
    public void testRevealEmptySquares() {
        field.getSquare(new Position(2, 2)).setMine(true);
        
        Action r = new RevealAction(game, new Position(0, 0));
        r.execute();
        
        String mask = "111\n"
                    + "111\n"
                    + "110";
        assertTrue(ActionTestUtils.revealedMatches(field, mask));
    }
    
    @Test
    public void testRevealUndo() {
        field.getSquare(new Position(0, 0)).setMine(true);

        Action r = new RevealAction(game, new Position(1, 1));
        r.execute();
        r.undo();

        String mask = "000\n"
                    + "000\n"
                    + "000";
        assertTrue("Undoing one square reveal", ActionTestUtils.revealedMatches(field, mask));
        
        r = new RevealAction(game, new Position(2,2));
        r.execute();
        r.undo();
        
        assertTrue("Undoing multi square reveal", ActionTestUtils.revealedMatches(field, mask));
    }
    
    @Test
    public void testHittingMine() {
        Position minepos = new Position(0, 0);
        field.getSquare(minepos).setMine(true);
        
        Action action = new RevealAction(game, minepos);
        game.addMove(action);
        
        assertTrue("Game should be over after hitting a mine", 
                game.isGameOver());
        assertFalse("Game should not be running after hitting a mine", 
                game.isRunning());
        
        String mask = "111\n"
                    + "111\n"
                    + "111";
        assertTrue("All squares should be revealed after hitting a mine", 
                ActionTestUtils.revealedMatches(field, mask));
    }
}
