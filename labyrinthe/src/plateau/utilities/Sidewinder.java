/**
 * A class who extends generator and can create a Labyrinthe with the sidewinder method
 *
 * @author Madeline Carpentier
 */
package plateau.utilities;

import java.util.Random;

import plateau.Cell;
import plateau.Labyrinthe;

import java.util.List;
import java.util.ArrayList;

public class Sidewinder extends Generator {

    public Sidewinder(Labyrinthe laby) {
        super(laby);
    }

    /**
     *
     * @param n, the number of current line
     * @return true, if is the last line of labyrinth
     */
    private boolean lastLine(int n) {
        return n == super.getLabyrinthe().getNBLigne()-1;
    }

    /**
     * remove the useless wall
     * @param i, the number of current line
     * @param j, the number of current column
     */
    private void removeEast(int i, int j) {
        super.getLabyrinthe().getCell(i, j).setWallEast();
        if (j < super.getLabyrinthe().getNBColonne()) {
            super.getLabyrinthe().getCell(i, j+1).setWallWest();
        }
    }



    /**
     * remove the usless south wall of current cell and (when it's possible) the north cell of previous cell north
     * @param c, the current cell
     */
    private void removeSouth(Cell c) {
        c.setWallSouth();
        if (c.getOrdonnee() < super.getLabyrinthe().getNBColonne()) {
            this.labyrinthe.getCell(c.getOrdonnee()+1, c.getAbscisse()).setWallNorth();
        }
    }


    /**
     *
     * @param n, the number of current column
     * @return true, if we are not on the last column
     */
    private boolean canRemoveEastWall(int n) {
        return n != super.getLabyrinthe().getNBColonne()-1;
    }

    /**
     * the one line action
     * @param i, the number of current line
     */
    private void oneLineAction(int i) {
        //initialisation des variables
        Random aleatoire = new Random();
        int des = 0;
        List<Cell> pile = new ArrayList<Cell>();

        for (int j = 0 ; j < super.getLabyrinthe().getNBColonne() ; j++) {
            pile.add(this.getLabyrinthe().getCell(i, j)); //On ajoute la nouvelle case a la liste
            des = aleatoire.nextInt(2);

            //On vérifie si on est sur la derniére ligne et si on peut supprimer le mur est ou si le des est a 0 et le mur est supprimable
            if ((lastLine(i) || des == 0) && canRemoveEastWall(j)) {
                this.removeEast(i, j);
            }
            else {
                //Sinon on prend une case au hasard dans celle parcourue
                des = aleatoire.nextInt(pile.size());

                //On vérifie su elle n'est pas sur la derniére ligne
                if ( ! lastLine(pile.get(des).getOrdonnee()) ) {
                    this.removeSouth(pile.get(des));
                }

                //On vide la liste quand on supprime un mur sud
                pile.clear();
            }
        }
    }

    /**
     * Allows to initialize the maze with the Sidewinder method
     *
     * It applies line by line, as soon as we are on a square we stoke it in a list,
     * then we throw 1d2 if we fall on 1 we see if we can remove the east wall if so we remove.
     * Otherwise we take a box on which we are passing at random, we see if we can remove its south wall if so we remove
     * otherwise we go to the next box. Each time a south wall is removed and each line changes, the list is emptied
     */
    public void init() {
        for (int i = 0 ; i < super.getLabyrinthe().getNBLigne() ; i++) {
            oneLineAction(i);
        }
    }
}