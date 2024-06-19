/**
 * @author Madeline Carpentier
 * A class menu for the parameter of labirynthe
 */
package display;

import java.util.Scanner;

public class Menu {
    /**
     *
     * @return int who can choice the creation method labyrinthe (1 for recursive Backtracker, 2 for Sidewinder)
     */
    public static int choiceLabyrinthe() {
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        try {
            while (choix != 1 && choix != 2) {
                System.out.println("Enchante veuillez choisir la maniere dont votre labyrinte sera genere (taper 1 ou 2) : ");
                System.out.println("1.RecursiveBacktracker       2.Sidewinder");

                choix = scanner.nextInt();
            }

        }catch (Exception e) {
            System.out.println("On choisi pour vous ce sera 1 ^^");
            choix = 1;
        }
        return choix;

    }

    /**
     *
     * @return the numbers of line of labyrinthe (>0)
     */
    public static int choiceLine() {
        int ligne = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            while (ligne <= 0) {
                System.out.println("Veulliez saisir le nombre de lignes de votre labyrinte :");
                ligne = scanner.nextInt();
            }
        }catch (Exception e) {
            System.out.println("Bon on choisi pour vous il y aura 10 lignes Youpi");
            ligne = 10;
        }
        return ligne;
    }

    /**
     * @return the numbers of column of labyrinthe (>0)
     */
    public static int choiceColumn() {
        int colonne = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            while (colonne <= 0) {
                System.out.println("Veullliez saisir le nombre de colonnes de votre labyrinte :");
                colonne = scanner.nextInt();
            }
        }catch (Exception e) {
            System.out.println("Bon on choisi pour vous il y aura 10 colonnes Youpi");
            colonne = 10;
        }
        return colonne;
    }

    /**
     * @return the name choice by the player
     */
    public static String choiceNamePlayer() {
        Scanner scanner = new Scanner(System.in);
        String choix = "";
        try {
            System.out.println("Comment vous vous appelez votre personnage ? ");
            choix = scanner.nextLine();
            if (choix == null || choix.equals("")) choix = "Gulliver";
        }catch (Exception e) {
            choix = "Gulliver";
        }
        System.out.println("Enchante " + choix);
        return choix;
    }
}
