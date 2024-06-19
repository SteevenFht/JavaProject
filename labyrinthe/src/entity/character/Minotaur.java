/**
 * @Author Massimiliano Righetti
 * A class for the Minotor a Character of Labyrinthe who extends Noplayer, he make damage of player
 */

package entity.character;

import plateau.*;

public class Minotaur extends NoPlayer{
    private int attack;

    public Minotaur(String name, Cell cell, String text) {
        super(name, cell, text);
        this.attack = 1;
    }

    public int getAttack(){
        return this.attack;
    }

    public void setAttack(int atta) { this.attack = atta;}


    /**
     * make damage if the player have the same position
     * @param p the player
     */
    @Override
    public void effect(Player p) {
        System.out.println(super.getText());
        p.setHP(p.getHP()-this.getAttack());
        this.setAttack(this.getAttack()+1);
    }
}
