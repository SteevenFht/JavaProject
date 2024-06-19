package entity.character;

/**
 * @Date 23/02/2022
 * @Author Madeline Carpentier
 * MinotaurTest class can test Spinx
 */

import entity.*;
import org.junit.Before;
import org.junit.Test;
import plateau.*;
import plateau.utilities.*;

import static org.junit.Assert.assertEquals;

public class MinotorTest {
    private Labyrinthe l1;
    private Sidewinder s1;

    private Inventory sac;
    private Player p1;
    private Minotaur pnj1;

    @Before
    public void initialisation() {
        l1 = new Labyrinthe(2, 2);
        s1 = new Sidewinder(l1);

        sac = new Inventory();
        p1 = new Player("Steeven", l1.getCell(0, 0), 0, sac, 10);
        pnj1 = new Minotaur("Minotor", l1.getCell(0, 0), "PERDUEEEEE");

    }


    @Test
    public void effectTest() {

        pnj1.effect(p1);
        assertEquals(p1.getHP(), 9);

        p1.setCell(Labyrinthe.labyrinthe[1][0]);
        pnj1.setCell(Labyrinthe.labyrinthe[1][0]);
        pnj1.effect(p1);
        assertEquals(p1.getHP(), 7);
    }

}
