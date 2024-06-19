package entity.character;

import org.junit.*;
import plateau.*;
import plateau.utilities.*;

import java.util.*;

import static org.junit.Assert.*;

public class CharactTest {
    private Labyrinthe laby1, laby2;
    private RecursiveBacktracker gen1, gen2;
    private Charact c1, c2;

    @Before
    public void initialisation() {
        laby1 = new Labyrinthe(1, 1);
        gen1 = new RecursiveBacktracker(laby1);
        c1 = new Player("Jo", laby1.getRandomCell());

        laby2 = new Labyrinthe(1, 2);
        gen2 = new RecursiveBacktracker(laby2);
        c2 = new Player("Jo", laby2.getCell(0, 0));
    }

    @Test
    public void availableMovenmentEmptyTest() {
        List<String> aucunMur = new ArrayList<>();
        List<String> murEst = new ArrayList<>();
        murEst.add("east");

        assertEquals(aucunMur, c1.availableMovement());
        assertEquals(murEst, c2.availableMovement());

    }
}
