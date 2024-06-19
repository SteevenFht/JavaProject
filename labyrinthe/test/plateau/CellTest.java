package plateau;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {
    public Cell c1;

    @Before
    public void initialisation() {
        c1 = new Cell(1, 2);
    }

    @Test
    public void getPositionTest() {
        assertEquals(c1.getAbscisse(), 1);
        assertEquals(c1.getOrdonnee(), 2);
    }

    @Test
    public void ouestWallTest() {
        assertTrue(c1.getWest());
        c1.setWallWest();
        assertFalse(c1.getWest());
        c1.setWallWest();
        assertTrue(c1.getWest());
    }

    @Test
    public void nordWallTest() {
        assertTrue(c1.getNorth());
        c1.setWallNorth();
        assertFalse(c1.getNorth());
        c1.setWallNorth();
        assertTrue(c1.getNorth());
    }

    @Test
    public void estWallTest() {
        assertTrue(c1.getEast());
        c1.setWallEast();
        assertFalse(c1.getEast());
        c1.setWallEast();
        assertTrue(c1.getEast());
    }

    @Test
    public void sudWallTest() {
        assertTrue(c1.getSouth());
        c1.setWallSouth();
        assertFalse(c1.getSouth());
        c1.setWallSouth();
        assertTrue(c1.getSouth());
    }
}
