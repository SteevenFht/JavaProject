package plateau;
/**
* @Author Madeline Carpentier
* A class Labyrinthe who can instance the Labyrinthe
*/

import entity.*;
import entity.character.*;
import entity.item.PotionLife;

import java.util.*;

public class Labyrinthe {
    private int nbLigne;
    private int nbColonne;
    private Cell winnerCell;
    private static int winY;
    private static int winX;
    public static Cell[][] labyrinthe;
    //private boolean fin (fonction renvoie un boolean si accomplie)

    public Labyrinthe(int i, int j) {
        nbLigne = i;
        nbColonne = j;
        labyrinthe = new Cell[i][j];
        for (int a = 0 ; a < nbLigne ; a++) {
            for (int b = 0 ; b < nbColonne ; b++) {
                labyrinthe[a][b] = new Cell(b, a);

            }
        }
        this.winnerCell = this.getRandomCell();
        this.winnerCell.setIsEnd();
        Labyrinthe.winX = this.winnerCell.getAbscisse();
        Labyrinthe.winY = this.winnerCell.getOrdonnee();
    }

        public static int getWinY() {
        return winY;
    }
    
    public static void setWinY(int winY) {
        Labyrinthe.winY = winY;
    }

    public static int getWinX() {
        return winX;
    }

    public static void setWinX(int winX) {
        Labyrinthe.winX = winX;
    }

    public Cell getWinCell() {
        return this.winnerCell;
    }

    /**
     * @return a random cell of labyrinthe
     */
    public Cell getRandomCell() {
        Random r = new Random();
        int positionX = r.nextInt(this.getNBLigne());
        int positionY = r.nextInt(this.getNBColonne());

        return this.getCell(positionX, positionY);
    }

    /**
     *
     * @return the chart of cell
     */
    public Cell[][] getLabyrinthe() {
        return labyrinthe;
    }

    /**
     *
     * @param x, the abscissa of the cell
     * @param y, the ordinate of the cell
     * @return the cell, which is at the given coordinates
     */
    public Cell getCell(int y, int x) {
        return labyrinthe[y][x];
    }

    public int getNBLigne() {
        return this.nbLigne;
    }

    public int getNBColonne() {
        return this.nbColonne;
    }

    /**
     * Give the total numbers of cell
     * @return, int the total numbers of cell
     */
    public int nbTotalCell() { return this.getNBLigne() * this.getNBColonne(); }
}
