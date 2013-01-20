package net.kenkku.swagsweeper.game.actions;

import net.kenkku.swagsweeper.game.Field;
import net.kenkku.swagsweeper.game.FieldGenerator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class RevealAllTest {
    
    private Field f;
    
    public RevealAllTest() {
        f = FieldGenerator.emptyField(3, 3);
    }
    
    @Test
    public void testRevealAll() {
        Action r = new RevealAll(f);
        r.execute();
        
        String mask = "111\n"
                    + "111\n"
                    + "111";
        assertTrue(ActionTestUtils.revealedMatches(f, mask));
    }
}
