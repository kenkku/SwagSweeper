package net.kenkku.swagsweeper.game;

import java.util.HashSet;
import java.util.Set;
import net.kenkku.swagsweeper.util.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class FieldTest {

    private Field f;

    @Before
    public void setUp() {
        f = FieldGenerator.emptyField(5, 5);
    }

    @Test
    public void testSetSquare() {
        Field field = new Field();
        Square square = new Square(new Position(1, 2), false);

        field.setSquare(square);

        assertEquals(square, field.getSquare(new Position(1, 2)));
    }

    @Test
    public void testGetAdjacents() {
        Set<Square> adjacents = f.getAdjacent(f.getSquare(new Position(1, 1)));
        Set<Square> expected = new HashSet<Square>();
        
        expected.add(f.getSquare(new Position(0, 0)));
        expected.add(f.getSquare(new Position(0, 1)));
        expected.add(f.getSquare(new Position(0, 2)));
        expected.add(f.getSquare(new Position(1, 0)));
        expected.add(f.getSquare(new Position(1, 2)));
        expected.add(f.getSquare(new Position(2, 0)));
        expected.add(f.getSquare(new Position(2, 1)));
        expected.add(f.getSquare(new Position(2, 2)));
        
        assertTrue(expected.equals(adjacents));
    }
    
    /**
     * Tests mine count around square
     * 
     * -------
     * |     |
     * | 111 |
     * | 1*1 |
     * | 1121|
     * |   1*|
     * |-----|
     */
    @Test
    public void testMineCountForPosition() {
        f.getSquare(new Position(4, 4)).setMine(true);
        f.getSquare(new Position(2, 2)).setMine(true);
        
        assertEquals("Square should have 2 mines", 2, f.getNumMines(f.getSquare(new Position(3, 3))));
        assertEquals("Square should have 1 mines", 1, f.getNumMines(f.getSquare(new Position(2, 3))));
        assertEquals("Square should have 0 mines", 0, f.getNumMines(f.getSquare(new Position(0, 0))));
    }
    
    @Test
    public void testMineCount() {
        assertEquals("Empty field should have 0 mines", 0, f.getMineCount());
        f.getSquare(new Position(4, 4)).setMine(true);
        f.getSquare(new Position(2, 2)).setMine(true);
        assertEquals("Field should have 2 mines after setting 2", 2, f.getMineCount());
        
    }
}
