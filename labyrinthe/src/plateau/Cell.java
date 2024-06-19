/**
 * @author Steeven
 * A cell of labyrinthe
 */

package plateau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import entity.character.*;
import entity.item.Item;


public class Cell {
    private int abscisse;
    private int ordonnee;
    private HashMap< Orientation , Boolean > wall = new HashMap<>(); //creates "wall" hashmap with a "String" key and a "boolean" value
    private boolean isPlayer;
    private List<NoPlayer> areNoPlayer;
    private List<Item> itemList;
    //des expériences
    private boolean isEnd;

    /**
     * Cell constructor with (x,y) coordinates, it's a hashmap!
     * @param x the abscissa of the Cell
     * @param y the ordinate of the Cell
     */
    public Cell(int x , int y){
        this.abscisse = x;
        this.ordonnee = y;
        this.isPlayer=false;
        this.areNoPlayer = new ArrayList<>();
        this.itemList = new ArrayList<>();
        wall.put(Orientation.West, true);
        wall.put(Orientation.North, true);
        wall.put(Orientation.East, true);
        wall.put(Orientation.South, true);

        
        this.isEnd = false;
    }

    public boolean getIsEnd() {
        return this.isEnd;
    }

    public void setIsEnd() {
        this.isEnd = !this.isEnd;
    }

    /**
     *
     * @return the abscissa of the cell
     */
    public int getAbscisse() {
        return abscisse;
    }

    /**
     *
     * @return the ordinate of the cell
     */
    public int getOrdonnee() {
        return ordonnee;
    }

    /**
     *
     * @return the hashmap
     */
    public HashMap<Orientation, Boolean> getWall() {
        return this.wall;
    }

    /**
     *
     * @return the value of west wall
     */
    public boolean getWest(){
        return this.getWall().get(Orientation.West);
    }

    /**
     *
     * @return the value of north wall
     */
    public boolean getNorth() {
        return this.getWall().get(Orientation.North);
    }

    /**
     *
     * @return the value of east wall
     */
    public boolean getEast() {
        return this.getWall().get(Orientation.East);
    }

    /**
     *
     * @return, the value of south wall
     */
    public boolean getSouth() {
        return this.getWall().get(Orientation.South);
    }

    /**
     *
     * @return the array of noPlayer present
     */
    public List<NoPlayer> getAreNoPlayer() {
        return areNoPlayer;
    }

    /**
     *
     * @param areNoPlayer the new array of no player
     */
    public void setAreNoPlayer(List<NoPlayer> areNoPlayer) {
        this.areNoPlayer = areNoPlayer;
    }

    /**
     * This function returns a list of items.
     * @return itemList is being returned.
     */
    public List<Item> getItemList() {return itemList;}

    /**
     * This function sets the value of the itemList variable to the value of the itemList parameter.
     * @param itemList The list of items to be displayed in the list.
     */
    public void setItemList(List<Item> itemList) {this.itemList = itemList;}

    /**
     * Can to change value of Orientation.West in wall
     */
    public void setWallWest() {
        this.getWall().replace(Orientation.West, ! this.getWall().get(Orientation.West));
    }

    /**
     * Can to change value of Orientation.North in wall
     */
    public void setWallNorth() {
        this.getWall().replace(Orientation.North, ! this.getWall().get(Orientation.North));
    }

    /**
     * Can to change value of Orientation.East in wall
     */
    public void setWallEast() {
        this.getWall().replace(Orientation.East, ! this.getWall().get(Orientation.East));
    }

    /**
     * Can to change value of Orientation.South in wall
     */
    public void setWallSouth() {
        this.getWall().replace(Orientation.South, ! this.getWall().get(Orientation.South));
    }

    /**
     * change the state of a wall of this cell depending of the orientation and isWall
     * @param orientation the orientation of the wall to change
     * @param isWall: boolean, is there a wall at that orientation
     */
    public void setWall(Orientation orientation, boolean isWall) {
        this.getWall().replace(orientation, isWall);
    }

    /**
     * @return ture if player is here
     */
    public boolean getIsPlayer(){ return isPlayer; }

    /**
     * can to change value of isPlayer
     */
    public void setPlayer(){ isPlayer = !(isPlayer) ; }
    
    /**
     * brokenWall is the list with the walls that are broken
     * @return brokenWall
     * UTILISER ?
     */
    public ArrayList<Orientation> allBrokenWall(){
        ArrayList<Orientation> brokenWall = new ArrayList<>();
        for (Orientation key : this.wall.keySet()) {
            if (! this.wall.get(key)){   
                brokenWall.add(key);
            }
        }
        return brokenWall;
    }

    /**
     * Given a cell, return true if the cell is equal to this cell
     * 
     * @param o the object to compare to this object
     * @return The boolean value of the equals method.
     */
    public boolean equals(Object o){
        if (o instanceof Cell){
           Cell other = (Cell) o;
           return (other.getAbscisse() == this.getAbscisse() && 
                   other.getOrdonnee() == this.getOrdonnee() &&
                   other.getEast() == this.getEast() &&
                   other.getNorth() == this.getNorth() &&
                   other.getSouth() == this.getSouth() &&
                   other.getWest() == this.getWest() &&
                   other.getIsPlayer() == this.getIsPlayer() ) ;
        }
        else {
            return false;
        }
    }

    public boolean isWin() {
        return this.getIsPlayer() && this.getIsEnd();
    }


    /**
     * Look the pnj and make the effect (if is possible)
     * @param p, the currrent player
     */
    public void pnjEffectCell(Player p) {
        if (this.getIsPlayer()) {
            for (NoPlayer pnj : this.getAreNoPlayer()) {
                if (pnj instanceof Minotaur) pnj.effect(p);
                if ((pnj instanceof Sphinx) && !((Sphinx) pnj).getFind()) p.setCanMove(false);
            }
        }

    }
}