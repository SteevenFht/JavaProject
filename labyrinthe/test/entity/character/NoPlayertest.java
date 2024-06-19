package entity.character;

import org.junit.*;
import plateau.*;
import plateau.utilities.*;

import static org.junit.Assert.*;

public class NoPlayertest {
    private Labyrinthe laby1;
    private RecursiveBacktracker gen1;

    private NoPlayer p1;

    @Before
    public void initialisation() {
        laby1 = new Labyrinthe(1, 2);
        gen1 = new RecursiveBacktracker(laby1);
        p1 = new Minotaur("Gribouille", laby1.getCell(0, 0), "Je suis enferme");

    }

    @Test
    public void futurCellTest() {
        Cell east = laby1.getCell(0, 1);
        assertEquals(east, p1.futurCell("East"));
        assertEquals(east, p1.futurCell("east"));
    }

    @Test
    public void goodDestination() {
        assertTrue(p1.goodDestination("east"));
        assertFalse(p1.goodDestination("southe"));
        assertFalse(p1.goodDestination("eAsTe"));
        assertFalse(p1.goodDestination(""));
    }


}
