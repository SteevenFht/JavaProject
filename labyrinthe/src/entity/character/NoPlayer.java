package entity.character;

/**
 * @author Steeven
 * An abstract class for the noPlayer
 */

import plateau.*;

import java.util.*;

public abstract class NoPlayer extends Charact implements Effect{
    protected String text;

    public NoPlayer(String name, Cell cell, String text) {
        super(name, cell);
        this.text = text;

        List<NoPlayer> persoPresent = this.getCell().getAreNoPlayer();
        persoPresent.add(this);
        super.getCell().setAreNoPlayer(persoPresent);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public abstract void effect(Player p);

    /**
     * Give the position of no player
     * The no player can move
     */
    public void move() {
        Orientation choix;
        List<Orientation> availableChoice = this.availableMovement();
        int n =0;
        Collections.shuffle(availableChoice);
        choix = availableChoice.get(n);
        if (this.goodDestination(choix) && this.getCanMove()) {
            this.getCell().getAreNoPlayer().remove(this);
            this.setCell(futurCell(choix));
            this.getCell().getAreNoPlayer().add(this);
        }
        
    }

}
