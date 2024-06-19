package entity.item;

/**
 * @Date 23/02/2022
 * @Author Madeline Carpentier
 * PotionLifeTest class can test PotionLife
 */

import entity.Inventory;
import entity.character.*;
import org.junit.Before;
import org.junit.Test;

import plateau.*;
import plateau.utilities.Sidewinder;

import static org.junit.Assert.assertEquals;


public class PotionLifeTest {
    private Labyrinthe l1;
    private Sidewinder s1;

    private Item i1;

    private Player p1;
    private Inventory sac;

    @Before
    public void initialisation() {
        l1 = new Labyrinthe(10, 10);
        s1 = new Sidewinder(l1);

        i1 = new PotionLife();

        sac = new Inventory();
        sac.addItem(i1);
        Cell cell = l1.getRandomCell();
        p1 = new Player("", cell, 10, sac, 2);
    }

    @Test
    public void effectTest() {
        i1.effect(p1);
        assertEquals(p1.getHP(), 12);
    }
}
