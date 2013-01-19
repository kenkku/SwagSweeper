package net.kenkku.swagsweeper.game;

import java.util.Observable;
import java.util.Observer;
import net.kenkku.swagsweeper.util.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class SquareTest {

    Square s;

    public SquareTest() {
        s = new Square(new Position(1, 2), false);
    }

    private class TestObserver implements Observer {

        public boolean updated = false;

        @Override
        public void update(Observable o, Object arg) {
            updated = true;
            System.out.println("updated");
        }
    }

    @Test
    public void testObservable() {
        TestObserver observer = new TestObserver();

        s.addObserver(observer);
        s.reveal();

        assertTrue("Square should update observers after revealing",
                observer.updated);
    }

    @Test
    public void testReveal() {
        // TODO: test for reveal() return value?
        assertFalse(s.isRevealed());

        s.reveal();
        assertTrue(s.isRevealed());
    }
}
