package net.kenkku.swagsweeper.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class PositionTest {
    
    public PositionTest() {
    }

    @Test
    public void testEquals() {
        Position pos1 = new Position(3, 6);
        Position pos2 = new Position(3, 6);
        Position pos3 = new Position(2, 6);
        
        assertTrue(pos1.equals(pos2));
        assertFalse(pos2.equals(pos3));
    }
}
