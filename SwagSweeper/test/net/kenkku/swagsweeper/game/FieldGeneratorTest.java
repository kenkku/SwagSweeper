package net.kenkku.swagsweeper.game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kenkku
 */
public class FieldGeneratorTest {
    @Test
    public void testRandomFieldGeneration() {
        Field field = FieldGenerator.randomField(10, 10, 5);
        
        assertEquals("Field should have correct number of mines", 
                5, field.getMineCount());
    }

}
