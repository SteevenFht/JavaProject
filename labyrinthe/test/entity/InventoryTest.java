package entity;
/**
 * @Date 23/02/2022
 * @Author Madeline Carpentier
 * InventoryTest class can test Inventory
 */
import entity.item.*;
import org.junit.Before;
import org.junit.Test;
import plateau.Labyrinthe;
import plateau.utilities.RecursiveBacktracker;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class InventoryTest {
    private Labyrinthe l1;
    private RecursiveBacktracker r1;

    private Inventory sac;

    private Item i1, i2;

    private int cptEl;

    @Before
    public void initialisation() {
        l1 = new Labyrinthe(10, 10);
        r1 = new RecursiveBacktracker(l1);

        sac = new Inventory();

        i1 = new PotionLife();
        i2 = new PotionLife();
    }

    @Test
    public void dropItemTest() {
        sac.addItem(i1);
        sac.addItem(i1);
        sac.addItem(i2);

        sac.dropItem(i2);
        assertFalse(sac.getInventory().containsKey(i2));
        sac.dropItem(i1);
        cptEl = sac.getInventory().get(i1);
        assertEquals(cptEl, 1);
        sac.dropItem(i1);
        assertTrue(sac.getInventory().isEmpty());
    }

    @Test
    public void allItemTest() {
        sac.addItem(i1);
        sac.addItem(i2);
        ArrayList<Item> liste = new ArrayList<>();
        liste.add(i1);
        liste.add(i2);

        for(int i = 0 ; i < sac.allItem().size() ; i++) {
            //assertEquals(sac.allItem().get(1), liste.get(0));
            //assertEquals(sac.allItem().get(0), liste.get(1));
            //ATTENTIOn A VERIFIER
            assertEquals(sac.allItem().get(i), liste.get(i));
        }

    }
}
