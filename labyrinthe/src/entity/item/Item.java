/**
 * @author Massimiliano Righetti
 */
package entity.item;
/**
 * An abstract class item who extends entity and implements interface effect
 */

import entity.*;
import entity.character.*;

public abstract class Item extends Entity implements Effect {
    protected String text;
    protected int price;
    protected boolean onPickUpEfect;


    public Item(String name, String text, int price)
    {
        super(name);
        this.text = text;
        this.price = price;
        this.onPickUpEfect = false;
    }

    /**
     * return the price the item cost
     * @return the price the item cost
     */
    public int getPrice(){return this.price;}

    /**
     * get the item description
     * @return the item description
     */
    public String getText(){return this.text;}

    /**
     * return true if the item applies its effect when you pick it up from the floor
     * @return The boolean value of onPickUpEfect.
     */
    public boolean getOnPickUpEfect(){return this.onPickUpEfect;}

    /**
     * set the price of the item
     * @param price new price for the item
     */
    public void setPrice(int price){this.price = price;}

    /**
     * set a new item description
     * @param text the new description
     * ! utile ?
     */
    public void setText(String text){this.text = text;}

    /**
     * The effect of item
     * @param p the player
     */
    public abstract void effect(Player p);



}