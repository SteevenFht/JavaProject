package entity.character;

/**
 * @Date 23/02/2022
 * @Author Madeline Carpentier
 * ShopKeeperTest class can test ShopKeeper
 */

import entity.*;
import entity.item.*;
import org.junit.Before;
import org.junit.Test;
import plateau.*;
import plateau.utilities.*;

import static org.junit.Assert.assertEquals;

public class ShopKeeperTest {
    private Labyrinthe l1;
    private Sidewinder s1;

    private Inventory sac;
    private ShopKeeper pnj1;

    private Item i1;

    @Before
    public void initialisation() {
        l1 = new Labyrinthe(10, 10);
        s1 = new Sidewinder(l1);

        pnj1 = new ShopKeeper("Raspoutine", l1.getCell(0, 0), "Je vend plein de chose ça t'interesse ?");

        i1 = new PotionLife();
    }

    @Test
    public void incressPriceTest() {
        pnj1.addItemForSell(i1);
        assertEquals(pnj1.incressPrice(i1), 111);
    }
}

