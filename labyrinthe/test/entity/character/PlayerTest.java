package entity.character;

/**
 * @Date 23/02/2022
 * @Author Madeline Carpentier
 * PlayerTest class can test Player
 */

import entity.Inventory;
import entity.character.*;
import entity.item.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import plateau.*;
import plateau.utilities.Sidewinder;


public class PlayerTest {
    private Labyrinthe l1;
    private Sidewinder s1;

    private Player p1;
    private Inventory sac, liste; 
    private Item i1, i2; //i1 est un item qui vaux 7 et i2 est gratuit



    @Before
    public void initialisation() {
        l1 = new Labyrinthe(10, 10);
        s1 = new Sidewinder(l1);

        p1 = new Player("Antoine", l1.getRandomCell()); //500 or, 100 hp

        liste = new Inventory();
    }

    @Test
    public void setMoneyTest() {
        //Le joueur dépenses/gagne de la money c'est donc modifier
        //Ca correspond a un set
        p1.setMoney(10);
        assertEquals(p1.getMoney(), 510);
        p1.setMoney(-5);
        assertEquals(p1.getMoney(), 505);
        p1.setMoney(-600);
        assertEquals(p1.getMoney(), 0); //On ne peut pas avoir - de 0
    }

    @Test
    public void isDeadTest() {
        p1.setHP(-200);
        assertTrue(p1.isDead());
        p1.setHP(111);
        assertFalse(p1.isDead());
    }

    @Test
    public void canBuyTest() {
        assertTrue(p1.canBuy(50));
        assertTrue(p1.canBuy(500));
        assertFalse(p1.canBuy(600));
    }
}
