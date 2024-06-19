/**
 * @author Madeline Carpentier
 * @Date 21/03/2022
 * An abstract class who link Player and NoPlayer
 */

package entity.character;

import entity.*;
import plateau.*;

import java.util.*;

public abstract class Charact extends Entity {
    private Cell cell;
    protected boolean canMove;

    public Charact(String name, Cell c) {
        super(name);
        this.cell = c;
        canMove = true;
    }

    public Cell getCell() {
        return cell;
    }

    public int getPositionX() { return this.getCell().getAbscisse(); }

    public int getPositionY() { return  this.getCell().getOrdonnee(); }

    public boolean getCanMove() {return this.canMove;}

    public void setCanMove(boolean canMove) {this.canMove = canMove;}

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    /**
     *
     * @return the list of string of availableMovement (ex : north, south, east)
     */
    public List<Orientation> availableMovement() {
        List<Orientation> destinationPossible = new ArrayList<>();
        HashMap<Orientation, Boolean > murAlentour = this.getCell().getWall();

        for (Map.Entry<Orientation, Boolean> mur : murAlentour.entrySet() ) {
            if (! mur.getValue()) {
                destinationPossible.add(mur.getKey());
            }
        }
        return destinationPossible;
    }

    /**
     * @param select, the choice of character
     * @return the futur position of character
     */
    public Cell futurCell(Orientation select) {
        int xCurrent = this.getCell().getOrdonnee();
        int yCurrent = this.getCell().getAbscisse();
        Cell newCell;

        switch(select) {
            case West:
                newCell = Labyrinthe.labyrinthe[xCurrent][yCurrent-1];
                break;
            case North:
                newCell = Labyrinthe.labyrinthe[xCurrent-1][yCurrent];
                break;
            case East:
                newCell = Labyrinthe.labyrinthe[xCurrent][yCurrent+1];
                break;
            case South:
                newCell = Labyrinthe.labyrinthe[xCurrent+1][yCurrent];
                break;
            default:
                newCell = Labyrinthe.labyrinthe[xCurrent][yCurrent];
                break;
        }

        return newCell;
    }

    /**
     * Verify the destination of character
     * @param choice, the choice
     * @return true if the choice is ok
     */
    protected boolean goodDestination(Orientation choice) {
        return this.availableMovement().contains(choice);
    }

    /**
     * An abstract method for move the charact
     */
    public abstract void move();
}
