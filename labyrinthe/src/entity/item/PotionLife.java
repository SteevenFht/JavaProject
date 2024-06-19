/**
 * @Author Massimiliano Righetti
 * A class potionLife who give HP of player
 */
package entity.item;

import entity.character.*;

public class PotionLife extends Item {
    private static final int HP = 10;

    public PotionLife()
    {
        super("Potion de soin", "Restaure " + HP + " point de vie", 110);
    }

    /**
     * This method will add the amount of HP to the player's current HP
     * @param p The player who is using the item.
     */
    @Override
    public void effect(Player p) {
        int currentHP = p.getHP();
        p.setHP(currentHP + HP);
        p.getInventory().dropItem(this);
    }
}
