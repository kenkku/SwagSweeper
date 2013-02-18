package net.kenkku.swagsweeper.game;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.kenkku.swagsweeper.game.actions.Action;
import net.kenkku.swagsweeper.game.actions.RevealAction;
import net.kenkku.swagsweeper.util.Position;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class MinesweeperGameTest {

    private MinesweeperGame game;
    private Action action;

    public MinesweeperGameTest() {
    }

    @Before
    public void setUp() {
        game = new MinesweeperGame(5, 5);
        action = new RevealAction(game, new Position(0, 0));
        game.setField(FieldGenerator.emptyField(5, 5));
    }

    @Test
    public void testAddMove() {
        game.gameOver();
        
        // Should not happen
        game.addMove(action);
        assertEquals(0, game.getActions().size());
    }

    @Test
    public void testIsRunning() {
        assertFalse("Game should not be running after creation", game.isRunning());
        
        game.addMove(action);
        assertTrue("Game should be running after adding first action", game.isRunning());
        
        game.gameOver();
        assertFalse("Game should not be running after calling gameOver", game.isRunning());
    }
    
    @Test
    public void testGetTime() throws InterruptedException {
        assertTrue("Time should be 0 before starting", game.getTime() == 0);
        
        game.addMove(action);
        Thread.sleep(10);
        
        long timeAfterMove = game.getTime();
        assertTrue("Elapsed time should be greater than 0 after adding move", 
                0 < timeAfterMove);
        
        game.gameOver();
        
        long timeGameOver = game.getTime();
        Thread.sleep(10);
        long timeGameOverAndWait = game.getTime();
        
        assertEquals("Timer should stop after game is over", 
                timeGameOver, timeGameOverAndWait);
                
    }
}
