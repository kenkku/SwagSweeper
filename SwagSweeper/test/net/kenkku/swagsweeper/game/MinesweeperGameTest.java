package net.kenkku.swagsweeper.game;

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
        game = new MinesweeperGame();
        action = new RevealAction(new Field(), new Position(0, 0));
    }

    @Test
    public void testAddMove() {
        game.gameOver();

        assertFalse("addMove should not add the move if game is over", game.addMove(action));
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
    public void testGetTime() {
        assertTrue(game.getTime() == 0);
    }
}
